package program_glowny;

import pakiet.Biblioteka;

public class App {
    public static void main(String[] args) throws Exception {
        Biblioteka.e(10, 1);
        Samochod[] auta = new Samochod[3];
        Adres a1 = new Adres("4C", "50-120", "Tęczowa", "Wrocław");
        Wlasciciel w1 = new Wlasciciel("11111111111", "Wrocław", "12.12.1998", a1);
        auta[0] = new Samochod(1999, 3, true, w1);
        auta[1] = new Samochod(2020, 9, true, w1);
        auta[2] = new Samochod(2077, 5, false, new Wlasciciel(null, null, null, new Adres(null, null, null, null)));
        
    }
}
