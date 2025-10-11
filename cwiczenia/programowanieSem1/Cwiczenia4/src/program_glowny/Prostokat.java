package program_glowny;
public class Prostokat {
    private double x1, y1;
    private double x2, y2;
    private double pole;
    private double obwod;
    private double przekatna;
    private double x, y;

    private void obliczPole() {
        pole = x * y;
    }

    private void obliczObwod() {
        obwod = 2 * x + 2 * y;
    }

    private void obliczPrzekatna() {
        przekatna = Math.sqrt(x * x + y * y);
    }

    public Prostokat(int x1, int y1, int x2, int y2) throws Exception {
        if (x1 == x2 || y1 == y2) {
            throw new Exception("Punkty nie mogą mieć tych samych współrzędnych");
        }
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        x = Math.abs(x1 - x2);
        y = Math.abs(y1 - y2);
        obliczObwod();
        obliczPole();
        obliczPrzekatna();
    }

    public Prostokat() throws Exception {
        this(0, 0, 1, 1);
    }
}
