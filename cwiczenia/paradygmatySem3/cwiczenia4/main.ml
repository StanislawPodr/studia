type 'a bt = Empty | Node of 'a * 'a bt * 'a bt;;

let tt = Node(1,Node(2,Node(4,Empty,Empty),Empty),Node(3,Node(5,Empty,Node(6,Empty,Empty)),Empty));;

let breadthBT tree = 
  let rec breadth acc queue = match queue with
  []->List.rev acc
  |Empty::tl -> breadth acc tl
  |(Node (a, b, c))::tl -> breadth (a::acc) (tl @ (b::c::[]))
in breadth [] [tree];;

breadthBT tt;;


type 'a graph = Graph of ('a -> 'a list);;

let g = Graph
(function
0 -> [3]
| 1 -> [0;2;4]
| 2 -> [1]
| 3 -> []
| 4 -> [0;2]
| n -> failwith ("Graph g: node "^string_of_int n^" doesn't exist")
);;

let depthSearch (Graph graph) startingPoint = 
  let rec depth visited startingPoint = 
    List.fold_left 
    (fun vis elem -> 
      if List.mem elem vis then vis
      else depth (elem::vis) elem
    )
    visited
    (graph startingPoint)
  in List.rev(depth [startingPoint] startingPoint);;
 



  
depthSearch g 0;;
depthSearch g 4;;
let Graph h = g in h 4;;