public class Macierz {
    private final int[][] macierzKlasy;
    public final int wiersze;
    public final int kolumny;

    public Macierz(int[][] macierz) throws Exception {
        macierzKlasy = macierz.clone();
        this.wiersze = macierz.length;
        int temp = macierz[0].length;
        for (int[] i : macierz) {
            if (i.length != temp) {
                throw new Exception("Macierz musi mieć wymiar!");
            }
        }
        this.kolumny = temp;
    }

    public int[][] getMatrix() {
        return macierzKlasy.clone();
    }

    public int getValue(int wiersz, int kolumna) {
        return macierzKlasy[wiersz - 1][kolumna - 1];
    }

    public Macierz dodaj(Macierz add) throws Exception {
        int[][] value = new int[wiersze][kolumny];
        if (add.wiersze != wiersze && add.kolumny != kolumny) {
            throw new Exception("Aby dodawać, macierze muszą mieć taki sam wymiar");
        }

        for (int wiersz = 0; wiersz < wiersze; wiersz++) {
            for (int kolumna = 0; kolumna < kolumny; kolumna++) {
                value[wiersz][kolumna] = macierzKlasy[wiersz][kolumna] + add.getValue(wiersz + 1, kolumna + 1);
            }
        }

        return new Macierz(value);
    }

    public Macierz multiply(Macierz multiply) throws Exception {
        if (kolumny != multiply.wiersze) {
            throw new Exception("Nie można mnożyć tych macierzy");
        }

        int[][] value = new int[wiersze][multiply.kolumny];
        for (int wiersz = 0; wiersz < wiersze; wiersz++) {
            for (int kolumna = 0; kolumna < multiply.kolumny; kolumna++) {
                for (int i = 0; i < kolumny; i++) {
                    value[wiersz][kolumna] += macierzKlasy[wiersz][i] * multiply.getValue(i + 1, kolumna + 1);
                }
            }
        }

        return new Macierz(value);
    }

    public void show() {
        for(int [] i : macierzKlasy) {
            for(int j : i) {
                System.out.print(j);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
