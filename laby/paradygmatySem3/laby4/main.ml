let filterContaining listOfLists param = 
  let rec contains = function 
    [] -> false
    |hd::tl -> if hd = param then true else contains tl
in List.filter contains listOfLists;;

let binToDecimal list = 
  let rec recBinToDecimal acc = function
    [] -> acc
    |hd::tl -> recBinToDecimal (acc * 2 + hd) tl
  in recBinToDecimal 0 list;;

let binToDecimal2 list = 
  List.fold_left (fun acc element -> acc * 2 + element) 0 list;;

let merge4tupleString list = 
  List.map (fun (a, b, c, d) -> a ^ b ^ c ^ d) list;;
  

filterContaining  [[1;2;3];[3;4];[5;6]] 3;;
filterContaining  [[1;2];[4];[5;6]] 3;;
filterContaining  [] 3;;
filterContaining  [[1;2;3]] 3;;

binToDecimal [1; 1; 1; 1; 1; 1; 1; 1];;
binToDecimal [0];;
binToDecimal [1; 1; 1; 1; 1; 1; 1; 0];;
binToDecimal [0; 1; 1; 0];;

binToDecimal2 [1; 1; 1; 1; 1; 1; 1; 1];;
binToDecimal2 [0];;
binToDecimal2 [1; 1; 1; 1; 1; 1; 1; 0];;
binToDecimal2 [0; 1; 1; 0];;

merge4tupleString [("ala","ma","kot","a");("kot","nie","ma","ali")];;
merge4tupleString [("ala"," ma ","","kota");("kot",""," ma ","ale")];;
merge4tupleString [];;
merge4tupleString [("ala","ma","kot","a")];;