public class App {
    public static void main(String[] args) throws Exception {
        Tablice tab = new Tablice();
        tab.sortTablica10();
        for(int i : tab.tablica10) {
            System.out.print(i);
        }
    }
}
