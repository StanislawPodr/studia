import java.util.Iterator;

public class IteratorTablicy<T> implements Iterator<T> {
    private T[][] tablica;
    private boolean kierunek; // true do przodu, false do tylu
    private int wiersz;
    private int kolumna;
    T wynik;

    public IteratorTablicy(T[][] tablica, boolean kierunek) {
        this.tablica = tablica;
        this.kierunek = kierunek;
        if (kierunek) {
            wiersz = 0;
            kolumna = 0;
        } else {
            wiersz = tablica.length - 1;
            kolumna = tablica[wiersz].length - 1;
        }
    }

    @Override
    public boolean hasNext() {
       try {
            wynik = tablica[wiersz][kolumna];
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public T next() {
        if (hasNext()) {
            if (kierunek) {
                if (kolumna < tablica[wiersz].length - 1) {
                    kolumna++;
                } else {
                    wiersz++;
                    kolumna = 0;
                }
            } else {
                if (kolumna > 0) {
                    kolumna--;
                } else {
                    wiersz--;
                    kolumna = tablica[wiersz].length - 1;
                }
            }
            return wynik;
        }
        throw new java.util.NoSuchElementException();
    }

}
