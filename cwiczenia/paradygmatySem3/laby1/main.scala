def flattenRecursive[T] (x: List[List[T]], y: List[T]) : List[T] = {
    if x.isEmpty then y
    else flattenRecursive (x.tail, y ::: x.head)
}

def flatten1[T] (x : List[List[T]]) : List[T] = {
    if x == Nil then 
    throw new Exception("empty list")
    else flattenRecursive(x, Nil)
}

def count [A] (x: A, xs: List[A]) : Int = {
  if x == Nil || xs == Nil then
    throw new Exception("wrong arguments")
  else countRecursive(x, xs)
}

def countRecursive [A] (x: A, xs: List[A]) : Int = {
  if xs == List.empty then 0
  else if xs.head == x then 
     1 + countRecursive(x, xs.tail)
  else countRecursive(x, xs.tail)
}

def replicate[A](x: A, n: Int): List[A] = {
  if x == Nil || n < 0 then 
    throw new Exception("Wrong arguments")
  else replicateRecursive(x, n, Nil)
}

def replicateRecursive[A](x: A, n: Int, list: List[A] ) : List[A] = {
  if n == 0 then 
  return list
  return replicateRecursive(x, n - 1, x :: list)
}
  
def sqrList(xs: List[Int]) : List[Int] = {
  if xs == List.empty then
  return Nil
  return (xs.head * xs.head) :: sqrList(xs.tail) 
}

def palindrome[A](xs: List[A]) : Boolean = {
  if xs == xs.reverse then 
  return true 
  else 
  return false
}

def listLength[A](xs: List[A]) : Int = {
  if xs == Nil then
  return 0
  return 1 + listLength (xs.tail)
}


object Main {
  def main(args: Array[String]): Unit = {
    val list = List(List(1, 2, 3, 3), List(4, 5), List(1, 2))
    val list2 = List(3,3,1,3,2,2,1,1,3)
    println(s"flatten = ${flatten1(list)}")
    println(s"count = ${countRecursive(3, list2)}")
    println(s"replicate = ${replicate(1, 10)}")
    println(s"sqrList = ${sqrList(List(1, 2, 4))}")
    println(s"isPalindrome = ${palindrome(List(Nil))}")
    println(s"listLength = ${listLength(List(1, 2, 3))}")
  }
}
