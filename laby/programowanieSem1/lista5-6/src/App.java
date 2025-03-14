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
    public static <T> List<T> showType(List<Osoba> list, Class<T> typZmiennejOczekiwany) {
        List<T> osoby = new ArrayList<>();
        for (Osoba i : list) {
            if (typZmiennejOczekiwany.isInstance(i)) {
                osoby.add(typZmiennejOczekiwany.cast(i));
            }
        }
        return osoby;
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
        try (ObjectInputStream oisOsoby = new ObjectInputStream(new FileInputStream("osoby.ser"));
                ObjectInputStream oisKursy = new ObjectInputStream(new FileInputStream("kursy.ser"))) {
            osoby = (List<Osoba>) oisOsoby.readObject();
            listyKursow = (List<List<Kursy>>) oisKursy.readObject();
            System.out.println("Odczytano z zapisu");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Nie masz jeszcze zapisu osób.");
        }

        try (ObjectInputStream oisKursy = new ObjectInputStream(new FileInputStream("kursy.ser"))) {

            listyKursow = (List<List<Kursy>>) oisKursy.readObject();
            System.out.println("Odczytano kursy z zapisu");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Nie masz jeszcze zapisu kursów.");
        }

        List<Kursy> nowaListaKursow = new ArrayList<>();
        for (int i = 0; i < kursy; i++) {
            System.out.println("Dodawanie " + (i + 1) + " kursu");
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
            System.out.println("Dodawanie " + (i + 1) + " studenta");
            Osoba daneOsoboweStudenta = Osoba.defaultOsobaInput(scanner);
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
                System.out.println(
                        "Indeks kursów, nr Indeksu i rok studiów muszą być liczbą! Grupa kursów musi istnieć!");
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

        AddPracownik pracownikAdder = new AddPracownik();
        AddPracownikBadawczoDydaktyczny pracownikBadawczoDydaktycznyStrategy = new AddPracownikBadawczoDydaktyczny();
        AddPracownikAdministracyjny pracownikAdministracyjnyStrategy = new AddPracownikAdministracyjny();

        pracownikAdder.setStrategy(pracownikAdministracyjnyStrategy);

        for (int i = 0; i < pracownicyAdminitracyjni; i++) {
            System.out.println("Dodawanie " + (i + 1) + " pracownika administracyjnego");
            pracownikAdder.add(osoby, scanner);
        }

        pracownikAdder.setStrategy(pracownikBadawczoDydaktycznyStrategy);
        for (int i = 0; i < pracownicyBdawczoDydaktyczni; i++) {
            System.out.println("Dodawanie " + (i + 1) + " pracownika badawczo-dydaktycznego");
            pracownikAdder.add(osoby, scanner);
        }

        try {

            if (!listyKursow.isEmpty()) {
                ObjectOutputStream oosKursy = new ObjectOutputStream(new FileOutputStream("kursy.ser"));
                oosKursy.writeObject(listyKursow);
                oosKursy.close();
            }

            if (!osoby.isEmpty()) {
                ObjectOutputStream oosOsoby = new ObjectOutputStream(new FileOutputStream("osoby.ser"));
                oosOsoby.writeObject(osoby);
                oosOsoby.close();
            }

            System.out.println("\n Obiekty zapisano do pliku.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Student> listaStudentow = showType(osoby, Student.class);

        for (Student i : listaStudentow) {
            i.show();
            System.out.println(i.getRokStudiow());
        }

        for (List<Kursy> i : listyKursow) {
            for (Kursy j : i) {
                j.show();
            }
            System.out.println();
        }

        Student.removeByRokStudiow(listaStudentow, 1);
        System.out.println();

        for (Student i : listaStudentow) {
            i.show();
            System.out.println(i.getRokStudiow());
        }
        scanner.close();

    }
}
