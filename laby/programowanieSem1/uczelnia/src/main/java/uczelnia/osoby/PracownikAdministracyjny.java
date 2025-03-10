package uczelnia.osoby;

public class PracownikAdministracyjny extends Pracownik {
    public PracownikAdministracyjny(String imie, String nazwisko, String pesel, int wiek, String plec,
            String stanowisko, int stazPracy, int pensja, int liczbaNdogodzin) {
        super(imie, nazwisko, pesel, wiek, plec, stanowisko, stazPracy, pensja);
        this.liczbaNdogodzin = liczbaNdogodzin;
    }

    private int liczbaNdogodzin;

    public int getLiczbaNdogodzin() {
        return liczbaNdogodzin;
    }

    public void setLiczbaNdogodzin(int liczbaNdogodzin) {
        this.liczbaNdogodzin = liczbaNdogodzin;
    }
}
