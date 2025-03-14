package jedzenie;

public class BarOrientalny {
    KuchniaChinska[] menuChinskie;
    KuchniaPolska[] menuPolskie;

    public BarOrientalny(KuchniaChinska[] menuChinskie, KuchniaPolska[] menuPolskie) {
        this.menuChinskie = menuChinskie;
        this.menuPolskie = menuPolskie;
    }

    public KuchniaChinska[] getMenuChinskie() {
        return menuChinskie;
    }

    public KuchniaPolska[] getMenuPolskie() {
        return menuPolskie;
    }

}
