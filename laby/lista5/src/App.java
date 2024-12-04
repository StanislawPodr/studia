import java.util.List;
import java.util.ArrayList;

import uczelnia.Osoba;
import uczelnia.PracownikAdministracyjny;

public class App {
    public static void main(String[] args) throws Exception {
        Osoba o2 = new PracownikAdministracyjny("wiesiek", null, null, 0, false, null, 0, 0, 0);
        Osoba o1 = new PracownikAdministracyjny("maciej", null, null, 0, false, null, 0, 0, 0);
        List<Osoba> osoby = new ArrayList<>();
        osoby.add(o1);
        osoby.add(o2);
        for(Osoba osob : PracownikAdministracyjny.searchByImie(osoby, "maciej")) {
            System.out.println(osob.getImie());
        }
    }
}
