package uczelnia;

public class PracownikAdministracyjny extends Pracownik {
    public PracownikAdministracyjny(String imie, String nazwisko, String pesel, int wiek, boolean czyMezczyzna,
            String stanowisko, int stazPracy, int pensja, int liczbaNdogodzin) {
        super(imie, nazwisko, pesel, wiek, czyMezczyzna, stanowisko, stazPracy, pensja);
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
