(* Coursera Programming Languages, Homework 3, Provided Code *)

exception NoAnswer

datatype pattern = Wildcard
				 | Variable of string
		 		 | UnitP
				 | ConstP of int
				 | TupleP of pattern list
				 | ConstructorP of string * pattern

datatype valu = Const of int
		      | Unit
		      | Tuple of valu list
		      | Constructor of string * valu

fun g f1 f2 p =
    let 
	val r = g f1 f2 
    in
	case p of
	    Wildcard          => f1 ()
	  | Variable x        => f2 x
	  | TupleP ps         => List.foldl (fn (p,i) => (r p) + i) 0 ps
	  | ConstructorP(_,p) => r p
	  | _                 => 0
    end

(**** for the challenge problem only ****)

datatype typ = Anything
	     | UnitT
	     | IntT
	     | TupleT of typ list
	     | Datatype of string

(**** you can put all your code here ****)

(* Problem 1 *)
fun only_capitals lst = 
    List.filter (fn x => Char.isUpper (String.sub (x, 0))) lst

(* Problem 2 *)
fun longest_string1 lst = 
    foldl (fn (x, acc) => 
				    if String.size x > String.size acc
                    then x
					else acc)
		   ""
		   lst

(* Problem 3 *)
fun longest_string2 lst = 
    foldl (fn (x, acc) => 
				    if String.size x >= String.size acc
                    then x
					else acc)
		   ""
		   lst

(* Problem 4 *)
fun longest_string_helper f lst = foldl (fn (x, y) => if f(String.size x, String.size y) then x else y) 
										"" lst

val longest_string3 = longest_string_helper (fn (x, y) => x > y)
val longest_string4 = longest_string_helper (fn (x, y) => x >= y)

(* Problem 5 *)
val longest_capitalized = longest_string3 o only_capitals

(* Problem 6 *)
fun rev_string str = (String.implode o rev o String.explode) str

(* Problem 7 *)
fun first_answer f lst =
    case lst of
		[]    => raise NoAnswer
 	  | x::xs' => case (f x) of 
	  		         SOME v => v
				   | NONE   => first_answer f xs'

(* Problem 8 *)
fun all_answers f lst = 
    let fun func (acc, xs) = 
		case xs of
			[]     => SOME acc
		  | x::xs' => case (f x) of
					      NONE   => NONE
						| SOME v => func ((acc @ v), xs')
	in func ([], lst)
	end

(* Problem 9a *)
fun count_wildcards p = g (fn _ => 1) (fn _ => 0) p
    
(* Problem 9b *)
fun count_wild_and_variable_lengths p = 
    count_wildcards p + g (fn _ => 0) (fn x => String.size x) p

(* Problem 9c *)
fun count_some_var (s, p) = 
    g (fn _ => 0) (fn x => if x = s then 1 else 0) p

(* Problem 10 *)
fun check_pat p =
	let 
	    fun all_strings p =
		    case p of
			    Variable x => [x]
			  | TupleP ps => foldl (fn (p,acc) => acc @ all_strings p) [] ps
			  | ConstructorP(_, p) => all_strings p
	  		  | _ => []
		fun no_repeat los =
		    case los of
				[] => true
			  | head::rest => if List.exists (fn x => x = head) rest
							  then false
							  else no_repeat rest
	in (no_repeat o all_strings) p 
	end

(* Problem 11 *)
fun match (v, p) = 
    case (v, p) of
		(_, Wildcard) => SOME []
	  | (v, Variable s) => SOME [(s, v)]
	  | (Unit, UnitP) => SOME []
	  | (Const i1, ConstP i2) => if i1 = i2 then SOME [] else NONE
	  | (Tuple vs, TupleP ps) => if List.length vs = List.length ps
	  							 then all_answers match (ListPair.zip (vs, ps))
								 else NONE
	  | (Constructor (s1, v), ConstructorP (s2, p)) =>
  			if (s1 = s2) then match (v, p) else NONE
	  | _ => NONE

(* Problem 12 *)
fun first_match v ps = 
	SOME (first_answer (fn p => match (v, p)) ps) 
	handle NoAnswer => NONE

(*

*)