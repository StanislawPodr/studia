let reverseTail (list) = 
  let rec reverse (accu, tail) = 
    match (accu, tail) with 
      |(_, []) -> accu
      |(a, head::tl) -> reverse(head::a, tl) in
  reverse([], list)

let rec concatenate(a, b) = 
  match (a, b) with 
    |([], _) -> b
    |(_, []) -> a
    |(ha::ta, hb::tb) -> ha::hb::concatenate(ta, tb)



let divide list =
  let rec divideRec(list, by10, by5, rest) = 
    match  (list, by10, by5, rest) with 
      |([], a, b, c) -> (a, b, c)
      |(head::tail, a, b, c) -> 
        if (head mod 10) = 0 then divideRec(tail, head::a, head::b, c) 
        else if (head mod 5) = 0 then divideRec(tail, head::a, b, c) 
        else divideRec(tail, a, b, head::c) 
  in divideRec(reverseTail(list), [], [], [])
    

let () = 
  print_endline "reverseTail";
  List.iter print_int (concatenate ([1;3;5], [2;4]));
  print_char ' '; List.iter print_int (concatenate ([], [2;4]));
  print_char ' '; List.iter print_int (concatenate ([], [])); print_string "nic";
  print_char ' '; List.iter print_int (concatenate ([1;2], []));
  print_char '\n';
  print_endline "divide";
  match divide [20;21;25;30;40] with 
    |(a, b, c) -> print_char ' '; List.iter print_int a; print_char ' '; List.iter print_int b; print_char ' '; List.iter print_int c; 
  print_char '\n';
  match divide [] with 
    |(a, b, c) -> print_char ' '; List.iter print_int a; print_char ' '; List.iter print_int b; print_char ' '; List.iter print_int c; 
  print_char '\n';
    match divide [21] with 
    |(a, b, c) -> print_char ' '; List.iter print_int a; print_char ' '; List.iter print_int b; print_char ' '; List.iter print_int c; 
  print_char '\n';
  