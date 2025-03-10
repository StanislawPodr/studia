public class SprawdzaczTerroryzmu implements Obserwator {
    @Override
    public void update(Obserwowany obserwowany) throws Exception {
        if (obserwowany.getStan().contains("bomba") || obserwowany.getStan().contains("zamach")
                || obserwowany.getStan().contains("pistolet")) {
            throw new Exception("Znaleziono terrorystę");
        }

    }
}
