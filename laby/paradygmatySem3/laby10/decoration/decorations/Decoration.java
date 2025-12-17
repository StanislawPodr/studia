package decoration.decorations;

import java.util.List;

public class Decoration {
    public static void editEvenWithSymbol(List<String> treeRep, char decorSymbol) {
        if (treeRep == null) {
            throw new IllegalArgumentException("Tree representation can't be null\n");
        }

        for (int level = 0; level < treeRep.size(); level++) {

            String lvlStr = treeRep.get(level);
            if (lvlStr == null) {
                throw new IllegalArgumentException("Tree level can't be null\n");
            }

            char levelChars[] = lvlStr.toCharArray();
            for (int even = 0; even < lvlStr.length(); even += 2) {
                levelChars[even] = decorSymbol;
            }

            treeRep.set(level, new String(levelChars));
        }
    }
}
