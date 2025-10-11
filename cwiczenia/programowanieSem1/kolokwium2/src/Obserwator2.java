public class Obserwator2 implements Obserwator {
    int liczbaSlow;
    @Override
    public void update(String stan) {
        liczbaSlow += stan.split(" ").length;
        System.out.println(liczbaSlow);
    }
}
