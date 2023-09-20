(* Homework3 Simple Test*)
(* These are basic test cases. Passing these tests does not guarantee that your code will pass the actual homework grader *)
(* To run the test, add a new line to the top of this file: use "homeworkname.sml"; *)
(* All the tests should evaluate to true. For example, the REPL should say: val test1 = true : bool *)

use "homework3.sml";

val test1  = only_capitals ["A","B","C"] = ["A","B","C"]
val test1a = only_capitals ["Aa","bB","cc"] = ["Aa"]
val test1b = only_capitals ["a", "b", "c"] = []

val test2  = longest_string1 ["A","bc","C"] = "bc"
val test2a = longest_string1 ["A","bc","Cc"] = "bc"
val test2b = longest_string1 ["A","bc","Ccc"] = "Ccc"

val test3 = longest_string2 ["A","bc","C"] = "bc"
val test3a = longest_string2 ["A","bc","Cc"] = "Cc"
val test3b = longest_string2 ["A","bc","Ccc"] = "Ccc"


val test4a = longest_string3 ["A","bc","C"] = "bc"
val test4aa = longest_string3 ["A","bc","Cc"] = "bc"
val test4ab = longest_string3 ["A","bc","Ccc"] = "Ccc"

val test4b = longest_string4 ["A","B","C"] = "C"
val test4ba = longest_string4 ["A","bc","Cc"] = "Cc"
val test4bb = longest_string4 ["A","bc","Ccc"] = "Ccc"

val test5 = longest_capitalized ["A","bc","C"] = "A"

val test6 = rev_string "abc" = "cba"

val test7 = first_answer (fn x => if x > 3 then SOME x else NONE) [1,2,3,4,5] = 4

val test8  = all_answers (fn x => if x = 1 then SOME [x] else NONE) [2,3,4,5,6,7] = NONE
val test8a = all_answers (fn x => if x > 1 then SOME [x] else NONE) [2,3,4,5,6,7] = SOME [2,3,4,5,6,7]
val test8b = all_answers (fn x => if x = 2 then SOME [x] else NONE) [3,2,4,5,6,7] = NONE
val test8c = all_answers (fn x => if x mod 2 = 0 then SOME [x] else NONE) [2,4,5,6,8] = NONE
val test8d = all_answers (fn x => if x mod 2 = 0 then SOME [x] else NONE) [2,4,6,8] = SOME [2,4,6,8]
val test8e = all_answers (fn x => if x mod 2 = 0 then SOME [x, x + 1] else NONE) [2,4,6,8] = SOME [2,3,4,5,6,7,8,9]
val test8f = all_answers (fn x => if x mod 2 = 0 then SOME [] else NONE) [2,4,6,8] = SOME []
val test8g = all_answers (fn x => if x mod 2 = 0 then SOME [x] else NONE) [] = SOME []

val test9a  = count_wildcards Wildcard = 1
val test9aa = count_wildcards (Variable "str") = 0
val test9ab = count_wildcards (TupleP [Wildcard, ConstP 12, Wildcard]) = 2
val test9ac = count_wildcards (ConstructorP("pattern", (TupleP [Wildcard, ConstP 12, Wildcard]))) = 2

val test9b  = count_wild_and_variable_lengths (Variable("a")) = 1
val test9ba = count_wild_and_variable_lengths Wildcard = 1
val test9bb = count_wild_and_variable_lengths (TupleP [Wildcard, ConstP 12, Wildcard]) = 2
val test9bc = count_wild_and_variable_lengths (TupleP [Wildcard, Variable "str", Wildcard]) = 5
val test9bd = count_wild_and_variable_lengths (TupleP [Wildcard, Variable "str", Wildcard, Variable "str2"]) = 9
val test9be = count_wild_and_variable_lengths (ConstructorP("pattern", (TupleP [Wildcard, ConstP 12, Wildcard]))) = 2
val test9bf = count_wild_and_variable_lengths (ConstructorP("pattern", (TupleP [Wildcard, Variable "str", Wildcard]))) = 5

