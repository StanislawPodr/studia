import java.util.List;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import uczelnia.Kursy;
import uczelnia.Osoba;
import uczelnia.Pracownik;
import uczelnia.PracownikAdministracyjny;
import uczelnia.PracownikBadawczoDydaktyczny;
import uczelnia.Student;

public class App {
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

    public static <T> List<T> showType(List<Osoba> list, Class<T> typZmiennejOczekiwany) {
        List<T> osoby = new ArrayList<>();
        for(Osoba i : list) {
            if(typZmiennejOczekiwany.isInstance(i)) {
                osoby.add(typZmiennejOczekiwany.cast(i));
            }
        }
        return osoby;
    }

    public static Pracownik defaultPracownikInput(Scanner scanner) {
        Osoba osobaPracownicza = defaultOsobaInput(scanner);
        System.out.println("Podaj stanowisko: ");
        String stanowisko = scanner.next();
        System.out.println("Podaj liczbę lat stażu: ");
        String stazString = scanner.next();
        System.out.println("Podaj pensję (PLN): ");
        String pensjaString = scanner.next();
        int staz, pensja;
        try {
            staz = Integer.parseInt(stazString);
            pensja = Integer.parseInt(pensjaString);
        } catch (NumberFormatException e) {
            System.out.println("Staż i pensja to liczby!");
            return null;
        }
        return new Pracownik(osobaPracownicza.getImie(), osobaPracownicza.getNazwisko(), osobaPracownicza.getPesel(),
                osobaPracownicza.getWiek(), osobaPracownicza.getPlec(), stanowisko, staz, pensja);
    }

