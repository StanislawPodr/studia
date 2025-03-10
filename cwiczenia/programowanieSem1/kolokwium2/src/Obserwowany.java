public interface Obserwowany {
    public void dodajObserwatora(Obserwator obserwator);
    public void usunObserwatora(int index);
    public void notifyObserwatorzy() throws Exception;
    public String getStan();
    public boolean getEndOfFile();
}
