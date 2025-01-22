public class ZliczaczSlow implements Obserwator {
    int ileSlow = 0;
    @Override
    public void update(Obserwowany obserwowany) {
        String[] slowa = obserwowany.getStan().split("\\s");
        ileSlow += slowa.length;
        if (obserwowany.getEndOfFile()) {
            System.out.println("Liczba slow: " + ileSlow);
        }
    }
}