val test9c  = count_some_var ("x", Variable("x")) = 1
val test9ca = count_some_var ("x", (TupleP [Wildcard, ConstP 12, Wildcard])) = 0
val test9cb = count_some_var ("x", (TupleP [Wildcard, Variable "str", Wildcard])) = 0
val test9cc = count_some_var ("x", (TupleP [Wildcard, Variable "x", Wildcard])) = 1
val test9cd = count_some_var ("x", (TupleP [Wildcard, Variable "x", Wildcard, Variable "x"])) = 2
val test9ce = count_some_var ("x", (ConstructorP("pattern", (TupleP [Wildcard, Variable "x", Wildcard])))) = 1
val test9cf = count_some_var ("x", (ConstructorP("x", (TupleP [Wildcard, Variable "x", Wildcard])))) = 1

val test10 = check_pat (Variable("x")) = true
val test10a = check_pat (TupleP [Wildcard, Variable "x", Wildcard]) = true
val test10b = check_pat (TupleP [Wildcard, Variable "x", Variable "y"]) = true
val test10c = check_pat (TupleP [Wildcard, Variable "x", Variable "x"]) = false
val test10d = check_pat (ConstructorP("x", (TupleP [Wildcard, Variable "x", Wildcard]))) = true
val test10e = check_pat (ConstructorP("x", (TupleP [Wildcard, Variable "x", ConstructorP("y", Variable "y")]))) = true
val test10f = check_pat (ConstructorP("x", (TupleP [Wildcard, Variable "x", ConstructorP("y", Variable "x")]))) = false
val test10g = check_pat (ConstructorP("x", (TupleP [Wildcard, Variable "x", ConstructorP("y", TupleP [Variable "y"])]))) = true
val test10h = check_pat (ConstructorP("x", (TupleP [Wildcard, Variable "x", ConstructorP("y", TupleP [Variable "z"])]))) = true
val test10i = check_pat (ConstructorP("x", (TupleP [Wildcard, Variable "x", ConstructorP("y", TupleP [Variable "x"])]))) = false
val test10j = check_pat (ConstructorP("x", (ConstructorP("y", TupleP [Variable "x", Variable "y"])))) = true
val test10k = check_pat (ConstructorP("x", (ConstructorP("y", TupleP [Variable "x", Variable "x"])))) = false
val test10l = check_pat (TupleP [Wildcard, Variable "x", TupleP [Variable "y"]]) = true

val test11 = match (Const(1), UnitP) = NONE
val test11a = match (Const(1), ConstP 1) = SOME []
val test11b = match (Const(1), Variable "s") = SOME [("s", Const(1))]
val test11c = match (Const(1), TupleP [Wildcard]) = NONE
val test11d = match (Const(1), TupleP [ConstP 1]) = NONE
val test11e = match (Tuple [Unit], TupleP [UnitP]) = SOME []
val test11f = match (Tuple [Tuple [Unit]], TupleP [TupleP[UnitP]]) = SOME []
val test11g = match (Tuple [Tuple [Unit]], TupleP [TupleP[UnitP, Variable "x"]]) = NONE
val test11h = match (Tuple [Const(1), Tuple [Unit]], TupleP [ConstP 1, TupleP[UnitP]]) = SOME []
val test11i = match (Tuple [Const(1), Tuple [Unit, Const(2)]], TupleP [ConstP 1, TupleP[UnitP, Variable("s")]]) = SOME [("s", Const(2))]
val test11j = match (Tuple [Const(1), Tuple [Unit, Const(2)]], TupleP [ConstP 2, TupleP[UnitP, Variable("s")]]) = NONE
val test11k = match (Tuple [Const(1), Tuple [Unit, Const(2)]], TupleP [ConstP 1, TupleP[UnitP, Variable("s"), Wildcard]]) = NONE

val test12 = first_match Unit [UnitP] = SOME []
val test12a = first_match Unit [Variable ("s")] = SOME [("s", Unit)]
val test12b = first_match (Tuple [Const(1), Tuple [Unit, Const(2)]]) [(TupleP [ConstP 1, TupleP[UnitP, Variable("s")]])] = SOME [("s", Const(2))]
val test12c = first_match (Tuple [Const(1), Tuple [Unit, Const(2)]]) [(TupleP [ConstP 1, TupleP[UnitP, ConstP 3]])] = NONE
(*
*)