import java.util.NoSuchElementException;
import java.util.Scanner;

import decoration.DecorationFactory;
import decoration.DecorationType;
import decoration.IDecoration;
import decoration.NoSuchDecorationExcpetion;

public class Main {

    public static IDecoration selectForDecorationType(String decorationName) throws NoSuchDecorationExcpetion {

        if (decorationName.equals("lamp")) {
            return DecorationFactory.getDecoration(DecorationType.LAMP);
        } else if (decorationName.equals("ornament")) {
            return DecorationFactory.getDecoration(DecorationType.ORNAMENT);
        } else if (decorationName.equals("angel")) {
            return DecorationFactory.getDecoration(DecorationType.ANGEL);
        } else {
            throw new NoSuchDecorationExcpetion();
        }
    }

    public static void createCli() {

        try (Scanner scanner = new Scanner(System.in);) {

            System.out.print("Podaj wysokość choinki: ");
            AbstractTree tree = new PineTree(scanner.nextInt());
            scanner.nextLine();

            System.out.print("Wybierz dekorację (angel, lamp, ornament): ");
            tree.decorate(selectForDecorationType(scanner.nextLine()));

            System.out.println("Choinka:");
            System.out.println(tree);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            System.out.print("Zły argument! Błąd: ");
            System.out.print(e);
        } catch (NoSuchDecorationExcpetion e) {
            System.out.print("Nie ma takiej dekoracji! Błąd: ");
            System.out.print(e);
        }
    }

    public static void main(String[] args) {
        createCli();
    }
}
