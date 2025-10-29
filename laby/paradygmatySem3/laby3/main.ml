let reverse list = 
  let rec reverseTailed (accu, tail) = 
    match tail with
    |[] -> accu
    |head::tail -> reverseTailed(head::accu, tail)
  in reverseTailed ([], list)


let divide (list, divider) = 
  let reversed = reverse list in
  let rec tailDivide (acc, tail) = 
    match tail with
      |[] -> acc
      |head::tl -> let (higher, lower) = acc in 
      if head > divider then tailDivide((head::higher, lower), tl)
      else if head < divider then tailDivide((higher, head::lower), tl)
      else tailDivide(acc, tl)
    in tailDivide(([], []), reversed)


let () = 
    match divide ([5; 4; 3; 2; 1], 3) with
      |(a, b) -> List.iter print_int a; List.iter print_int (b);

    match divide ([], 3) with
      |(a, b) -> List.iter print_int a; List.iter print_int (b);

    match divide ([5; 5; 5; 5; 5], 5) with
      |(a, b) -> List.iter print_int a; List.iter print_int (b);
  ;;
