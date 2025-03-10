package uczelnia.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import uczelnia.App;
import uczelnia.osoby.Kursy;
import uczelnia.osoby.Osoba;
import uczelnia.osoby.PracownikAdministracyjny;
import uczelnia.osoby.PracownikBadawczoDydaktyczny;
import uczelnia.osoby.PrzynaleznoscPol;
import uczelnia.osoby.Student;
import uczelnia.osoby.add.OsobyList;

public class AddOsobaController implements Initializable {
    private PrzynaleznoscPol typOsoby;
    private Osoba osobaDodawana;

    @FXML
    private TextField imie;

    @FXML
    private TextField nazwisko;

    @FXML
    private TextField pesel;

    @FXML
    private TextField wiek;

    @FXML
    private TextField plec;

    @FXML
    private TextField stanowisko;

    @FXML
    private TextField staz;

    @FXML
    private TextField pensja;

    @FXML
    private TextField publikacje;

    @FXML
    private TextField nadgodziny;

    @FXML
    private GridPane gridPane;

    @FXML
    private TextField indeks;

    @FXML
    private TextField rokStudiow;

    @FXML
    private CheckBox erasmus;

    @FXML
    private CheckBox inzynier;

    @FXML
    private CheckBox magister;

    @FXML
    private CheckBox stacjonarnie;

    @FXML
    private CheckBox nieStacjonarnie;

    private int n = 2;

    public PrzynaleznoscPol getTypOsoby() {
        return typOsoby;
    }

    public void setTypOsoby(PrzynaleznoscPol typOsoby) {
        this.typOsoby = typOsoby;
    }

    @FXML
    public void dodajOsobe() {
        if (typOsoby == null) {
            Stage stage = (Stage) imie.getScene().getWindow();
            stage.close();
            return;
        }

        if (imie.getText().isEmpty() || nazwisko.getText().isEmpty() || pesel.getText().isEmpty()
                || wiek.getText().isEmpty()
                || plec.getText().isEmpty()) {
            return;
        }

        osobaDodawana = new Osoba(imie.getText(), nazwisko.getText(), pesel.getText(), Integer.parseInt(wiek.getText()),
                plec.getText());
        switch (typOsoby) {
            case STUDENT:
                zaladujSceneStudenta();
                break;
            case PRACOWNIK_ADMINISTRACYJNY:
                zmienScene("dodajPracownikaAdministracyjnego.fxml");
                staz.setTextFormatter(App.getIntegerTextFormatter());
                pensja.setTextFormatter(App.getIntegerTextFormatter());
                nadgodziny.setTextFormatter(App.getIntegerTextFormatter());
                break;
            case PRACOWNIK_BADAWCZO_DYDAKTYCZNY:
                zmienScene("dodajPracownikaBadawczoDydaktycznego.fxml");
                staz.setTextFormatter(App.getIntegerTextFormatter());
                pensja.setTextFormatter(App.getIntegerTextFormatter());
                publikacje.setTextFormatter(App.getIntegerTextFormatter());
                break;
            default: break;
        }
    }

    private void zmienScene(String plikFxml) {
        Stage stage = (Stage) imie.getScene().getWindow();
        try {
            FXMLLoader newRoot = new FXMLLoader(getClass().getResource("/uczelnia/" + plikFxml));
            newRoot.setController(this);
            Scene newScene = new Scene(newRoot.load());

            stage.setScene(newScene);
        } catch (Exception e) {
            App.showAlert("Błąd aplikacji", "Wystąpił błąd podczas zmiany sceny.");
            stage.close();
            e.printStackTrace();
        }
    }

