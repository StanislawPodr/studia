type 'a llist = LNil | LCons of 'a * (unit -> 'a llist);;

let lhd = function 
  LNil -> failwith "lhd"
  |LCons (x, _) -> x;;

let ltl = function 
  LNil -> failwith "ltl"
  |LCons (_, func) -> func();;

let rec lfrom k = LCons (k, function () -> lfrom (k+1));;

let rec ltake (n, lxs) =
match (n, lxs) with
(0, _) -> []
| (_, LNil) -> []
| (n, LCons(x,xf)) -> x::ltake(n-1, xf())
;;


let ldivide lazyList = 
  let rec getNextNext = function
    LNil -> LNil
    |LCons (res, f) -> 
      match f() with
        LNil -> LCons(res, function () -> LNil)
        |LCons (_, nxt) -> LCons (res, function () -> getNextNext (nxt()))
  in match lazyList with 
    LNil -> (LNil, LNil)
    |LCons (first, f) -> (getNextNext lazyList, getNextNext (f()))
;;



ltake (10, (let (notEven, _) = ldivide (lfrom 1) in notEven));;
ldivide LNil;;
ldivide (LCons (0, function () -> LNil));;
