type 'a llist = LNil | LCons of 'a * 'a llist Lazy.t

let rec toLazyList = function
[] -> LNil
| x :: xs -> LCons(x, lazy (toLazyList xs))
;;

let rec ltake = function
(0, _) -> []
| (_, LNil) -> []
| (n, LCons(x, lazy xs)) -> x :: ltake(n-1, xs)
;;

let lrepeat numRepeat list = 
  let rec repeatRecLeft howMany list= 
    match list with 
      LNil -> LNil
      |LCons (elem, lazy tail) -> if howMany <> 1 then 
        LCons (elem, lazy (repeatRecLeft (howMany - 1) list))
      else LCons (elem, lazy (repeatRecLeft numRepeat tail))
  in repeatRecLeft numRepeat list;;


ltake (27, (lrepeat 5 (toLazyList [1; 2; 3])));;

let rec fib =  
  let rec fibRec prev current = 
    LCons (current, lazy (fibRec current (prev + current)))
  in LCons (0, lazy (fibRec 0 1));;

ltake (10, fib);;


type 'a lBT = LEmpty | LNode of 'a * (unit ->'a lBT) * (unit -> 'a lBT);;


let lBreadth tree = 
  let rec breathSearchLazy queue = 
    match queue with 
      [] -> LNil
      |LEmpty::tl -> breathSearchLazy tl
      |(LNode (hd, f1, f2))::tl -> 
        LCons (hd, lazy (breathSearchLazy (tl @ ((f1()) :: (f2()) :: []))))
  in breathSearchLazy [tree]   
;;

ltake (27, (lBreadth 
(LNode 
  (1, 
  (function () -> LNode (2, (function () -> LEmpty), (function () -> LEmpty))), 
  (function () -> LNode(3, (function () -> LEmpty), (function () -> LEmpty)))
  )
)));; 


let rec lTree n = 
  LNode (
    n,
    (function () -> lTree (2*n)),
    (function () -> lTree (2*n))
  );;


ltake (27, (lBreadth (lTree 1)
));; 