let rec rangeRec (max, min) = 
    if min != max then 
       min :: rangeRec (max, min + 1)
    else [max]

let range (a, b) = 
    if a > b then 
        rangeRec (a, b)
    else rangeRec (b, a)


let rec toOne x = 
  if x < 1 then []
  else x :: (toOne (x - 1))
  

let rec multRec list = 
  if list = [] then 1 
  else (List.hd list) * multRec (List.tl list)

let mult list = 
  if list = [] then 0
  else multRec list




let () = 
  print_endline "range";
  List.iter print_int (range (4, 10));
  print_endline "\nrange";
  List.iter print_int (range (4, 4));
  print_endline "\nrange";
   List.iter print_int (range (-10, -15));
  print_endline "\ntoOne";
  List.iter print_int (toOne 5);
  print_endline "\ntoOne";
  List.iter print_int (toOne 1);
  print_endline "\ntoOne";
  List.iter print_int (toOne (-50));
   print_endline "\ntoOne";
  List.iter print_int (toOne (0));
  print_endline "\nmult";
  print_int (mult [2; -2; -2]);
  print_endline "\nmult";
  print_int (mult [2; 2; 0]);
  print_endline "\nmult";
  print_int (mult []);
  print_endline "";