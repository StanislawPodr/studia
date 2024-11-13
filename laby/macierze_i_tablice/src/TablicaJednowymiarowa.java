import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TablicaJednowymiarowa {
    public int [] tablica;
    TablicaJednowymiarowa(int rozmiar) {
        tablica = new int[rozmiar];
    }

    public void generujLosowo(int K) {
        Random rd = new Random();
        for(int i = 0; i<tablica.length; i++){
            tablica[i] = rd.nextInt(K) + 1;
        }
    }

    public void show() {
        for(int i: tablica) {
            System.out.print(i);
        }
    }

    public void naOdwrot() {
        for(int i = tablica.length -1; i>=0; i--) {
            System.out.println(tablica[i]);
        }
    }

    public Object [][] ciagParzystych() {
        List<Integer> parzyste = new ArrayList<Integer>();
        List<Integer> nieParzyste = new ArrayList<Integer>();
        for(int i: tablica) {
            if(i%2 == 0) {
                parzyste.add(i);
            }
            else nieParzyste.add(i);
        }
        
        Object [][] result = new Object[2][];
        result[0] = parzyste.toArray();
        result[1] = nieParzyste.toArray();
        return result;
    }

    public int getMax(){
        int max = tablica[0];
        for(int i: tablica) {
            if(i>max) max = i;
        }
        return max;
    }

    public int getMin(){
        int min = tablica[0];
        for(int i: tablica) {
            if(i<min) min = i;
        }
        return min;
    }


}
