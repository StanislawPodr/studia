package uczelnia;

import java.util.ArrayList;
import java.util.List;

public class Student extends Osoba {
    public Student(String imie, String nazwisko, String pesel, int wiek, String plec, int nrIndeksu,
            int rokStudiow, List<Kursy> listaKursow, boolean erasmus, boolean inzynier, boolean magister,
            boolean studiujeStacjonarnie, boolean studiujeNieStacjonarnie) {
        super(imie, nazwisko, pesel, wiek, plec);
        this.nrIndeksu = nrIndeksu;
        this.rokStudiow = rokStudiow;
        this.listaKursow = listaKursow;
        this.erasmus = erasmus;
        this.inzynier = inzynier;
        this.magister = magister;
        this.studiujeStacjonarnie = studiujeStacjonarnie;
        this.studiujeNieStacjonarnie = studiujeNieStacjonarnie;
    }

    private int nrIndeksu;
    private int rokStudiow;
    List<Kursy> listaKursow;
    private boolean erasmus;
    private boolean inzynier;
    private boolean magister;
    private boolean studiujeStacjonarnie;
    private boolean studiujeNieStacjonarnie;

    public static List<Osoba> searchByNrIndeksu(List<Student> listaStudentow, int nrIndeksu) {
        List<Osoba> wyszukano = new ArrayList<>();
        for (Student osoba : listaStudentow) {
            if (osoba.getNrIndeksu() == nrIndeksu) {
                wyszukano.add(osoba);
            }
        }
        return wyszukano;
    }

    public static List<Osoba> searchByRokStudiow(List<Student> listaStudentow, int rokStudiow) {
        List<Osoba> wyszukano = new ArrayList<>();
        for (Student osoba : listaStudentow) {
            if (osoba.getRokStudiow() == rokStudiow) {
                wyszukano.add(osoba);
            }
        }
        return wyszukano;
    }

    public static List<Osoba> searchByNazwaKursu(List<Student> listaStudentow, String nazwaKursu) {
        List<Osoba> wyszukano = new ArrayList<>();
        for (Student osoba : listaStudentow) {
            for(Kursy kurs : osoba.listaKursow) {
                if (kurs.getNazwaKursu().equals(nazwaKursu)) {
                    wyszukano.add(osoba);
                    break;
                }
            }
        }
        return wyszukano;
    }

    public int getNrIndeksu() {
        return nrIndeksu;
    }

    public void setNrIndeksu(int nrIndeksu) {
        this.nrIndeksu = nrIndeksu;
    }

    public int getRokStudiow() {
        return rokStudiow;
    }

    public void setRokStudiow(int rokStudiow) {
        this.rokStudiow = rokStudiow;
    }

    public List<Kursy> getListaKursow() {
        return listaKursow;
    }

    public void setListaKursow(List<Kursy> listaKursow) {
        this.listaKursow = listaKursow;
    }

    public boolean isErasmus() {
        return erasmus;
    }

    public void setErasmus(boolean erasmus) {
        this.erasmus = erasmus;
    }

    public boolean isInzynier() {
        return inzynier;
    }

    public void setInzynier(boolean inzynier) {
        this.inzynier = inzynier;
    }

    public boolean isMagister() {
        return magister;
    }

    public void setMagister(boolean magister) {
        this.magister = magister;
    }

    public boolean isStudiujeStacjonarnie() {
        return studiujeStacjonarnie;
    }

    public void setStudiujeStacjonarnie(boolean studiujeStacjonarnie) {
        this.studiujeStacjonarnie = studiujeStacjonarnie;
    }

    public boolean isStudiujeNieStacjonarnie() {
        return studiujeNieStacjonarnie;
    }

    public void setStudiujeNieStacjonarnie(boolean studiujeNieStacjonarnie) {
        this.studiujeNieStacjonarnie = studiujeNieStacjonarnie;
    }
}