    public static boolean isTak(String in) {
        if (in.equals("tak"))
            return true;
        return false;
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ile studentów chciałbyś dodać?");
        String ileStudentow = scanner.next();
        int studenci = 0;
        System.out.println("Ile pracowników badawczo-dydaktycznych chciałbyś dodać?");
        String ilePracownikowBadawczoDydaktycznych = scanner.next();
        int pracownicyBdawczoDydaktyczni = 0;
        System.out.println("Ile pracowników administracyjnych chciałbyś dodać?");
        String ilePracownikowAdministracyjnych = scanner.next();
        int pracownicyAdminitracyjni = 0;
        System.out.println("Czy chciałbyś utworzyć nową listę kursów? (tak/nie)");
        String nowaListaKursowString = scanner.next();
        String ileKursow = "0";
        if (isTak(nowaListaKursowString)) {
            System.out.println("Podaj liczbę kursów którą chciałbyś dodać do listy: ");
            ileKursow = scanner.next();
        }
        int kursy = 0;
        try {
            studenci = Integer.parseInt(ileStudentow);
            pracownicyBdawczoDydaktyczni = Integer.parseInt(ilePracownikowBadawczoDydaktycznych);
            pracownicyAdminitracyjni = Integer.parseInt(ilePracownikowAdministracyjnych);
            kursy = Integer.parseInt(ileKursow);
        } catch (NumberFormatException e) {
            System.out.println("Podaj wartości numeryczne");
            return;
        }

        List<Osoba> osoby = new ArrayList();
        List<List<Kursy>> listyKursow = new ArrayList<>();
        try {
            ObjectInputStream oisOsoby = new ObjectInputStream(new FileInputStream("osoby.ser"));
            ObjectInputStream oisKursy = new ObjectInputStream(new FileInputStream("kursy.ser"));
            osoby = (List<Osoba>) oisOsoby.readObject();
            listyKursow = (List<List<Kursy>>) oisKursy.readObject();
            System.out.println("Odczytano z zapisu");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Nie masz jeszcze zapisu osób.");
        }

        try {
            ObjectInputStream oisKursy = new ObjectInputStream(new FileInputStream("kursy.ser"));
            listyKursow = (List<List<Kursy>>) oisKursy.readObject();
            System.out.println("Odczytano kursy z zapisu");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Nie masz jeszcze zapisu kursów.");
        }

        List<Kursy> nowaListaKursow = new ArrayList<>();
        for (int i = 0; i < kursy; i++) {
            System.out.println("Dodawanie " + i + 1 + " kursu");
            System.out.println("Podaj nazwę kursu: ");
            String nazwaKursu = scanner.next();
            System.out.println("Podaj imię i nazwisko prowadzącego: ");
            String prowadzacy = scanner.next();
            System.out.println("Podaj ilość punktów ECTS: ");
            String punktyEctsString = scanner.next();
            int punktyEcts;
            try {
                punktyEcts = Integer.parseInt(punktyEctsString);
            } catch (NumberFormatException e) {
                System.out.println("punkty ECTS muszą być liczbą stałoprzecinkową");
                return;
            }
            nowaListaKursow.add(new Kursy(nazwaKursu, prowadzacy, punktyEcts));
        }
        if (kursy != 0)
            listyKursow.add(nowaListaKursow);

        for (int i = 0; i < studenci; i++) {
            System.out.println("Dodawanie " + i + 1 + " studenta");
            Osoba daneOsoboweStudenta = defaultOsobaInput(scanner);
            if (daneOsoboweStudenta == null)
                return;
            System.out.println("Podaj nr indeksu: ");
            String nrIndeksuString = scanner.next();
            System.out.println("Podaj rok studiów: ");
            String rokStudiowString = scanner.next();
            System.out.println("Które kursy:");
            String nrIndeksuKursowString = scanner.next();
            List<Kursy> listaKursow = new ArrayList<>();
            int nrIndeksu;
            int rokStudiow;
            try {
                int indeksKursow = Integer.parseInt(nrIndeksuKursowString);
                listaKursow = listyKursow.get(indeksKursow);
                nrIndeksu = Integer.parseInt(nrIndeksuString);
                rokStudiow = Integer.parseInt(rokStudiowString);
            } catch (NumberFormatException e) {
                System.out.println("Indeks kursów, nr Indeksu i rok studiów muszą być liczbą!");
                return;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Nie ma takiego indeksu kursu!");
                return;
            }
            System.out.println("Czy jest na ERASMUS-ie? (tak/nie)");
            String czyErasmusString = scanner.next();
            System.out.println("Czy studia pierwszego stopnia? (tak/nie)");
            String czyStudiaPierwszegoStopniaString = scanner.next();
            System.out.println("Czy studia drugiego stopnia? (tak/nie)");
            String czyStudiaDrugiegoStopniaString = scanner.next();
            System.out.println("Czy studuje stacjonarnie? (tak/nie)");
            String czyStacjonarnieString = scanner.next();
            System.out.println("Czy studiuje niestacjonarnie? (tak/nie)");
            String czyNieStacjonarnieString = scanner.next();
            osoby.add(new Student(daneOsoboweStudenta.getImie(), daneOsoboweStudenta.getNazwisko(),
                    daneOsoboweStudenta.getPesel(),
                    daneOsoboweStudenta.getWiek(), daneOsoboweStudenta.getPlec(), nrIndeksu, rokStudiow, listaKursow,
                    isTak(czyErasmusString),
                    isTak(czyStudiaPierwszegoStopniaString), isTak(czyStudiaDrugiegoStopniaString),
                    isTak(czyStacjonarnieString), isTak(czyNieStacjonarnieString)));
        }

        for (int i = 0; i < pracownicyAdminitracyjni; i++) {
            System.out.println("Dodawanie " + i + 1 + " pracownika administracyjnego");
            Pracownik pracownik = defaultPracownikInput(scanner);
            if (pracownik == null)
                return;
            System.out.println("Podaj liczbę nadgodzin: ");
            String nadgodzinyString = scanner.next();
            int nadgodziny;
            try {
                nadgodziny = Integer.parseInt(nadgodzinyString);
            } catch (NumberFormatException e) {
                System.out.println("Nadgodziny jak nazwa mówi to LICZBA godzin!");
                return;
            }
            osoby.add(new PracownikAdministracyjny(pracownik.getImie(), pracownik.getNazwisko(),
                    pracownik.getPesel(),
                    pracownik.getWiek(), pracownik.getPlec(), pracownik.getStanowisko(), pracownik.getStazPracy(),
                    pracownik.getPensja(), nadgodziny));
        }

        for (int i = 0; i < pracownicyBdawczoDydaktyczni; i++) {
            System.out.println("Dodawanie " + i + 1 + " pracownika badawczo-dydaktycznego");
            Pracownik pracownik = defaultPracownikInput(scanner);
            if (pracownik == null)
                return;
            System.out.println("Podaj liczbę publikacji: ");
            String liczbaPublikacjiString = scanner.next();
            int liczbaPublikacji;
            try {
                liczbaPublikacji = Integer.parseInt(liczbaPublikacjiString);
            } catch (NumberFormatException e) {
                System.out.println("Liczba publikacji to liczba!");
                return;
            }
            osoby.add(new PracownikBadawczoDydaktyczny(pracownik.getImie(), pracownik.getNazwisko(),
                    pracownik.getPesel(),
                    pracownik.getWiek(), pracownik.getPlec(), pracownik.getStanowisko(), pracownik.getStazPracy(),
                    pracownik.getPensja(), liczbaPublikacji));
        }

        try {

            if (!listyKursow.isEmpty()) {
                ObjectOutputStream oosKursy = new ObjectOutputStream(new FileOutputStream("kursy.ser"));
                oosKursy.writeObject(listyKursow);

            }

            if (!osoby.isEmpty()) {
                ObjectOutputStream oosOsoby = new ObjectOutputStream(new FileOutputStream("osoby.ser"));
                oosOsoby.writeObject(osoby);
            }

            System.out.println("\n Obiekty zapisano do pliku.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Osoba i : osoby) {
            i.show();
        }

        for (List<Kursy> i : listyKursow) {
            for (Kursy j : i) {
                j.show();
            }
            System.out.println();
        }
    }
}
