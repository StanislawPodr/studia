import scala.annotation.tailrec
sealed trait BT[+A]
case object Empty extends BT[Nothing]
case class Node[+A](elem: A, left: BT[A], right: BT[A]) extends BT[A]


def treeSum (tree: Node[Int]):  Int =
    (tree.left, tree.right) match {
        case (Empty, Empty) => tree.elem
        case (a: Node[Int], b: Node[Int]) => tree.elem + treeSum (a) + treeSum (b)
        case (a: Node[Int], Empty) => tree.elem + treeSum (a)
        case (Empty, b: Node[Int]) => tree.elem + treeSum (b)
    }


val res1 = treeSum (Node(1, Node(2,Node(4,Empty,Empty),Empty),Node(3,Node(5,Empty,Node(6,Empty,Empty)),Empty)))
val res3 = treeSum (Node(1, Node(2,Node(4,Empty,Empty),Empty), Empty))
val res2 = treeSum (Node(-3, Empty, Empty))


object Main {
  def main(args: Array[String]): Unit = {
    println(s"resIs21 = ${res1}")
    println(s"resIs-3 = ${res2}")
    println(s"resIs7 = ${res3}")
  }
}