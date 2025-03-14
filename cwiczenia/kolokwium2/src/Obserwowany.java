public interface Obserwowany {
public void notifyObserwatorzy(String stan) throws Exception;
public void deleteObserwator(int indeks);
public void addObserwator(Obserwator obserwator);
}
