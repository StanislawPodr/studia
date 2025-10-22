import scala.annotation.tailrec
import scala.math
def fib(num: Int): Int = 
    if num == 0 then 0
    else if num == 1 then 1
    else fib(num - 1) + fib(num - 2)

def fibTail(num: Int): Int = 
    @tailrec
    def fibTailHelper(first: Int, last: Int, counter: Int): Int =
        if counter == 0 then last
        else fibTailHelper(last, first + last, counter - 1)
    fibTailHelper(0, 1, num - 1)


def cubicRoot(a: Double, e: Double) : Double = 
    @tailrec
    def rootRec(last: Double): Double = 
        if (last * last * last - a).abs <= e * a.abs then 
            last
        else rootRec(last + (a/(last*last) - last)/3)
    rootRec( if (a > 1.0) a / 3.0 else a)

def replaceNth[A] (xs: List[A], n: Int, x: A): List[A] = 
    xs match
        case head :: next => if n == 0 then x :: next else head :: replaceNth(next, n - 1, x)
        case Nil => throw new Exception("Index out of bounds")
    
   

object Main {
  def main(args: Array[String]): Unit = {
    //println(s"fibRec = ${fib(42)}")
    println(s"fibTail = ${fibTail(42)}")
    println(s"cubicRoot = ${cubicRoot(-27, math.pow(10, -15))}")
    println(s"replaceNth = ${replaceNth(List(0), 0, 100)}")
  }
}
