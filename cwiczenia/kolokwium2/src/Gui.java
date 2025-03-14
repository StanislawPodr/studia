import javax.swing.*;
import java.awt.*;

public class Gui {

    private static IOManager manager;

    private Gui() {
    }

    public static void createGui() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setBounds(50, 10, 400, 300);
        //panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new Label("Ścieżka dostępu do pliku"));
        TextField sciezkaTextField = new TextField();
        panel.add(sciezkaTextField);
        panel.add(new Label("Wynik"));
        TextField wynikTextField = new TextField();
        wynikTextField.setEditable(false);
        wynikTextField.setText("Negatywny");
        panel.add(wynikTextField);
        JButton przycisk = new JButton("JButton");
        przycisk.setPreferredSize(new Dimension(100, 50));
        przycisk.addActionListener(e -> {
            wynikTextField.setText("Negatywny");
            manager = new IOManager(sciezkaTextField.getText());
            manager.addObserwator(new Obserwator1());
            manager.addObserwator(new Obserwator2());
            manager.addObserwator(new Obserwator3());
            try {
                manager.wczytajPlik();
            } catch (TerroristException ex) {
                wynikTextField.setText("Pozytywny");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        panel.add(przycisk);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
