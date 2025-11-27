def lazyArithmetic [A] (list1: LazyList[A], list2: LazyList[A], 
operation: (A,A) => A): LazyList[A] = 
    (list1, list2) match {
        case (LazyList(), LazyList()) => LazyList()
        case (first#::tl, LazyList()) => first#::lazyArithmetic(tl, LazyList(), operation)
        case (LazyList(), second#::tl) => second#::lazyArithmetic(LazyList(), tl, operation)
        case (first#::tl1, second#::tl2) => operation(first, second) #:: lazyArithmetic(tl1, tl2, operation)
        case _ => throw new IllegalArgumentException
    }






object Main {
  def main(args: Array[String]): Unit = {
    println(s"testAdd = ${lazyArithmetic(LazyList(1, 2, 3, 4), LazyList(1, 2), (a: Int, b: Int)=> {a + b}).force}")
    println(s"testMult = ${lazyArithmetic(LazyList(1, 2), LazyList(-1, 2, 3, 4), (a: Int, b: Int)=> {a * b}).force}")
    println(s"testDivErr = ${lazyArithmetic(LazyList(0), LazyList(0, 2), (a: Int, b: Int)=> {a / b}).force}")
  }
}