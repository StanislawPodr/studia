package decoration.decorations;

import java.util.List;

import decoration.IDecoration;

public class Angel implements IDecoration {

    @Override
    public void decorate(List<String> treeRepresentation) {
        if (treeRepresentation == null) {
            throw new IllegalArgumentException("Tree representation can't be null");
        }

        if (treeRepresentation.size() < 1) {
            return;
        }

        if(treeRepresentation.get(0) == null) {
            throw new IllegalArgumentException("Tree element can't be null");
        }

        treeRepresentation.set(0, "A");
    }
    
}
