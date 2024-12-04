package uczelnia;

import java.util.ArrayList;
import java.util.List;

public abstract class Pracownik extends Osoba {
    private String stanowisko;
    private int stazPracy;
    private int pensja;

    public static List<Osoba> searchByStanowisko(List<Pracownik> listaPracownikow, String stanowisko) {
        List<Osoba> wyszukano = new ArrayList<>();
        for (Pracownik osoba : listaPracownikow) {
            if (osoba.getStanowisko().equals(stanowisko)) {
                wyszukano.add(osoba);
            }
        }
        return wyszukano;
    }

    public static List<Osoba> searchByStazPracy(List<Pracownik> listaPracownikow, int stazPracy) {
        List<Osoba> wyszukano = new ArrayList<>();
        for (Pracownik osoba : listaPracownikow) {
            if (osoba.getStazPracy() == stazPracy) {
                wyszukano.add(osoba);
            }
        }
        return wyszukano;
    }

    public static List<Osoba> searchByLiczbaNadgodzin(List<Pracownik> listaPracownikow, int pensja) {
        List<Osoba> wyszukano = new ArrayList<>();
        for (Pracownik osoba : listaPracownikow) {
            if (osoba.getPensja() == pensja) {
                wyszukano.add(osoba);
            }
        }
        return wyszukano;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public int getStazPracy() {
        return stazPracy;
    }

    public void setStazPracy(int stazPracy) {
        this.stazPracy = stazPracy;
    }

    public int getPensja() {
        return pensja;
    }

    public void setPensja(int pensja) {
        this.pensja = pensja;
    }

    protected Pracownik(String imie, String nazwisko, String pesel, int wiek, boolean czyMezczyzna, String stanowisko,
            int stazPracy, int pensja) {
        super(imie, nazwisko, pesel, wiek, czyMezczyzna);
        this.stanowisko = stanowisko;
        this.stazPracy = stazPracy;
        this.pensja = pensja;
    }
}
