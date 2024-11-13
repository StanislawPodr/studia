package program_glowny;

public class Wlasciciel {
    String pesel;
    String miejsceUrodzneia;
    String dataUrodzenia;
    Adres adres;

    public Wlasciciel(String pesel, String miejsceUrodzneia, String dataUrodzenia, Adres adres) {
        this.pesel = pesel;
        this.miejsceUrodzneia = miejsceUrodzneia;
        this.dataUrodzenia = dataUrodzenia;
        this.adres = adres;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getMiejsceUrodzneia() {
        return miejsceUrodzneia;
    }

    public void setMiejsceUrodzneia(String miejsceUrodzneia) {
        this.miejsceUrodzneia = miejsceUrodzneia;
    }

    public String getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(String dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }
}
