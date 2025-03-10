public class WyswietlWiersz implements Obserwator {
    @Override
    public void update(Obserwowany obserwowany) {
        System.out.println(obserwowany.getStan());
    }
}
