type graph = (int * int list) list;;

let rec findInGraphList list arg =
  match list with
  [] -> None
  |(x, toReach)::tl -> if x = arg then (Some (x, toReach)) else findInGraphList tl arg;;

let reachable_graph g verticle = 
  let rec reach verticle reached = 
    let isIn = findInGraphList g verticle in
    match isIn with 
      None -> reached
      |Some (x, toReach) -> List.fold_left (fun acc arg -> if List.mem arg acc then acc else reach arg acc) (x::reached) toReach
  in List.fold_left (fun acc x -> acc + 1) 0 (reach verticle []) ;;



let g = [(1, [2;3]);(2, [4]);(3, [4;5]);(4, []);(5, [4])];;


reachable_graph g 1;;
reachable_graph g 3;;
reachable_graph g 4;;



type 'a tree =
| Empty
| Node of 'a * 'a tree * 'a tree;;

let tree_stats tree = 
  let rec depthInspect node numberOfNodes maxHeight = 
    match node with 
      Empty -> (numberOfNodes, maxHeight)
      |Node (_, left, right) -> 
        let (leftNum, leftHeihgt) = depthInspect left numberOfNodes maxHeight and 
        (rightNum, rightHeight) = depthInspect right numberOfNodes maxHeight in
        (leftNum + rightNum + 1, (max leftHeihgt rightHeight) + 1)
  in depthInspect tree 0 0;;


let tt = Node(1,Node(2,Node(4,Empty,Empty),Empty),Node(3,Node(5,Empty,Node(6,Empty,Empty)),Empty));;

tree_stats tt;;
tree_stats (Node(10, Empty, Empty));;
tree_stats (Node(1,Node(2,Empty,Empty),Node(3,Node(5,Empty,Empty),Empty)));;
tree_stats Empty;;