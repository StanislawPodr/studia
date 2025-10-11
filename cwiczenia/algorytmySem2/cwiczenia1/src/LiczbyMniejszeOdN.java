import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class LiczbyMniejszeOdN implements Iterator<Integer> {
    private int next;
    List<Integer> liczbyPierwsze;

    public static List<Integer> znajdzLiczbyPierwsze(int n) {
        if (n < 2)
            return Collections.emptyList();

        boolean[] pierwsze = new boolean[n];
        Arrays.fill(pierwsze, true);
        pierwsze[0] = pierwsze[1] = false;

        for (int i = 2; i * i < n; i++) {
            if (pierwsze[i]) {
                for (int j = i * i; j < n; j += i) {
                    pierwsze[j] = false;
                }
            }
        }

        List<Integer> wynik = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            if (pierwsze[i]) {
                wynik.add(i);
            }
        }
        return wynik;
    }

    public LiczbyMniejszeOdN(int n) {
        liczbyPierwsze = znajdzLiczbyPierwsze(n);
    }

    @Override
    public boolean hasNext() {
        if(next < liczbyPierwsze.size()) {
            return true;
        }
        return false;
    }

    @Override
    public Integer next() {
        if(hasNext()) {
            return liczbyPierwsze.get(next++);
        }
        throw new java.util.NoSuchElementException();
    }

}
