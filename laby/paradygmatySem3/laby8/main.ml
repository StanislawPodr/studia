type 'a llist = LNil | LCons of 'a * (unit -> 'a llist)

let rec ltake (n, lxs) =
match (n, lxs) with
(0, _) -> []
| (_, LNil) -> []
| (n, LCons(x,xf)) -> x::ltake(n-1, xf())
;;

let rec toLazyList xs =
match xs with
[] -> LNil
| h::t -> LCons(h, function () -> toLazyList t);;

let lazyFib n = 
  if n < 0 then 
    raise (Invalid_argument "n can't be less than 0")
  else if n = 0 then LNil
  else if n = 1 then LCons (0, fun () -> LNil)
  else let rec fib howManyMore current previous = 
    if current < 0 then raise (Invalid_argument "n is too big, int overflow") 
    else if howManyMore > 1 then 
      LCons (
        current, 
        fun () -> fib (howManyMore - 1) (previous + current) current
        )
    else LCons (
      current,
      fun () -> LNil
    )
  in LCons (0 , fun() -> fib (n - 1) 1 0);;



ltake (15, (lazyFib 0));;
ltake (15, (lazyFib 1));;
ltake (15, (lazyFib 2));;
ltake (15, (lazyFib 3));;
ltake (15, (lazyFib 10));;
ltake (15, (lazyFib (-10)));;
ltake (10000000, (lazyFib (100000000)));;