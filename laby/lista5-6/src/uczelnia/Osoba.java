package uczelnia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Osoba implements Serializable{
    private String imie;
    private String nazwisko;
    private String pesel;
    private int wiek;
    private String plec;

    public Osoba(String imie, String nazwisko, String pesel, int wiek, String plec) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.wiek = wiek;
        this.plec = plec;
    }

    public static <T> List<T> getType(List<Osoba> list, Class<T> typZmiennejOczekiwany) {
        List<T> osoby = new ArrayList<>();
        for(Osoba i : list) {
            if(typZmiennejOczekiwany.isInstance(i)) {
                osoby.add(typZmiennejOczekiwany.cast(i));
            }
        }
        return osoby;
    }

    static void removeByImie(List<Osoba> osoby, String imie) {
        osoby.removeIf(osoba -> osoba.getImie().equals(imie));
    }

    static void removeByNazwisko(List<Osoba> osoby, String nazwisko) {
        osoby.removeIf(osoba -> osoba.getNazwisko().equals(nazwisko));
    }

    public static void sortByNazwisko(List<Osoba> osoby)  {
        Comparator<Osoba> byNazwisko = Comparator.comparing(Osoba::getNazwisko);
        osoby.sort(byNazwisko);
    }

    public static void sortByNazwiskoIImie(List<Osoba> osoby)  {
        Comparator<Osoba> byNazwiskoIImie = Comparator.comparing(Osoba::getNazwisko).thenComparing(Osoba::getImie);
        osoby.sort(byNazwiskoIImie);
    }

    public static void sortByNazwiskoIWiek(List<Osoba> osoby)  {
        Comparator<Osoba> byNazwiskoIWiek = Comparator.comparing(Osoba::getNazwisko).thenComparing(Comparator.comparingInt(Osoba::getWiek).reversed());
        osoby.sort(byNazwiskoIWiek);
    }

    public static List<Osoba> searchByImie(List<Osoba> listaOsob, String imie) {
        List<Osoba> wyszukano = new ArrayList<>();
        for (Osoba osoba : listaOsob) {
            if (osoba.getImie().equals(imie)) {
                wyszukano.add(osoba);
            }
        }
        return wyszukano;
    }

    public static List<Osoba> searchByNazwisko(List<Osoba> listaOsob, String nazwisko) {
        List<Osoba> wyszukano = new ArrayList<>();
        for (Osoba osoba : listaOsob) {
            if (osoba.getNazwisko().equals(nazwisko)) {
                wyszukano.add(osoba);
            }
        }
        return wyszukano;
    }

    public void show() {
        System.out.println(imie + " " + nazwisko);
    }


    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public int getWiek() {
        return wiek;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }
}
