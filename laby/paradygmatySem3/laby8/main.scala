def printAll (args: Any*): Unit = {
    for (arg <- args) 
    {
        if (arg == null) print("Null\n")
        else print(arg.getClass.getSimpleName + ": " + arg + '\n')
    }
}

object main {
    def main(args: Array[String]): Unit = {
        printAll(1, 2, "aloha", Nil);
        printAll();
        printAll(null);
        printAll(null, "k");
  }
}