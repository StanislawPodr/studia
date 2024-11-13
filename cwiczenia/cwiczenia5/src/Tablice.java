import java.util.Random;

public class Tablice {
    public final int[] tablica10 = new int[10];
    public final int[][] tablica10x20 = new int[10][20];
    public final int[][] tabliceJednowymiarowe10 = new int[2][10];
    public final int[][] losowaTablica = new int[10][];
    private Random rand = new Random();

    public Tablice() {
        randTab10(tablica10, rand);
        for (int i = 0; i < tablica10x20.length; i++) {
            randTab10(tablica10x20[i], rand);
        }
        randTab10(tabliceJednowymiarowe10[0], rand);
        randTab10(tabliceJednowymiarowe10[1], rand);
        for(int i = 0; i < losowaTablica.length; i++)
        {
            int [] kolumnyLosowejTablicy = new int [rand.nextInt(10) + 1];
            randTab10(kolumnyLosowejTablicy, rand);
            losowaTablica[i] = kolumnyLosowejTablicy;
        }   
    }

    public void sortLosowaTablica() {
        for(int [] i : losowaTablica) {
            bubbleSort(i);
        }
    }

    public static void bubbleSort(int[] tab) {
        for (int i = 1; i < tab.length; i++) {
            for (int j = 0; j < tab.length - i; j++) {
                if (tab[j] > tab[j + 1]) {
                    int temp = tab[j];
                    tab[j] = tab[j + 1];
                    tab[j + 1] = temp;
                }
            }
        }
    }

    public void sortTablica10() {
        bubbleSort(tablica10);
    }

    public static void randTab10(int[] tab, Random rand) {
        for (int i = 0; i < tab.length; i++) {
            tab[i] = rand.nextInt(10) + 1;
        }
    }

    public static void randTab10(int[] tab) {
        Random rand = new Random();
        for (int i = 0; i < tab.length; i++) {
            tab[i] = rand.nextInt(10) + 1;
        }
    }

    public void sortWierszeTablicy10x20() {
        for (int[] i : tablica10x20) {
            bubbleSort(i);
        }
    }

    public void sortkolumnyTablicy10x20() {
        int[][] temptab = new int[20][10];
        for (int i = 0; i < 20; i++) {
            // transpozycja tej macierzy
            for (int j = 0; j < 10; j++) {
                temptab[i][j] = tablica10x20[j][i];
            }
        }

        for (int[] i : temptab) {
            bubbleSort(i);
        }
    }

    public int sprawdzPodzielnoscPrzez3WartosciDlaTablicLosowych() {
        return sprawdzPodzielnoscPrzez3Wartosci(tabliceJednowymiarowe10[0], tabliceJednowymiarowe10[1]);
    }

    public static int sprawdzPodzielnoscPrzez3Wartosci(int[] tab1, int[] tab2) {
        int ile = 0;
        for (int i : tab1) {
            int ileDzielnikow = 0;
            for (int j : tab2) {
                if (i % j == 0) {
                    ileDzielnikow++;
                }
            }
            if (ileDzielnikow >= 3) {
                ile++;
            }
        }
        return ile;
    }
}
