type code = Elem of bool | Not | And | Or | Xor 

let eval arg = 
  if arg = [] then raise (invalid_arg "Empty list")
  else let rec evalRec elemLst stack = 
    match (stack, elemLst) with 
      ((Elem hd):: tl, _)-> evalRec (hd::elemLst) tl
      |((Not) :: tl, a::b) -> evalRec ((not a)::b) tl 
      |((And) :: tl, a::b::c) -> evalRec ((a && b)::c) tl
      |((Or) :: tl, a::b::c) -> evalRec ((a || b)::c) tl
      |((Xor) :: tl, a::b::c) -> evalRec ((a <> b)::c) tl
      |([], [x]) -> x
      |_ -> raise (invalid_arg "wrong stack")
  in evalRec [] arg;;


eval [Elem true; Elem false; And; Not];;
eval [Elem true; Elem false; Elem true; Not; And; Not; And];;
eval [Elem true; Elem false; And;];;
eval [Elem true; Elem false; And; And];;


      
          
      
    