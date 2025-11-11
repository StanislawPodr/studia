let insert predicate value list = 
  let rec insertRec acc = function 
    [] -> List.rev (value :: acc)
    |hd::tl -> if predicate value hd then List.rev_append  tl (hd::value::acc)
    else insertRec (hd::acc) tl 
    in List.rev (insertRec [] list);; (*till this predicate is false*)

let insert_le elem = insert (<=) elem;;
insert_le 4 [1;2;3;4;5;6;7;8];;


let insertionSort predicate list = 
    List.fold_left (fun acc item -> insert predicate item acc) [] list;;


insertionSort (<=) [10; 5; 2; 8; 4];;


let merge predicate list1 list2= 
  let rec recMerge acc = function 
    ([], []) -> acc
    |([], l2) -> List.rev_append l2 acc
    |(l1, []) -> List.rev_append l1 acc
    |(hd1::tl1, hd2::tl2) -> if predicate hd1 hd2 then recMerge (hd1::acc) (tl1, hd2::tl2) else recMerge (hd2::acc) (hd1::tl1, tl2)
in List.rev (recMerge [] (list1, list2));;


merge (<=) [10] [5];;
merge (<=) [2] [8];;
merge (<=) [4] [2;8];;
merge (<=) [5;10] [2;4;8];;


let mergeSort predicate list = 
  if list = [] then [] else
  let singleLists = List.map (fun item -> [item]) list in
  let rec mergeAll acc = function 
    [] -> 
        (match acc with 
        [x] -> x 
        | _ -> mergeAll [] acc)
    |[x] -> mergeAll [] (x::acc)
    |fs::sc::tl -> mergeAll ((merge predicate fs sc)::acc) tl
  in mergeAll [] singleLists ;;


mergeSort (<=) [10; 5; 2; 8; 4;];;
let curry3 f a b c = 
        f (a, b, c);;


let uncurry3 f (a, b, c) = 
        f a b c;;


let rec sumProd xs =
  let apply acc arg = 
    (fst acc + arg, fst acc * arg)
  in List.fold_left apply (0, 1) xs
;;

