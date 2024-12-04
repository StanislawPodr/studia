package uczelnia;

public class PracownikBadawczoDydaktyczny extends Pracownik {

    private int liczbaPublikacji;

    public PracownikBadawczoDydaktyczny(String imie, String nazwisko, String pesel, int wiek, boolean czyMezczyzna,
            String stanowisko, int stazPracy, int pensja, int liczbaPublikacji) {
        super(imie, nazwisko, pesel, wiek, czyMezczyzna, stanowisko, stazPracy, pensja);
        this.liczbaPublikacji = liczbaPublikacji;
    }

    public int getLiczbaPublikacji() {
        return liczbaPublikacji;
    }

    public void setLiczbaPublikacji(int liczbaPublikacji) {
        this.liczbaPublikacji = liczbaPublikacji;
    }
}
