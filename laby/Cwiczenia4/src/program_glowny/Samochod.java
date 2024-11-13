package program_glowny;

public class Samochod {
    int rokProdukcji;
    int typSilnika;
    boolean czyUbezpieczony;
    Wlasciciel wlasciciel;

    public Samochod(int rokProdukcji, int typSilnika, boolean czyUbezpieczony, Wlasciciel wlasciciel) {
        this.rokProdukcji = rokProdukcji;
        this.typSilnika = typSilnika;
        this.czyUbezpieczony = czyUbezpieczony;
        this.wlasciciel = wlasciciel;
    }

    public int getRokProdukcji() {
        return rokProdukcji;
    }

    public void setRokProdukcji(int rokProdukcji) {
        this.rokProdukcji = rokProdukcji;
    }

    public int getTypSilnika() {
        return typSilnika;
    }

    public void setTypSilnika(int typSilnika) {
        this.typSilnika = typSilnika;
    }

    public boolean isCzyUbezpieczony() {
        return czyUbezpieczony;
    }

    public void setCzyUbezpieczony(boolean czyUbezpieczony) {
        this.czyUbezpieczony = czyUbezpieczony;
    }

    public Wlasciciel getiDWlasiciela() {
        return wlasciciel;
    }

    public void setiDWlasiciela(Wlasciciel wlasciciel) {
        this.wlasciciel = wlasciciel;
    }
}

