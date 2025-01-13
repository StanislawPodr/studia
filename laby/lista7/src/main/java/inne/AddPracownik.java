package inne;

import java.util.List;
import java.util.Scanner;

import uczelnia.Osoba;

public class AddPracownik {
    private AddPracownikStrategy addPracownikStrategy;
    public void setStrategy(AddPracownikStrategy strategia) {
        addPracownikStrategy = strategia;
    }
    
    public void add(List<Osoba> osoby, Scanner scanner) {
        addPracownikStrategy.add(osoby, scanner);
    }
}
