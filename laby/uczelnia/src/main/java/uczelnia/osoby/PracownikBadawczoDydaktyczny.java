package uczelnia.osoby;

public class PracownikBadawczoDydaktyczny extends Pracownik {

    private int liczbaPublikacji;

    public PracownikBadawczoDydaktyczny(String imie, String nazwisko, String pesel, int wiek, String plec,
            String stanowisko, int stazPracy, int pensja, int liczbaPublikacji) {
        super(imie, nazwisko, pesel, wiek, plec, stanowisko, stazPracy, pensja);
        this.liczbaPublikacji = liczbaPublikacji;
    }

    public int getLiczbaPublikacji() {
        return liczbaPublikacji;
    }

    public void setLiczbaPublikacji(int liczbaPublikacji) {
        this.liczbaPublikacji = liczbaPublikacji;
    }
}
