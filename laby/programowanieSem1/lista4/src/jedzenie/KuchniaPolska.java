package jedzenie;

import java.util.List;

public class KuchniaPolska extends Jedzenie {
    List<String> zupy;
    int ileDanZZiemniakami;

    public KuchniaPolska(List<String> smaki, List<String> dania, boolean czyOstre, boolean czyKwasne,
            int ocenaEkspertow,
            List<String> zupy, int ileDanZZiemniakami) {
        super(smaki, dania, czyOstre, czyKwasne, ocenaEkspertow);
        this.zupy = zupy;
        this.ileDanZZiemniakami = ileDanZZiemniakami;
    }

    public List<String> getZupy() {
        return zupy;
    }

    public int getIleDanZZiemniakami() {
        return ileDanZZiemniakami;
    }

}
