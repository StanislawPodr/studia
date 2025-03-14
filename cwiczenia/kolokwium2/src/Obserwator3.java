public class Obserwator3 implements Obserwator {

    @Override
    public void update(String stan) throws Exception {
        if(stan.contains("pistolet") || stan.contains("bomba") || stan.contains("zamach")) {
            throw new TerroristException();
        }
    }

}
