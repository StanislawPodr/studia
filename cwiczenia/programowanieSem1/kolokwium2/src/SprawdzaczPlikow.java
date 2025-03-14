import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SprawdzaczPlikow implements Obserwowany {
    private List<Obserwator> obserwatorzy = new ArrayList<>();
    private String stan = "";
    private boolean endOfFile = true;

    public SprawdzaczPlikow() {
        dodajObserwatora(new WyswietlWiersz());
        dodajObserwatora(new ZliczaczSlow());
        dodajObserwatora(new SprawdzaczTerroryzmu());
    }

    public String sprawdzPliki(String nazwaPliku) {
        File plik = new File("src/" + nazwaPliku);
        try (Scanner odczyt = new Scanner(plik)){
            endOfFile = !odczyt.hasNextLine();
            while(!endOfFile) {
                stan = odczyt.nextLine();
                if(!odczyt.hasNextLine()) {
                    endOfFile = true;
                }
                notifyObserwatorzy();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return "Negatywny";
        }
        catch(Exception e) {
            return "Pozytywny";
        }
        return "Negatywny";
    }

    public void notifyObserwatorzy() throws Exception{
        for(Obserwator obserwator : obserwatorzy) {
            obserwator.update(this);
        }
    }

    public void dodajObserwatora(Obserwator obserwator) {
        obserwatorzy.add(obserwator);
    }

    public void usunObserwatora(int index) {
        obserwatorzy.remove(index);
    }

    @Override
    public String getStan() {
        return stan;
    }

    @Override
    public boolean getEndOfFile() {
        return endOfFile;
    }
}
