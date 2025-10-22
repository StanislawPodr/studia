import scala.annotation.tailrec

def count[A](list: List[A]) : Int =
    @tailrec
    def countRec[A](l: List[A], counter: Int): Int = 
        l match
            case Nil => counter
            case _::tail => countRec(tail, counter + 1)
    countRec(list, 0)
        





object Main {
  def main(args: Array[String]): Unit = {
    println(s"count = ${count(List("a", "b", "c", "d"))}")
    println(s"count = ${count(List())}")
    println(s"count = ${count(List(0))}")
  }
}