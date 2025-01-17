package uczelnia.osoby.add;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.HashSet;

import uczelnia.App;
import uczelnia.osoby.Kursy;
import uczelnia.osoby.Osoba;
import uczelnia.osoby.Student;
import uczelnia.osoby.Pracownik;

public class OsobyList extends ArrayList<Osoba> {
    HashSet<String> peselePracownikow = new HashSet<>();
    HashSet<Integer> indeksyStudentow = new HashSet<>();
    private ObservableList<Student> listaStudentow = FXCollections.observableArrayList();
    private ObservableList<Pracownik> listaPracownikow = FXCollections.observableArrayList();
    private ObservableList<Osoba> lista = FXCollections.observableArrayList();
    private static OsobyList listaOsob = new OsobyList();
    private String plik = "osoby.ser";

    public String getPlik() {
        return plik;
    }

    public static <T> List<T> showType(List<Osoba> list, Class<T> typZmiennejOczekiwany) {
        List<T> osoby = new ArrayList<>();
        for (Osoba i : list) {
            if (typZmiennejOczekiwany.isInstance(i)) {
                osoby.add(typZmiennejOczekiwany.cast(i));
            }
        }
        return osoby;
    }

    private OsobyList() {
        wczytaj();
    }

    @Override
    public boolean add(Osoba osoba) {
        if (osoba instanceof Student student) {
            if (indeksyStudentow.contains(student.getNrIndeksu())) {
                return false;
            }
            indeksyStudentow.add(student.getNrIndeksu());
            listaStudentow.add(student);
        } else if (peselePracownikow.contains(osoba.getPesel())) {
            return false;
        } else if (osoba instanceof Pracownik pracownik) {
            listaPracownikow.add(pracownik);
        }
        peselePracownikow.add(osoba.getPesel());
        boolean wynik = lista.add(osoba);
        App.zapisz(new ArrayList<Osoba>(lista), plik);
        return wynik;
    }

    @Override
    public Osoba get(int index) {
        return lista.get(index);
    }

    @Override
    public Osoba set(int index, Osoba element) {
        return lista.set(index, element);
    }

    public ObservableList<Student> getListaStudentow() {
        return listaStudentow;
    }

    public ObservableList<Pracownik> getListaPracownikow() {
        return listaPracownikow;
    }

    public ObservableList<Osoba> getLista() {
        return lista;
    }

    public static OsobyList getInstance() {
        return listaOsob;
    }

    @Override
    public boolean remove(Object osoba) {
        boolean wynik = lista.remove(osoba);
        listaPracownikow.remove(osoba);
        listaStudentow.remove(osoba);
        peselePracownikow.remove(((Osoba) osoba).getPesel());
        if(osoba instanceof Student student) {
            indeksyStudentow.remove(student.getNrIndeksu());
        }
        App.zapisz(new ArrayList<Osoba>(lista), plik);
        return wynik;
    }

    public boolean removeWithoutSaving(Object osoba) {
        boolean wynik = lista.remove(osoba);
        listaPracownikow.remove(osoba);
        listaStudentow.remove(osoba);
        peselePracownikow.remove(((Osoba) osoba).getPesel());
        if(osoba instanceof Student student) {
            indeksyStudentow.remove(student.getNrIndeksu());
        }
        return wynik;
    }



    private void wczytaj() {
        Object loadedObject = App.wczytaj(lista, plik);
        if (loadedObject instanceof ArrayList<?> o) {
            List<Osoba> listaArrayList = new ArrayList<>();
            for (Object obiekt : o) {
                if (obiekt instanceof Osoba osoba) {
                    listaArrayList.add((Osoba) osoba);
                    switch (osoba) {
                        case Student student -> {
                            indeksyStudentow.add(student.getNrIndeksu());
                            listaStudentow.add(student);
                        }
                        case Pracownik pracownik -> {
                            peselePracownikow.add(pracownik.getPesel());
                            listaPracownikow.add(pracownik);
                        }
                        default -> {
                            peselePracownikow.add(osoba.getPesel());
                        }
                    }
                }
            }
            lista = FXCollections.observableArrayList(listaArrayList);
        } else {
            lista = FXCollections.observableArrayList();
            App.showAlert("zepsuty plik " + plik, "Usuń ten plik lub ponownie dodaj Osoby");
        }
    }

    public void usunStudentowKursu(Kursy kurs) {
        for(Student student : listaStudentow) {
            if(student.getListaKursow().contains(kurs)) {
                student.getListaKursow().remove(kurs);
            }
        }
    }
}
