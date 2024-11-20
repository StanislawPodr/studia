public class App {
    public static void main(String[] args) throws Exception {
        // TablicaJednowymiarowa tab = new TablicaJednowymiarowa(20);
        // tab.generujLosowo(100);
        // tab.show();
        // System.out.println();
        // Object [][] tabParzyste = tab.ciagParzystych();
        // for(int i = 0; i<2; i++){
        //     for(Object j: tabParzyste[i]){
        //         System.out.println(j);
        //     }
        //     System.out.println();
        // }

        // System.out.println(tab.getMax());
        // System.out.println(tab.getMin());
        // System.out.println();

        // tab.naOdwrot();

        int [][] m = {{1, 2, 3}, {4, 5, 6}};
        Macierz macierz = new Macierz(m);
        int [][] m2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        Macierz macierz2 = new Macierz(m2);
        macierz.multiply(macierz2).show();
        // Macierz macierz3 = macierz.dodaj(macierz2).dodaj(macierz);
        //macierz3.show();
    }
}
