import java.util.Random;

public class Tablice {
    public final int[] tablica10 = new int[10];
    public final int[][] tablica10x20 = new int[10][20];
    private Random rand = new Random();

    public Tablice() {
        for (int i = 0; i < tablica10.length; i++) {
            tablica10[i] = rand.nextInt(10) + 1;
        }

        for (int i = 0; i < tablica10x20.length; i++) {
            for (int j = 0; j < tablica10x20[i].length; j++) {
                tablica10x20[i][j] = rand.nextInt(10) + 1;
            }
        }
    }

    public void bubbleSort(int[] tab) {
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

    public void sortWierszeTablicy10x20() {
        for (int [] i : )
    }
}
