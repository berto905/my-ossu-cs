(* Homework2 Simple Test *)
(* These are basic test cases. Passing these tests does not guarantee that your code will pass the actual homework grader *)
(* To run the test, add a new line to the top of this file: use "homeworkname.sml"; *)
(* All the tests should evaluate to true. For example, the REPL should say: val test1 = true : bool *)

use "homework2.sml";

val test1  = all_except_option ("string", ["string"]) = SOME []
val test1a = all_except_option ("string", ["string", "not string"]) = SOME ["not string"]
val test1b = all_except_option ("string", ["not string", "not string too"]) = NONE

val test2  = get_substitutions1 ([["foo"],["there"]], "foo") = []
val test2a = get_substitutions1 ([["Fred","Fredrick"], ["Elizabeth","Betty"], ["Freddie","Fred","F"]],"Fred")
           = ["Fredrick","Freddie","F"]
val test2b = get_substitutions1 ([["Fred","Fredrick"],["Jeff","Jeffrey"],["Geoff","Jeff","Jeffrey"]],"Jeff")
           = ["Jeffrey","Geoff","Jeffrey"]

val test3   = get_substitutions2 ([["foo"],["there"]], "foo") = []
val test3a  = get_substitutions2 ([["Fred","Fredrick"], ["Elizabeth","Betty"], ["Freddie","Fred","F"]],"Fred")
            = ["Fredrick","Freddie","F"]
val test3aa = get_substitutions2 ([["Fred","Fredrick"], ["Elizabeth","Betty"], ["Freddie","Fred","F"]],"Fred")
val test3ab = "Fred" :: test3aa
val test3b  = get_substitutions2 ([["Fred","Fredrick"],["Jeff","Jeffrey"],["Geoff","Jeff","Jeffrey"]],"Jeff")
            = ["Jeffrey","Geoff","Jeffrey"]

val test4 = similar_names ([["Fred","Fredrick"],["Elizabeth","Betty"],["Freddie","Fred","F"]], {first="Fred", middle="W", last="Smith"}) =
	    [{first="Fred", last="Smith", middle="W"}, {first="Fredrick", last="Smith", middle="W"},
	     {first="Freddie", last="Smith", middle="W"}, {first="F", last="Smith", middle="W"}]

val test4a = similar_names ([["Fred","Fredrick"],["Elizabeth","Betty"],["Freddie","Fred","F"]], 
                            {first="Fred", middle="W", last="Smith"})


val test5  = card_color (Clubs, Num 2) = Black
val test5a = card_color (Spades, Num 2) = Black
val test5b = card_color (Hearts, Num 2) = Red
val test5c = card_color (Diamonds, Num 2) = Red

val test6  = card_value (Clubs, Num 2) = 2
val test6a = card_value (Clubs, Ace) = 11
val test6b = card_value (Clubs, King) = 10

val test7  = remove_card ([(Hearts, Ace)], (Hearts, Ace), IllegalMove) = []
val test7a = remove_card ([(Hearts, Ace), (Spades, Num 9), (Diamonds, King)], 
                          (Hearts, Ace), IllegalMove)
           = [(Spades, Num 9), (Diamonds, King)]

val test7b = ((remove_card ([(Spades, Num 9), (Diamonds, King)], 
                          (Hearts, Ace), IllegalMove);
               false)
              handle IllegalMove => true)

val test8  = all_same_color [(Hearts, Ace), (Hearts, Ace)] = true
val test8a = all_same_color [] = true
val test8b = all_same_color [(Hearts, Ace)] = true
val test8c = all_same_color [(Hearts, Ace), (Clubs, Num 9)] = false
val test8d = all_same_color [(Hearts, Ace), (Hearts, Ace)] = true
val test8e = all_same_color [(Hearts, Ace), (Diamonds, Ace)] = true

val test9 = sum_cards [(Clubs, Num 2),(Clubs, Num 2)] = 4

val test10  = score ([(Hearts, Num 2),(Clubs, Num 4)],10) = 4
val test10a = score ([(Hearts, Num 2),(Clubs, Ace)],10) = 9
val test10b = score ([(Hearts, Num 2),(Diamonds, King)],9) = 4
val test10c = score ([(Hearts, Num 2),(Diamonds, Num 4)],9) = 1

val test11 = officiate ([(Hearts, Num 2),(Clubs, Num 4)],[Draw], 15) = 6

val test12 = officiate ([(Clubs,Ace),(Spades,Ace),(Clubs,Ace),(Spades,Ace)],
                        [Draw,Draw,Draw,Draw,Draw],
                        42)
             = 3

val test13 = ((officiate([(Clubs,Jack),(Spades,Num(8))],
                         [Draw,Discard(Hearts,Jack)],
                         42);
               false) 
              handle IllegalMove => true)
             
            
