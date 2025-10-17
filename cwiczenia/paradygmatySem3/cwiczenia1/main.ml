let rec flatten xList = 
    if xList = [] then []
    else (List.hd xList) @ flatten (List.tl xList)

let rec count (x, list) = 
    if list = [] then 0
    else if x = List.hd list then
        1 + (count (x, (List.tl list)))
    else count (x, List.tl list)

let third (a, b, c) = c

let rec rangeRec (max, min) = 
    if min != max then 
       min :: rangeRec (max, min + 1)
    else [max]

let range (a, b) = 
    if a > b then 
        rangeRec (a, b)
    else rangeRec (b, a)

let rec sum list = 
    if list != [] then 
        List.hd list + sum (List.tl list)
    else 0 

let rec eachSmaller (list, number) = 
    if list = [] then 
        true
    else if (List.hd list) < number then
        eachSmaller ((List.tl list), number)
    else false


let () = 
    print_endline (String.concat "," (flatten [["a"]; ["a";"b"]]));
    print_endline (string_of_int (count (10, [10;10;1;2;10])));
    print_endline (third ("a", "b", "c"));
    List.iter print_int (range (4, 10));
    print_endline "";
    print_int (sum [1; 2; 1]);
    print_endline "";
    print_endline (string_of_bool (eachSmaller ([1; 2; 3], 0)));

