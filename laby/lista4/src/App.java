import jedzenie.*;

import java.util.ArrayList;
import java.util.List;
public class App {
    public static void main(String[] args) throws Exception {
        List<String> chinskieSmaki = new ArrayList<>();
        List<String> chinskieDania = new ArrayList<>();
        List<String> polskaWymowa = new ArrayList<>();
        chinskieSmaki.add("ostry");
        chinskieSmaki.add("słony");
        chinskieSmaki.add("słodki");
        chinskieDania.add("gōng bǎo jī dīng");
        chinskieDania.add("běi jīng kǎo yā");
        chinskieDania.add("lǔ dàn");
        chinskieDania.add("chá yè dàn");
        polskaWymowa.add("Kung Pao chicken");
        polskaWymowa.add("Peking Duck");
        polskaWymowa.add("Soy egg"); 
        polskaWymowa.add("Tea egg");
        KuchniaChinska chinska = new KuchniaChinska(chinskieSmaki, chinskieDania, polskaWymowa, "Hebei", true, false, 7);
        List<String> polskieSmaki = new ArrayList<>();
        List<String> polskieDania = new ArrayList<>();
        List<String> zupy = new ArrayList<>();
        polskieSmaki.add("słony");
        polskieSmaki.add("kwaśny");
        polskieSmaki.add("słodki");
        polskieDania.add("Schabowy");
        polskieDania.add("Rolada wołowa");
        polskieDania.add("Kiełbasy");
        polskieDania.add("Zapiekanka");
        polskieDania.add("Bigos");
        polskieDania.add("Pierogi");
        zupy.add("Rosół");
        zupy.add("Pomidorowa");
        zupy.add("Żurek");
        zupy.add("Jarzynowa");
        zupy.add("Barszcz");
        KuchniaPolska  polska = new KuchniaPolska(polskieSmaki, polskieDania, false, true, 9, zupy, 4);
        Zjedz.mniam(polska);
        Zjedz.mniam(chinska);
        Jedzenie [] jedzenie = new Jedzenie[2];
        jedzenie[0] = chinska;
        jedzenie[1] = polska;
    }
}
