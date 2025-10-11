public class Wykladowca extends Osoba {
    private int lataDoEmerytury;
    private int wiek;

    public Wykladowca(String imie, String nazwisko, int lataDoEmerytury) {
        super(imie, nazwisko);
        this.lataDoEmerytury = lataDoEmerytury;
    }

    public int getLataDoEmerytury() {
        return lataDoEmerytury;
    }

    public void setLataDoEmerytury(int lataDoEmerytury) {
        this.lataDoEmerytury = lataDoEmerytury;
    }

    public int getWiek() {
        return wiek;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }
    
}
