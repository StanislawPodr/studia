import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

import uczelnia.Osoba;
import uczelnia.Student;
import uczelnia.Pracownik;

public class OsobyList extends ArrayList<Osoba> {
    HashSet<String> peselePracownikow;
    HashSet<Integer> indeksyStudentow;
    private List<Student> listaStudentow = new ArrayList<>();
    private List<Pracownik> listaPracownikow = new ArrayList<>();

    @Override
    public boolean add(Osoba osoba) {
        if(osoba instanceof Student student) {
            if(indeksyStudentow.contains(student.getNrIndeksu())) {
                return false;
            }
            indeksyStudentow.add(student.getNrIndeksu());
            listaStudentow.add(student);
        }
        else if(peselePracownikow.contains(osoba.getPesel())) { 
            return false;
        }
        else if(osoba instanceof Pracownik pracownik) {
            listaPracownikow.add(pracownik);
        }
        peselePracownikow.add(osoba.getPesel());
        return super.add(osoba);
    }

    
}
