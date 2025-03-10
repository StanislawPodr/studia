import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Swing Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel label = new JLabel("Wprowadź dane:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        

        JPanel panel = new JPanel();
        JTextField editableTextField = new JTextField();
        editableTextField.setPreferredSize(new Dimension(200, 30));
        panel.add(editableTextField);

        JButton przycisk = new JButton("Sprawdź plik");
        panel.add(przycisk);
        


        JTextField nonEditableTextField = new JTextField("Negatywny");
        nonEditableTextField.setEditable(false);


        frame.add(label);
        frame.add(panel);
        frame.add(nonEditableTextField);

        SprawdzaczPlikow sprawdz = new SprawdzaczPlikow();

        przycisk.addActionListener(e -> nonEditableTextField.setText(sprawdz.sprawdzPliki(editableTextField.getText())));

        frame.setVisible(true);

    }
}
