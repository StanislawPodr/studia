package jedzenie;

import java.util.List;

public class Jedzenie {
    List<String> smaki;
    List<String> dania;
    boolean czyOstre;
    boolean czyKwasne;
    int ocenaEkspertow;
    boolean czyZamowione = false;

    public Jedzenie(List<String> smaki, List<String> dania, boolean czyOstre, boolean czyKwasne, int ocenaEkspertow) {
        this.smaki = smaki;
        this.dania = dania;
        this.czyOstre = czyOstre;
        this.czyKwasne = czyKwasne;
        this.ocenaEkspertow = ocenaEkspertow;
    }

    static void wypiszDania(List<String> dania) {
        dania.forEach(System.out::println);
    }

    public List<String> getSmaki() {
        return smaki;
    }

    public List<String> getDania() {
        return dania;
    }

    public boolean isCzyOstre() {
        return czyOstre;
    }

    public boolean isCzyKwasne() {
        return czyKwasne;
    }

    public int getOcenaEkspertow() {
        return ocenaEkspertow;
    }

    public boolean isCzyZamowione() {
        return czyZamowione;
    }

}
