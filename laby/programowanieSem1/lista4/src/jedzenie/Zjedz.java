package jedzenie;

public class Zjedz {
    public static void mniam(KuchniaPolska jedzenie) {
        System.out.println("Dobre Polskie klasyczne jedzonko");
        Jedzenie.wypiszDania(jedzenie.dania);
        Jedzenie.wypiszDania(jedzenie.zupy);
        jedzenie.czyZamowione = true;
    }

    public static void mniam(KuchniaChinska jedzenie) {
        System.out.print("Dobre Chińskie jedzonko z ");
        System.out.print(jedzenie.prowincja);
        System.out.print("\n");
        Jedzenie.wypiszDania(jedzenie.dania);
        jedzenie.czyZamowione = true;
    }

    public static void mniam(Jedzenie jedzenie) {
        System.out.println("Dobre jedzonko");
        jedzenie.czyZamowione = true;
    }

    public static void fuj(KuchniaPolska jedzenie) {
        System.out.println("Złe Polskie klasyczne jedzonko");
        Jedzenie.wypiszDania(jedzenie.dania);
        Jedzenie.wypiszDania(jedzenie.zupy);
        jedzenie.czyZamowione = true;
    }

    public static void fuj(KuchniaChinska jedzenie) {
        System.out.print("Złe Chińskie jedzonko z ");
        System.out.print(jedzenie.prowincja);
        System.out.print("\n");
        Jedzenie.wypiszDania(jedzenie.dania);
        jedzenie.czyZamowione = true;
    }

    public static void fuj(Jedzenie jedzenie) {
        System.out.println("Złe jedzonko");
        jedzenie.czyZamowione = true;
    }
}
