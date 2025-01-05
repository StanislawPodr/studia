package uczelnia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Kursy implements Serializable {
    public Kursy(String nazwaKursu, String prowadzacy, int punktyEcts) {
        this.nazwaKursu = nazwaKursu;
        this.prowadzacy = prowadzacy;
        this.punktyEcts = punktyEcts;
    }

    private String nazwaKursu;
    private String prowadzacy;
    private int punktyEcts;

    static void removeByNazwaKursu(List<Kursy> kursy, String nazwa) {
        kursy.removeIf(kurs -> kurs.getNazwaKursu().equals(nazwa));
    }

    static void removeByNazwisko(List<Kursy> kursy, String nazwisko) {
        kursy.removeIf(kurs -> kurs.getProwadzacy().equals(nazwisko));
    }

    static void removeByEcts(List<Kursy> kursy, int punkty) {
        kursy.removeIf(kurs -> kurs.getPunktyEcts() == punkty);
    }

    public static void sortKursy(List<Kursy> kursy) {
        Comparator<Kursy> kursyByNazwaImieIPunktyEcts = Comparator.comparingInt(Kursy::getPunktyEcts).thenComparing(Kursy::getProwadzacy);
        kursy.sort(kursyByNazwaImieIPunktyEcts);
    }

     public static List<Kursy> searchByNazwaKursu(List<Kursy> listaKursow, String nazwaKursu) {
        List<Kursy> wyszukano = new ArrayList<>();
        for (Kursy kurs : listaKursow) {
            if (kurs.getNazwaKursu().equals(nazwaKursu)) {
                wyszukano.add(kurs);
            }
        }
        return wyszukano;
    }

    public static List<Kursy> searchByProwadzacy(List<Kursy> listaKursow, String prowadzacy) {
        List<Kursy> wyszukano = new ArrayList<>();
        for (Kursy kurs : listaKursow) {
            if (kurs.getProwadzacy().equals(prowadzacy)) {
                wyszukano.add(kurs);
            }
        }
        return wyszukano;
    }

    public static List<Kursy> searchByPunktyEcts(List<Kursy> listaKursow, int punktyEcts) {
        List<Kursy> wyszukano = new ArrayList<>();
        for (Kursy kurs : listaKursow) {
            if (kurs.getPunktyEcts() == punktyEcts) {
                wyszukano.add(kurs);
            }
        }
        return wyszukano;
    }

    public void show() {
        System.out.print(nazwaKursu + "; ");
    }

    public String getNazwaKursu() {
        return nazwaKursu;
    }

    public void setNazwaKursu(String nazwaKursu) {
        this.nazwaKursu = nazwaKursu;
    }

    public String getProwadzacy() {
        return prowadzacy;
    }

    public void setProwadzacy(String prowadzacy) {
        this.prowadzacy = prowadzacy;
    }

    public int getPunktyEcts() {
        return punktyEcts;
    }

    public void setPunktyEcts(int punktyEcts) {
        this.punktyEcts = punktyEcts;
    }
}
