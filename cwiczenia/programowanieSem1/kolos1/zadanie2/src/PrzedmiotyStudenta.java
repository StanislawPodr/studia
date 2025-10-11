public class PrzedmiotyStudenta {
    private String przedmiot;
    private String wykladowca;
    private String cwiczeniowiec;

    public PrzedmiotyStudenta(String przedmiot, String wykladowca, String cwiczeniowiec) {
        this.przedmiot = przedmiot;
        this.wykladowca = wykladowca;
        this.cwiczeniowiec = cwiczeniowiec;
    }

    public void setPrzedmiot(String przedmiot) {
        this.przedmiot = przedmiot;
    }

    public String getPrzedmiot() {
        return przedmiot;
    }

    public void setWykladowca(String wykladowca) {
        this.wykladowca = wykladowca;
    }

    public void setCwiczeniowiec(String cwiczeniowiec) {
        this.cwiczeniowiec = cwiczeniowiec;
    }

    public String getWykladowca() {
        return wykladowca;
    }

    public String getCwiczeniowiec() {
        return cwiczeniowiec;
    }
}
