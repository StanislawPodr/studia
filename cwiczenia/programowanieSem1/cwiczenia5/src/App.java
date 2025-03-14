public class App {
    public static int[][] multiply(int[][] macierz1, int[][] macierz2) {
        int[][] value = new int[macierz1.length][macierz2[0].length];
        for (int wiersz = 0; wiersz < macierz1.length; wiersz++) {
            for (int kolumna = 0; kolumna < macierz2[0].length; kolumna++) {
                for (int i = 0; i < macierz2.length; i++) {
                    value[wiersz][kolumna] += macierz1[wiersz][i] * macierz2[i][kolumna];
                }
            }
        }

        return value;
    }

    public static void main(String[] args) throws Exception {
        int[][] x1 = { { 3, 1 }, { 2, 1 }, { 1, 0 } };
        int[][] x2 = { { 1, 0, 2 }, { -1, 3, 1 } };
        int[][] result = multiply(x1, x2);
        for (int[] i : result) {
            for (int j : i) {
                System.out.print(j);
                System.out.print(" ");
            }
            System.out.println();
        }
        Tablice tab = new Tablice();
        tab.sortTablica10();
        for (int i : tab.tablica10) {
            System.out.print(i);
        }
    }
}
