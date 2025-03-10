public class Student extends Osoba {
    private String nrIndeksu;
    private int ktorySemestr;
    private PrzedmiotyStudenta [] przedmioty;

    public Student(String imie, String nazwisko, String nrIndeksu, int ktorySemestr, PrzedmiotyStudenta[] przedmioty) {
        super(imie, nazwisko);
        this.nrIndeksu = nrIndeksu;
        this.ktorySemestr = ktorySemestr;
        this.przedmioty = przedmioty;
    }

    public String getNrIndeksu() {
        return nrIndeksu;
    }

    public int getKtorySemestr() {
        return ktorySemestr;
    }

    public void setNrIndeksu(String nrIndeksu) {
        this.nrIndeksu = nrIndeksu;
    }

    public void setKtorySemestr(int ktorySemestr) {
        this.ktorySemestr = ktorySemestr;
    }
}
