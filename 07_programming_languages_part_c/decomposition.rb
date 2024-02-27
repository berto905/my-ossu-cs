class Exp 
    #
end

class Value < Exp
    #
end

class Int < Value
    attr_reader :i 
    def initialize i 
        @i =  i 
    end
    def eval
        self
    end
    def toString
        @i.to_s
    end
    def hasZero
        i==0
    end
    def noNegConstants
        if i < 0
            Negate.new(Int.new(-1))
        else
            self
        end
    end
    # double dispatch for adding values
    def add_values v # first dispatch
        v.addInt self
    end
    def addInt v # second dispatch: other is Int
        Int.new(v.i + i)
    end
    def addString v # second dispatch: other is String
        MyString.new(v.s + i.to_s)
    end
    def addRational v # second dispatch: other is Rational
        MyRational.new(v.i + v.j*i, v.j)
    end
end

class MyString < Value
    attr_reader :s
    def initialize s
        @s = s
    end
    def eval
        self
    end
    def toString
        s
    end
    def hasZero
        false
    end
    def noNegConstants
        self
    end
    # double dispatch for adding values
    def add_values v # first dispatch
        v.addString self
    end
    def addInt v # second dispatch: other is Int
        String.new(v.i.to_s + s)
    end
    def addString v # second dispatch: other is String
        MyString.new(v.s + s)
    end
    def addRational v # second dispatch: other is Rational
        MyString.new(v.i.to_s + "/" + v.j.to_s + s)
    end
end

class MyRational < Value
    attr_reader :i, :j
    def initialize(i, j)
        @i = i
        @j = j
    end
    def eval
        self
    end
    def toString
        i.to_s + "/" + j.to_s
    end
    def hasZero
        i==0
    end
    def noNegConstants
        if i < 0 && j < 0
            MyRational.new(-i, -j)
        elsif j < 0
            Negate.new(MyRational.new(i, -j))
        elsif i < 0
            Negate.new(MyRational.new(-i, j))
        else
            self
        end
    end
    # double dispatch for adding values
    def add_values v # first dispatch
        v.addRational self
    end
    def addInt v # second dispatch: other is Int
        v.addRational self
    end
    def addString v # second dispatch: other is String
        MyString.new(v.s + i.to_s + "/" + j.to_s )
    end
    def addRational v # second dispatch: other is Rational
        a, b, c, d = i, j, v.i, v.j
        MyRational.new(a+d+b*c, b*d)
    end
end

class Negate < Exp
    attr_reader :e 
    def initialize e 
        @e = e
    end
    def eval
        Int.new(-e.eval.i)
    end
    def toString
        "-(" + e.toString + ")"
    end
    def hasZero
        e.hasZero
    end
    def noNegConstants
        Negate.new(e.noNegConstants)
    end
end

class Add < Exp
    attr_reader :e1, :e2
    def initialize(e1, e2)
        @e1 = e1
        @e2 = e2
    end
    def eval
        e1.eval.add_values e2.eval.i
    end
    def toString
        "(" + e1.toString + " + " + e2.toString + ")"
    end
    def hasZero
        e1.hasZero || e2.hasZero
    end
    def noNegConstants
        Add.new(e1.noNegConstants, e2.noNegConstants)
    end
end

class Mult < Exp
    attr_reader :e1, :e2
    def initialize(e1, e2)
        @e1 = e1
        @e2 = e2
    end
    def eval
        Int.new(e1.eval.i * e2.eval.i)
    end
    def toString
        "(" + e1.toString + " * " + e2.toString + ")"
    end
    def hasZero
        e1.hasZero || e2.hasZero
    end
    def noNegConstants
        Mult.new(e1.noNegConstants, e2.noNegConstants)
    end
end