    @FXML
    private void dodajBadawczego() {
        if (stanowisko.getText().isEmpty() || staz.getText().isEmpty() || pensja.getText().isEmpty()
                || publikacje.getText().isEmpty()) {
            return;
        }

        PracownikBadawczoDydaktyczny pracownik = new PracownikBadawczoDydaktyczny(osobaDodawana.getImie(),
                osobaDodawana.getNazwisko(), osobaDodawana.getPesel(), osobaDodawana.getWiek(), osobaDodawana.getPlec(),
                stanowisko.getText(), Integer.parseInt(staz.getText()), Integer.parseInt(pensja.getText()),
                Integer.parseInt(publikacje.getText()));

        if (!OsobyList.getInstance().add(pracownik)) {
            App.showAlert("Błąd", "Jest już taki pracownik.");
        }
        Stage stage = (Stage) stanowisko.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void dodajAdministracyjnego() {
        if (stanowisko.getText().isEmpty() || staz.getText().isEmpty() || pensja.getText().isEmpty()
                || nadgodziny.getText().isEmpty()) {
            return;
        }

        PracownikAdministracyjny pracownik = new PracownikAdministracyjny(osobaDodawana.getImie(),
                osobaDodawana.getNazwisko(), osobaDodawana.getPesel(), osobaDodawana.getWiek(), osobaDodawana.getPlec(),
                stanowisko.getText(), Integer.parseInt(staz.getText()), Integer.parseInt(pensja.getText()),
                Integer.parseInt(nadgodziny.getText()));

        if (!OsobyList.getInstance().add(pracownik)) {
            App.showAlert("Błąd", "Jest już taki pracownik.");
        }
        Stage stage = (Stage) stanowisko.getScene().getWindow();
        stage.close();
    }

    private void zaladujSceneStudenta() {
        Stage stage = (Stage) imie.getScene().getWindow();
        try {
            FXMLLoader newRoot = new FXMLLoader(getClass().getResource("/uczelnia/dodajStudenta.fxml"));
            newRoot.setController(this);
            Scene newScene = new Scene(newRoot.load());
            int i = 0;
            for (Kursy kurs : App.listaKursow) {
                CheckBox kursCheckBox = new CheckBox(kurs.toString());
                kursCheckBox.setUserData(kurs);
                gridPane.add(kursCheckBox, i % n, i / n);
                i++;
            }
            rokStudiow.setTextFormatter(App.getIntegerTextFormatter());
            indeks.setTextFormatter(App.getIntegerTextFormatter());
            stage.setScene(newScene);
        } catch (Exception e) {
            App.showAlert("Błąd aplikacji", "Wystąpił błąd podczas zmiany sceny.");
            stage.close();
            e.printStackTrace();
        }
    }

    @FXML
    private void dodajStudenta() {
        List<Kursy> listaKursow = new ArrayList<>();
        for (Node node : gridPane.getChildren()) {
            if (App.listaKursow.contains(node.getUserData()) && ((CheckBox) node).isSelected()) {
                listaKursow.add((Kursy) node.getUserData());
            }
        }

        if (indeks.getText().isEmpty() || rokStudiow.getText().isEmpty()
                || (!stacjonarnie.isSelected() && !nieStacjonarnie.isSelected())
                || (!magister.isSelected() && !inzynier.isSelected()) || listaKursow.isEmpty()) {
            return;
        }

        Student student = new Student(osobaDodawana.getImie(), osobaDodawana.getNazwisko(), osobaDodawana.getPesel(),
                osobaDodawana.getWiek(), osobaDodawana.getPlec(), Integer.parseInt(indeks.getText()),
                Integer.parseInt(rokStudiow.getText()), listaKursow, erasmus.isSelected(), inzynier.isSelected(),
                magister.isSelected(), stacjonarnie.isSelected(), nieStacjonarnie.isSelected());

        if (!OsobyList.getInstance().add(student)) {
            App.showAlert("Błąd", "Jest już taki student.");
        }
        ((Stage) indeks.getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        wiek.setTextFormatter(App.getIntegerTextFormatter());
        pesel.setTextFormatter(App.getIntegerTextFormatter());
    }
}
