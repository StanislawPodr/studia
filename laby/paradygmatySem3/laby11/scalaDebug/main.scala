trait Debug {
  def debugVars(): Unit = {
    val dbg = this.getClass()

    printf("class name: %s\n", dbg.getCanonicalName())

    val fields = dbg.getDeclaredFields()
    for (field <- fields) {
      field.setAccessible(true)
      printf(
        "field %s, %s\n",
        field,
        field.get(this)
      )
    }

  }
}

class Point(xv: Int, yv: Int) extends Debug {
  var x: Int = xv
  var y: Int = yv
  var a: String = "test"
}

@main def main() = {
  var p: Point = new Point(3, 4)
  p.debugVars()
  object A extends Debug {
    val A : Any = null
  }
  A.debugVars()
  case object B extends Debug
  B.debugVars()
}
