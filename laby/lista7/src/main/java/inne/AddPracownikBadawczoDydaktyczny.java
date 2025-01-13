package inne;

import java.util.List;
import java.util.Scanner;

import uczelnia.Osoba;
import uczelnia.Pracownik;
import uczelnia.PracownikBadawczoDydaktyczny;

public class AddPracownikBadawczoDydaktyczny implements AddPracownikStrategy {
    public void add(List<Osoba> osoby, Scanner scanner) {
        Pracownik pracownik = Pracownik.defaultPracownikInput(scanner);
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
}
