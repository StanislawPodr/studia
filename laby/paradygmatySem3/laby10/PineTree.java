import java.util.ArrayList;
import java.util.List;

import decoration.IDecoration;

public class PineTree extends AbstractTree {

    private List<String> tree;

    public PineTree(int height) {
        generateTree(height);
    }

    @Override
    public void generateTree(int height) {
        if (height < 0) {
            throw new IllegalArgumentException("Height can't be negative");
        }

        tree = new ArrayList<>(height);
        for (int level = 0; level < height; level++) {
            tree.add("*".repeat(level * 2 + 1)); // z każdym kolejnym poziomem liczba
            // gwiazdek musi wzrosnac o dwa
        }
    }

    @Override
    public void decorate(IDecoration decoration) {
       decoration.decorate(tree);
    }

    @Override 
    public String toString() {
        String result = "";
        for (int level = 0; level < tree.size(); level++) {
            result += " ".repeat(tree.size() - level - 1) + tree.get(level) + "\n";
        }
        return result;
    }
    
}
