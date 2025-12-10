let insert sorted num =
  let rec tailInsert acc sorted = 
    match sorted with 
      hd::tl -> if hd < num then 
        tailInsert (hd::acc) tl else List.rev_append (hd::num::acc) tl
      |[] -> List.rev (num::acc)
  in tailInsert [] sorted;;


insert [1;3;5;7] 2;;
insert [1;3;5;7] 15;;
insert [1;3;5;7] 0;;
insert [] (-10);;

let removeDuplicates list num = 
  let rec removeNext acc list = 
    match list with 
      hd::tl -> if hd = num then removeNext acc tl 
        else removeNext (hd::acc) tl
      |[] -> acc 
  in let rec findFirst acc list = 
    match list with 
      hd::tl -> if hd = num then removeNext (hd::acc) tl 
        else findFirst (hd::acc) tl
      |[] -> acc
  in List.rev (findFirst [] list);;


removeDuplicates [1; 2; 2; 2; 3; 1; 4; 2] 2;;
removeDuplicates [] 0;;
removeDuplicates [1] 1;;
