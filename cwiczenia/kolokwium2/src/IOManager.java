import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOManager implements Obserwowany {
    private File plik;
    private List<Obserwator> obserwatorzy;

    public IOManager(String nazwaPliku) {
        plik = new File("src/" + nazwaPliku);
        obserwatorzy = new ArrayList<>();
    }

    public void wczytajPlik() throws Exception {
        try(Scanner in = new Scanner(plik)) {
            while(in.hasNextLine()) {
                notifyObserwatorzy(in.nextLine());
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addObserwator(Obserwator obserwator) {
        obserwatorzy.add(obserwator);
    }

    @Override
    public void deleteObserwator(int indeks) {
        obserwatorzy.remove(indeks);
    }

    public File getPlik() {
        return plik;
    }

    @Override
    public void notifyObserwatorzy(String stan) throws Exception {
        for(Obserwator o : obserwatorzy) {
            o.update(stan);
        }
    }

}
