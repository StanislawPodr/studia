let rec initSegment (list, segment) = 
    match (list, segment) with 
        |(_, []) -> true
        |([], _) -> false
        |(a::b, c::d) when a = c -> initSegment (b, d)
        | _ -> false


let () = 
    print_endline (string_of_bool (initSegment ([1; 2; 3; 4], [1; 2; 1])))