def concatenate (splitted: List[String], separator: String): String = {
    if splitted == Nil then ""
    else concatenateRecursive (splitted, separator)
}

def concatenateRecursive (splitted: List[String], separator: String): String = {
    if splitted.tail == Nil then splitted.head
    else splitted.head + separator + concatenateRecursive (splitted.tail, separator)
}

object Main {
  def main(args: Array[String]): Unit = {
    println(s"concatenate = ${concatenate(List("a", "b", "c"), "-")}")
    println(s"concatenate = ${concatenate(Nil, "-")}")
    println(s"concatenate = ${concatenate(List(""), "-")}")
    println(s"concatenate = ${concatenate(List("a"), "-")}")
  }
}