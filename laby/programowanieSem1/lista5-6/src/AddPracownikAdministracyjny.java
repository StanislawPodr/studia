import java.util.List;
import java.util.Scanner;

import uczelnia.Osoba;
import uczelnia.Pracownik;
import uczelnia.PracownikAdministracyjny;

public class AddPracownikAdministracyjny implements AddPracownikStrategy {
    public void add(List<Osoba> osoby, Scanner scanner) {
        Pracownik pracownik = Pracownik.defaultPracownikInput(scanner);
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
}
