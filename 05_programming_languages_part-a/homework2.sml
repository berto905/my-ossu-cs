(* Dan Grossman, Coursera PL, HW2 Provided Code *)

(* if you use this function to compare two strings (returns true if the same
   string), then you avoid several of the functions in problem 1 having
   polymorphic types that may be confusing *)
fun same_string(s1 : string, s2 : string) =
    s1 = s2

(* put your solutions for problem 1 here *)

(* Problem 1a *)
fun all_except_option (str, lst0) = 
    let fun get_opt (head, tail) =
        case tail of
            []       => NONE
          | x::tail' => if same_string(x, str)
                        then SOME (head @ tail')
                        else get_opt (x::head, tail')
    in get_opt ([], lst0)
    end

(* Problem 1b *)
fun get_substitutions1 (lst, str) =
    case lst of
        []       => []
      | x::tail' => case all_except_option (str, x) of
                        NONE => get_substitutions1 (tail', str)
                      | SOME los => los @ get_substitutions1 (tail', str)

(* Problem 1c *)
fun get_substitutions2 (lst0, str) =
    let fun build_list (lst, acc) = 
        case lst of
            []       => acc
          | x::tail' => let val to_list = all_except_option (str, x) 
                        in 
                            case to_list of
                                NONE => build_list (tail', acc)
                              | SOME los => build_list (tail', acc @ los)
                        end
    in build_list (lst0, [])
    end

(* Problem 1d *)
fun similar_names (lst, {first=first, middle=middle, last=last}) =
    let val substitutions0 =  [first] @ get_substitutions2 (lst, first)
    in
        let fun full_names (substitutions, acc) =
            case substitutions of
                [] => acc
              | x::tail' => full_names (tail',
                                           acc @ [{first=x, last=last, middle=middle}])
        in full_names (substitutions0, [])
        end
    end

(* you may assume that Num is always used with values 2, 3, ..., 10
   though it will not really come up *)
datatype suit = Clubs | Diamonds | Hearts | Spades
datatype rank = Jack | Queen | King | Ace | Num of int 
type card = suit * rank

datatype color = Red | Black
datatype move = Discard of card | Draw 

exception IllegalMove

(* put your solutions for problem 2 here *)

(* Problem 2a *)
fun card_color (card_suit, card_rank) =
    case card_suit of 
        Clubs    => Black
      | Diamonds => Red
      | Hearts   => Red
      | Spades   => Black

(* Problem 2b *)
fun card_value (card_suit, card_rank) = 
    case card_rank of
        Num x => x
      | Ace   => 11
      | _     => 10

(* Problem 2c *)
fun remove_card (cs, c, e) =
    let fun update_cs (new_cs, acc) = 
        case new_cs of
            []     => raise e 
          | x::cs' => if x = c 
                      then acc @ cs'
                      else update_cs (cs', x::acc)
    in update_cs (cs, [])
    end

(* Problem 2d *)
fun all_same_color cs = 
    case cs of 
        []                 => true
      | _::[]              => true
      | head::(neck::rest) => (card_color (head) = card_color (neck)
                               andalso all_same_color (neck::rest))

(* Problem 2e *)
fun sum_cards cs =
    let fun f (cs, acc) =
        case cs of
            []     => acc
          | c::cs' => f(cs', acc + card_value(c))
    in f (cs, 0)
    end

(* Problem 2f *)
fun score (cs, goal) =
    let val sum = sum_cards(cs)
    in
        let fun prelim_score () =
            if sum > goal
            then 3 * (sum - goal)
            else (goal - sum)
        in if all_same_color cs
           then (prelim_score ()) div 2
           else (prelim_score ()) 
        end
    end

(* Problem 2g *)
fun officiate (cs0, moves0, goal) = 
    let fun game (cs, moves, helds) = 
        case (cs, moves) of
            (_, []) => score (helds, goal)
          | (_, (Discard d) :: moves') => game (cs, moves', remove_card (helds, d, IllegalMove))
          | ([], Draw :: moves') => score (helds, goal)
          | (c::cs', Draw :: moves') => let val sum_helds = sum_cards (c::helds)
                                        in
                                            if sum_helds > goal
                                            then score (c::helds, goal)
                                            else game (cs', moves', c::helds)
                                        end
    in 
        game (cs0, moves0, [])
    end
                      