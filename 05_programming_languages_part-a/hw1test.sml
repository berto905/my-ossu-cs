(* Homework1 Simple Test *)
(* These are basic test cases. Passing these tests does not guarantee that your code will pass the actual homework grader *)
(* To run the test, add a new line to the top of this file: use "homeworkname.sml"; *)
(* All the tests should evaluate to true. For example, the REPL should say: val test1 = true : bool *)

use "hw1.sml";

val test1a = is_older ((1,2,3), (2,3,4)) = true
val test1b = is_older ((1,2,3), (1,3,4)) = true
val test1c = is_older ((1,2,3), (1,2,4)) = true
val test1d = is_older ((1,2,3), (1,2,3)) = false
val test1e = is_older ((2,3,4), (1,2,3)) = false
val test1f = is_older ((2,3,4), (2,2,4)) = false
val test1g = is_older ((2,3,4), (2,3,3)) = false

val test2a = number_in_month ([(2012,2,28), (2013,12,1)],2) = 1
val test2b = number_in_month ([(2012,2,28), (2013,12,1), (2013,2,12)], 2) = 2 
val test2c = number_in_month ([(2012,2,28), (2013,12,1), (2013,2,12)], 6) = 0

val test3a = number_in_months ([(2012,2,28),(2013,12,1),(2011,3,31),(2011,4,28)],[2,3,4]) = 3
val test3b = number_in_months ([(2012,2,28),(2013,12,1),(2011,3,31),(2011,4,28)],[]) = 0
val test3c = number_in_months ([(2012,2,28),(2013,12,1),(2011,3,31),(2011,4,28)],[1,5,6]) = 0

val test4a = dates_in_month ([(2012,2,28),(2013,12,1)],2) = [(2012,2,28)]
val test4b = dates_in_month ([],2) = []
val test4c = dates_in_month ([(2012,2,28),(2013,12,1)],6) = []
val test4d = dates_in_month ([(2012,2,28),(2013,12,1),(2013,2,12)],2) = [(2012,2,28),(2013,2,12)]

val test5a = dates_in_months ([(2012,2,28),(2013,12,1),(2011,3,31),(2011,4,28)],[2,3,4]) = [(2012,2,28),(2011,3,31),(2011,4,28)]
val test5b = dates_in_months ([(2012,2,28),(2013,12,1),(2011,3,31),(2011,4,28)],[]) = []
val test5c = dates_in_months ([(2012,2,28),(2013,12,1),(2011,3,31),(2011,4,28)],[1,5,6]) = []

val test6a = get_nth (["hi", "there", "how", "are", "you"], 2) = "there"
val test6b = get_nth (["hi", "there", "how", "are", "you"], 1) = "hi"
val test6c = get_nth (["hi", "there", "how", "are", "you"], 0) = ""
val test6d = get_nth (["hi", "there", "how", "are", "you"], 6) = ""

val test7a = date_to_string (2013, 6, 1) = "June 1, 2013"

val test8 = number_before_reaching_sum (10, [1,2,3,4,5]) = 3

val test9a = what_month 70 = 3
val test9b = what_month 59 = 2
val test9z = what_month 60 = 3


val test10a = month_range (31, 34) = [1,2,2,2]
val test10b = month_range (58, 63) = [2, 2, 3, 3, 3, 3]

val test11 = oldest([(2012,2,28),(2011,3,31),(2011,4,28)]) = SOME (2011,3,31) 

val test12h1 = isin (2, [1, 2, 3]) = true
val test12h2 = isin (4, [1, 2, 3]) = false
val test12h3 = unique ([1, 2, 2, 1, 3, 5, 3, 6]) = [1, 2, 3, 5, 6]
val test12h4 = unique ([1, 2, 3, 4]) = [1, 2, 3, 4]
val test12a = number_in_months_challenge ([(2012,2,28),(2013,12,1),(2011,3,31),(2011,4,28)],[2,3,4, 3, 2]) = 3
val test12b = dates_in_months_challenge ([(2012,2,28),(2013,12,1),(2011,3,31),(2011,4,28)],[2,3,4,2,4]) = [(2012,2,28),(2011,3,31),(2011,4,28)]

val test13h1 = is_leap_year 2000 = true
val test13h2 = is_leap_year 1900 = false
val test13h3 = is_leap_year 1996 = true
val test13h4 = is_leap_year 1997 = false

val test13h5 = is_date_month_matched (2021, 1, 32) = false
val test13h6 = is_date_month_matched (2021, 1, 31) = true
val test13h7 = is_date_month_matched (2021, 4, 31) = false
val test13h8 = is_date_month_matched (2021, 4, 30) = true
val test13h9 = is_date_month_matched (2021, 2, 29) = false

val test13h10 = get_nth_int ([31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31], 2) = 28

val test13a = reasonable_date (1996,2,29) = true
val test13b = reasonable_date (~1,2,13) = false
val test13c = reasonable_date (2,0,12) = false
val test13d = reasonable_date (3,2,29) = false
val test13e = reasonable_date (20, 4, 31) = false
val test13f = reasonable_date (20, 4, 30) = true
val test13g = reasonable_date (20, 5, 33) = false
val test13h = reasonable_date (20, 13, 2) = false
