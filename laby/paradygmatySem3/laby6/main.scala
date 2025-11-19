import scala.annotation.tailrec
sealed trait Instr
case class Push(x: Int) extends Instr
case object Pop extends Instr
case object Inc extends Instr

type Stack = List[Int]


def eval(inst: List[Instr]): Stack =
    @tailrec
    def stackEval (inst: List[Instr], stack: Stack): Stack =
        (inst, stack) match {
            case (Nil, _) => stack
            case (Push(x)::tl, _) => stackEval (tl, x::stack)
            case (Pop::tl, hd::rest) => stackEval (tl, rest)
            case (Inc::tl, hd::rest) => stackEval (tl, (hd + 1)::rest)
            case _ => throw new IllegalArgumentException
        }
    stackEval(inst, List())


object Main {
  def main(args: Array[String]): Unit = {
    println(s"testPush = ${eval(List(Push(1), Push(2), Push(3)))}")
    println(s"testIncrement = ${eval(List(Push(1), Push(2), Push(3), Inc, Inc))}")
    println(s"testPop = ${eval(List(Push(1), Push(2), Push(3), Inc, Inc, Pop, Pop, Inc))}")
    println(s"testEmpty = ${eval(List())}")
    println(s"testIllegal = ${eval(List(Pop))}")
  }
}