package jedzenie;

import java.util.List;

public class KuchniaChinska extends Jedzenie {
    String prowincja;
    List<String> polskaWymowaNazwDan;
    public KuchniaChinska(List<String> smaki, List<String> dania, List<String> polskaWymowaNazwDan,String prowincja, boolean czyOstre, boolean czyKwasne, int ocenaEkspertow) {
        super(smaki, dania, czyOstre, czyKwasne, ocenaEkspertow);
        this.polskaWymowaNazwDan = polskaWymowaNazwDan;
        this.prowincja = prowincja;
    }
    public String getProwincja() {
        return prowincja;
    }
    public List<String> getPolskaWymowaNazwDan() {
        return polskaWymowaNazwDan;
    }
    
}
