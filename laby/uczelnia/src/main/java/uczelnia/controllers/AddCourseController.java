package uczelnia.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import uczelnia.osoby.Kursy;
import uczelnia.App;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

public class AddCourseController implements Initializable{

    @FXML
    private TextField nazwaKursu;
    @FXML
    private TextField prowadzacy;
    @FXML
    private TextField punktyEcts;

     @FXML
    public void dodajKurs() {
        if(nazwaKursu.getText().isEmpty() || prowadzacy.getText().isEmpty() || punktyEcts.getText().isEmpty()){
            return;
        }
        Stage stage = (Stage) nazwaKursu.getScene().getWindow();

        Kursy dodawanyKurs = new Kursy(nazwaKursu.getText(), prowadzacy.getText(), Integer.parseInt(punktyEcts.getText()));

        if(App.listaKursow.contains(dodawanyKurs)) {
            App.showAlert("Błąd", "Istnieje już identyczny kurs");
            stage.close();
            return;
        }
        
        App.listaKursow.add(dodawanyKurs);
        App.zapisz(new ArrayList<Kursy>(App.listaKursow), "kursy.ser");
        stage.close();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        TextFormatter<Integer> formatter = App.getIntegerTextFormatter();
        punktyEcts.setTextFormatter(formatter);
    }


}
