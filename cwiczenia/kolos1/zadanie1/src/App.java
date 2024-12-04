import java.util.Random;

public class App {
    public static void genRandomPictogram(int [][] piktogram) {
        Random rand = new Random();
        int M = piktogram.length;
        int N = piktogram[0].length;
        for(int m = 0; m<M; m++) {
            for(int n = 0; n<N; n++) {
                piktogram[m][n] = rand.nextInt(256);
            }
        }
    }

    public static int[] rozkladWartosci(int [][] piktogram) {
        int [] wartosci = new int [256];
        for(int [] m: piktogram) {
            for(int n: m) {
                wartosci[n] +=1;
            }
        }
        return wartosci;
    }

    //warość o maksymalnej liczności
    //wszystkie wartości o licznościach powyżej T


    public static int [] maksymalnaLicznosc(int [] rozkladWartosci) { //bez przyjmowania piktogramu
        int [] wartosc = rozkladWartosci;
        int maks = wartosc[0];
        int indexMaksa = 0;
        int ile_maksow = 1;
        for(int i = 0; i<wartosc.length; i++) {
            if(wartosc[i]>maks) {
                maks = wartosc[i];
                indexMaksa = i;
                ile_maksow = 1;
            }
            else if(wartosc[i] == maks) {
                ile_maksow++;
            }
        }

        int [] indexMaksow = new int [ile_maksow+1];
        int j = 1;
        for(int i = 0; i<wartosc.length; i++) {
            if(wartosc[i]==maks) {
                indexMaksow[j] = i;
            }
        }
        indexMaksow[0] = maks; //ta tablica w 0 indeksie to po prostu wartość tej liczności

        return indexMaksow;
    }

    public static int [] maksymalnaLicznosc(int [][] piktogram) {
        int [] wartosc = rozkladWartosci(piktogram);
        int maks = wartosc[0];
        int indexMaksa = 0;
        int ile_maksow = 1;
        for(int i = 0; i<wartosc.length; i++) {
            if(wartosc[i]>maks) {
                maks = wartosc[i];
                indexMaksa = i;
                ile_maksow = 1;
            }
            else if(wartosc[i] == maks) {
                ile_maksow++;
            }
        }

        int [] indexMaksow = new int [ile_maksow+1];
        int j = 1;
        for(int i = 0; i<wartosc.length; i++) {
            if(wartosc[i]==maks) {
                indexMaksow[j] = i;
            }
        }
        indexMaksow[0] = maks; //ta tablica w 0 indeksie to po prostu wartość tej liczności

        return indexMaksow;
    }

    // public void warosciOLicznosciachPowyzejT(int T, int [][] piktogram) {
    //     int [] rozkladWartosci = rozkladWartosci(piktogram);
    //     int [] maksymalnaWartosc = maksymalnaLicznosc(rozkladWartosci);
    //     while(maksymalnaWartosc > T){
    //         for(int i = 1; i<maksymalnaWartosc.length; i++) 
    //         {

    //         }
    //     }

    // } no tego nie zdąrzę dokończyc więc biorę się za 2

    public static void main(String[] args) throws Exception {
        int M = 550;
        int N = 650;
        int[][] piktogram = new int[M][N];
        genRandomPictogram(piktogram);
        int [] k = rozkladWartosci(piktogram);
    }
}
