package decoration.decorations;

import java.util.List;

import decoration.IDecoration;

public class Lamp implements IDecoration {

    @Override
    public void decorate(List<String> treeRepresentation) {
        Decoration.editEvenWithSymbol(treeRepresentation, 'x');
    }
    
}
