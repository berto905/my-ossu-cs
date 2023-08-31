(* Function #1 *)
fun is_older (d1:int*int*int, d2:int*int*int) =
    if ((#1 d1) < (#1 d2) 
       orelse (((#1 d1) = (#1 d2)) andalso ((#2 d1) < (#2 d2)))
       orelse (((#1 d1) = (#1 d2)) andalso ((#2 d1) = (#2 d2)) andalso ((#3 d1) < (#3 d2))))
    then true
    else false

(* Function #2 *)
fun number_in_month (dlist: (int*int*int) list, mo:int) =
    if null dlist
    then 0
    else 
        if (#2 (hd dlist)) = mo
        then 1 + number_in_month ((tl dlist), mo)
        else 0 + number_in_month ((tl dlist), mo)

(* Function #3 *)
fun number_in_months (dlist: (int*int*int) list, mlist: int list) =
    if null mlist
    then 0
    else 
        number_in_month(dlist, (hd mlist)) + number_in_months (dlist, (tl mlist))

(* Function #4 *)
fun dates_in_month (dlist : (int*int*int) list, mo:int) =
    if null dlist
    then []
    else
        if (#2 (hd dlist)) = mo
        then (hd dlist) :: (dates_in_month (tl dlist, mo))
        else dates_in_month (tl dlist, mo)

(* Function #5 *)
fun dates_in_months (dlist: (int*int*int) list, mlist: int list) =
    if null mlist
    then []
    else
        dates_in_month (dlist, (hd mlist)) @ dates_in_months (dlist, (tl mlist))

(* Function #6 *)
fun get_nth (strs0 : string list, n:int) =
    let 
        fun get_str (strs : string list, x:int) =
            if (null strs) orelse (n <= 0)
            then ""
            else
                if x = n
                then hd strs
                else get_str ((tl strs), x + 1)
    in 
        get_str (strs0, 1)
    end

(* Function #7 *)
fun date_to_string (d:int*int*int) =
    get_nth(["January", "February", "March", "April", "May", "June", 
             "July", "August", "September", "October", "November", "December"], 
             (#2 d)) ^
    " " ^
    (Int.toString (#3 d)) ^
    ", " ^
    (Int.toString (#1 d))

(* Function #8 *)
fun number_before_reaching_sum (sum:int, nums0 : int list) =
    let fun get_ind (current_sum : int, ind:int, nums : int list) =
            let val next_sum = current_sum + (hd nums)
            in
                if next_sum >= sum
                then (ind-1)
                else get_ind (next_sum, ind + 1, (tl nums))
            end
    in get_ind (0, 1, nums0)
    end

(* Function #9 *)
fun what_month (day:int) =
    number_before_reaching_sum (day, [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]) + 1

(* Function #10 *)
fun month_range (dstart:int, dend:int) =
    let fun get_month (d:int, dend:int) =
            if d > dend
            then []
            else what_month d :: get_month (d+1, dend)
    in get_month (dstart, dend)
    end


(* Function #11 *)
fun oldest (dlist0 : (int*int*int) list) = 
    if null dlist0
    then NONE 
    else let
            fun oldest_nonempty (dlist : (int*int*int) list) = 
                if null (tl dlist)
                then hd dlist
                else let val tl_ans = oldest_nonempty(tl dlist)
                     in
                        if is_older (hd dlist, tl_ans)
                        then hd dlist
                        else tl_ans
                     end
         in
             SOME (oldest_nonempty dlist0)
         end

(* Helper function for Problem 12*)
fun isin (n:int, nums : int list) = 
    if null nums
    then false
    else if hd nums = n
         then true
         else isin (n, tl nums)

(* Helper function for Problem 12*)
fun unique (nums0 : int list) = 
    let fun append_element (curr : int list, nums : int list) =
            if null nums
            then rev curr
            else if isin (hd nums, curr)
                 then append_element(curr, tl nums)
                 else append_element ((hd nums) :: curr, tl nums)
    in append_element ([], nums0)
    end

(* Challenge Problem 12a *)
fun number_in_months_challenge (dlist: (int*int*int) list, mlist0: int list) =
    let fun get_number (mlist : int list) = 
            if null mlist
            then 0
            else number_in_month(dlist, (hd mlist)) + get_number (tl mlist)
    in get_number (unique (mlist0))
    end

(* Challenge Problem 12b *) 
fun dates_in_months_challenge (dlist: (int*int*int) list, mlist1: int list) =
    let fun get_dates (mlist : int list) = 
            if null mlist
            then []
            else
                dates_in_month (dlist, (hd mlist)) @ get_dates (tl mlist)
    in get_dates (unique (mlist1))
    end
  


(* Helper function for Challenge Problem 13*)
fun is_leap_year (y:int) = 
    if (y mod 400) = 0
    then true
    else if (((y mod 100) = 0) andalso ((y mod 400) <> 0))
    then false
    else if (y mod 4) = 0
    then true
    else false

(* Helper function for Challenge Problem 13*)  
fun get_nth_int (nums0 : int list, n:int) =
    let 
        fun get_int (nums : int list, x:int) =
            if (null nums) orelse (n <= 0)
            then 0
            else
                if x = n
                then hd nums
                else get_int ((tl nums), x + 1)
    in 
        get_int (nums0, 1)
    end

(* Helper function for Challenge Problem 13*)
fun is_date_month_matched (d : int*int*int) = 
    let val max_date = get_nth_int ([31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31], (#2 d))
    in
        if max_date >= (#3 d)
        then true
        else false
    end

(* Challenge Problem 13 *)
fun reasonable_date (d : int*int*int) = 
    if (((#1 d) < 1) orelse ((#2 d) < 1) orelse ((#3 d) < 1))
    then false
    else if ((is_leap_year (#1 d)) andalso ((#2 d) = 2) andalso ((#3 d) <= 29) andalso ((#3 d) >= 1))
    then true
    else if (#2 d) > 12
    then false
    else if is_date_month_matched d
    then true
    else false
