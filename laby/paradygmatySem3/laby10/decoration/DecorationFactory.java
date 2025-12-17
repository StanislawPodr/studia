package decoration;

import decoration.decorations.Angel;
import decoration.decorations.Lamp;
import decoration.decorations.Ornament;

public class DecorationFactory {
    public static IDecoration getDecoration(DecorationType decorationName) throws NoSuchDecorationExcpetion {
        if (decorationName == DecorationType.LAMP) {
            return new Lamp();
        } else if (decorationName == DecorationType.ORNAMENT) {
            return new Ornament();
        } else if (decorationName == DecorationType.ANGEL) {
            return new Angel();
        } else {
            throw new NoSuchDecorationExcpetion();
        }
    }
}
