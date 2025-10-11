package uczelnia.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import uczelnia.App;
import uczelnia.osoby.Kursy;
import uczelnia.osoby.Obserwator;
import uczelnia.osoby.ObserwatorusunieciaKursu;
import uczelnia.osoby.Osoba;
import uczelnia.osoby.Pracownik;
import uczelnia.osoby.Student;
import uczelnia.osoby.add.OsobyList;

public class SearchController implements Initializable {

    @FXML
    ComboBox<String> selectionComboBox;
    @FXML
    TextField searchField;
    @FXML
    ListView<Object> wyszukano;

    ObservableList<Object> wyszukanoList;

    private Obserwator obserwator = new ObserwatorusunieciaKursu();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        selectionComboBox.getItems().addAll("Imie", "Nazwisko", "Stanowisko", "Staż pracy", "Pensja", "nr indeksu",
                "rok studiów", "nazwa kursu", "prowadzący kurs", "punkty ECTS");
        wyszukano.setItems(FXCollections.observableArrayList(OsobyList.getInstance().getLista()));
    }

    @FXML
    private void usunGrupe() {
        boolean zmienionoKursy = false;
        boolean zmienionoOsoby = false;
        for (Object o : wyszukano.getItems()) {
            if (o instanceof Osoba osoba) {
                zmienionoOsoby = true;
                OsobyList.getInstance().removeWithoutSaving(osoba);
            } else if (o instanceof Kursy kurs) {
                zmienionoKursy = true;
                App.listaKursow.remove(kurs);
                obserwator.update(kurs);
            }
        }
        if (zmienionoKursy) {
            App.zapisz(new ArrayList<Kursy>(App.listaKursow), "kursy.ser");
        }
        if (zmienionoOsoby) {
            App.zapisz(new ArrayList<Osoba>(OsobyList.getInstance().getLista()), OsobyList.getInstance().getPlik());
        }
       ((Stage) selectionComboBox.getScene().getWindow()).close();
    }

    @FXML
    private void szukaj() {
        List<Osoba> lista = OsobyList.getInstance().getLista();
        List<Student> studenci = OsobyList.getInstance().getListaStudentow();
        List<Pracownik> pracownicy = OsobyList.getInstance().getListaPracownikow();
        List<Kursy> kursy = App.listaKursow;

        switch (selectionComboBox.getValue()) {
            case "Imie":
                wyszukano.setItems(FXCollections.observableArrayList(
                        Osoba.searchByImie(lista, searchField.getText())));
                break;
            case "Nazwisko":
                wyszukano.setItems(FXCollections.observableArrayList(
                        Osoba.searchByNazwisko(lista, searchField.getText())));
                break;
            case "Stanowisko":
                wyszukano.setItems(FXCollections.observableArrayList(
                        Pracownik.searchByStanowisko(pracownicy, searchField.getText())));
                break;
            case "Staż pracy":
                wyszukano.setItems(FXCollections.observableArrayList(
                        Pracownik.searchByStazPracy(pracownicy, Integer.parseInt(searchField.getText()))));
                break;
            case "Pensja":
                wyszukano.setItems(FXCollections.observableArrayList(
                        Pracownik.searchByPensja(pracownicy, Integer.parseInt(searchField.getText()))));
                break;
            case "nr indeksu":
                wyszukano.setItems(FXCollections.observableArrayList(
                        Student.searchByNrIndeksu(studenci, Integer.parseInt(searchField.getText()))));
                break;
            case "rok studiów":
                wyszukano.setItems(FXCollections.observableArrayList(
                        Student.searchByRokStudiow(studenci, Integer.parseInt(searchField.getText()))));
                break;
            case "nazwa kursu":
                wyszukano.setItems(FXCollections.observableArrayList(
                        Kursy.searchByNazwaKursu(kursy, searchField.getText())));
                break;
            case "prowadzący kurs":
                wyszukano.setItems(FXCollections.observableArrayList(
                        Kursy.searchByProwadzacy(kursy, searchField.getText())));
                break;
            case "punkty ECTS":
                wyszukano.setItems(FXCollections.observableArrayList(
                        Kursy.searchByPunktyEcts(kursy, Integer.parseInt(searchField.getText()))));
                break;
        }
    }

    @FXML
    private void zmianaParametruWyszukiwania() {
        if (selectionComboBox.getValue() == "Staż pracy" || selectionComboBox.getValue() == "Pensja"
                || selectionComboBox.getValue() == "nr indeksu" || selectionComboBox.getValue() == "rok studiów"
                || selectionComboBox.getValue() == "punkty ECTS") {
            searchField.setTextFormatter(App.getIntegerTextFormatter());
        } else {
            searchField.setTextFormatter(null);
        }
    }

}
