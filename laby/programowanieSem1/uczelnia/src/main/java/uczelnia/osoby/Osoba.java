package uczelnia.osoby;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

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

     public static Osoba defaultOsobaInput(Scanner scanner) {
        System.out.println("Podaj imię: ");
        String imie = scanner.next();
        System.out.println("Podaj nazwisko: ");
        String nazwisko = scanner.next();
        System.out.println("Podaj PESEL: ");
        String pesel = scanner.next();
        System.out.println("Podaj wiek: ");
        String wiekString = scanner.next();
        System.out.println("Podaj płeć: ");
        String plec = scanner.next();
        int wiek = 0;
        try {
            wiek = Integer.parseInt(wiekString);
        } catch (NumberFormatException e) {
            System.out.println("Wiek musi byc liczbą!");
            return null;
        }
        return new Osoba(imie, nazwisko, pesel, wiek, plec);
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

    public static void removeByImie(List<? extends Osoba> osoby, String imie) {
        osoby.removeIf(osoba -> osoba.getImie().equals(imie));
    }

    public static void removeByNazwisko(List<? extends Osoba> osoby, String nazwisko) {
        osoby.removeIf(osoba -> osoba.getNazwisko().equals(nazwisko));
    }

    public static void sortByNazwisko(List<? extends Osoba> osoby)  {
        Comparator<Osoba> byNazwisko = Comparator.comparing(Osoba::getNazwisko);
        osoby.sort(byNazwisko);
    }

    public static void sortByNazwiskoIImie(List<? extends Osoba> osoby)  {
        Comparator<Osoba> byNazwiskoIImie = Comparator.comparing(Osoba::getNazwisko).thenComparing(Osoba::getImie);
        osoby.sort(byNazwiskoIImie);
    }

    public static void sortByNazwiskoIWiek(List<? extends Osoba> osoby)  {
        Comparator<Osoba> byNazwiskoIWiek = Comparator.comparing(Osoba::getNazwisko).thenComparing(Comparator.comparingInt(Osoba::getWiek).reversed());
        osoby.sort(byNazwiskoIWiek);
    }

    public static List<Osoba> searchByImie(List<? extends Osoba> listaOsob, String imie) {
        List<Osoba> wyszukano = new ArrayList<>();
        if(imie == null) {
            return wyszukano;
        }
        for (Osoba osoba : listaOsob) {
            if (osoba.getImie().equals(imie)) {
                wyszukano.add(osoba);
            }
        }
        return wyszukano;
    }

    public static List<Osoba> searchByNazwisko(List<? extends Osoba> listaOsob, String nazwisko) {
        List<Osoba> wyszukano = new ArrayList<>();
        if(nazwisko == null) {
            return wyszukano;
        }
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

    @Override
    public String toString() {
        return imie + " " + nazwisko;
    }

    public String wypiszDane() {
        StringBuilder dane = new StringBuilder();
        Class<?> clazz = this.getClass();
        while(clazz != null) {
            for(Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(this); 
                    dane.append(field.getName())
                        .append(": ")
                        .append(value != null ? value : "null")
                        .append("\n");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            clazz = clazz.getSuperclass();
        }
        return dane.toString();
    }
}
