import scala.annotation.tailrec

@tailrec
def concatenate (acc: List[Int], first: List[Int]): List[Int] = 
    first match
        case Nil => acc
        case hd::tl => concatenate(hd::acc, tl)


def reverse[A] (list: List[A]): List[A] = 
    @tailrec
    def reverseTailed[A](acc: List[A], tl: List[A]) : List[A] = 
        tl match
            case head::tail => reverseTailed(head::acc, tail)
            case Nil => acc
    reverseTailed (Nil, list)



def mult (first: List[Int], second: List[Int]): List[Int] = {
    @tailrec 
    def multTail (acc: List[Int], first: List[Int], second: List[Int]) : List[Int] = {
    (first, second) match 
        case (Nil, Nil) => acc
        case (_, Nil) => concatenate (acc, first)
        case (Nil, _) => concatenate (acc, second)
        case (hd1::tl1, hd2::tl2) => multTail ((hd1 * hd2)::acc, tl1, tl2)
    }
    reverse(multTail(Nil, first, second))
}



object Main {
  def main(args: Array[String]): Unit = {
    println(s"mult = ${mult(List(1, 2, 3, 5), List(6, 5, 6))}")
    println(s"mult = ${mult(List(), List())}")
    println(s"mult = ${mult(List(7), List(6, 5, 6))}")
    println(s"mult = ${mult(List(), List(6, 5, 6))}")
    println(s"mult = ${mult(List(1, 2, 3, 5), List())}")
  }
}