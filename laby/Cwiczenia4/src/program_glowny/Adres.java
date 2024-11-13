package program_glowny;

public class Adres {
    String numerMieszkania;
    String kodPocztowy;
    String ulica;
    String miejscowosc;

    public Adres(String numerMieszkania, String kodPocztowy, String ulica, String miejscowosc) {
        this.numerMieszkania = numerMieszkania;
        this.kodPocztowy = kodPocztowy;
        this.ulica = ulica;
        this.miejscowosc = miejscowosc;
    }

    public String getNumerMieszkania() {
        return numerMieszkania;
    }

    public void setNumerMieszkania(String numerMieszkania) {
        this.numerMieszkania = numerMieszkania;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

}
