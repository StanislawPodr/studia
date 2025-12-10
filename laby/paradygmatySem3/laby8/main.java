import java.util.ArrayList;

class Main {
	public static void main(String ...args) {
        printAll(1, 2, "aloha", new ArrayList<>());
        printAll();
        printAll(null);
        printAll(null, "k");
    }

    private static void printAll(Object ...args) {

        if (args == null) {
                System.out.println("Null");
                return;
        }

        for (Object arg : args) {
            if (arg == null) System.out.print("Null\n");
            else System.out.print(arg.getClass().getSimpleName() + ": " + arg + '\n');
        }
    }
}