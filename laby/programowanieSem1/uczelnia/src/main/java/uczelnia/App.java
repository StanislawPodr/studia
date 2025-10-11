package uczelnia;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import uczelnia.osoby.add.OsobyList;
import uczelnia.controllers.AddOsobaController;
import uczelnia.osoby.Kursy;
import uczelnia.osoby.Obserwator;
import uczelnia.osoby.ObserwatorusunieciaKursu;
import uczelnia.osoby.Osoba;
import uczelnia.osoby.Pracownik;
import uczelnia.osoby.PrzynaleznoscPol;
import uczelnia.osoby.Student;

public class App extends Application implements Serializable {

    private static Scene scene;
    public static ObservableList<Kursy> listaKursow;
    private Obserwator obserwator = new ObserwatorusunieciaKursu();

    @FXML
    private ListView<Osoba> osoby;

    @FXML
    private ComboBox<String> searchComboBox;

    @FXML
    private TextField searchTextField;

    @FXML
    private ListView<Pracownik> pracownicy;

    @FXML
    private ListView<Student> studenci;

    @FXML
    private ListView<Kursy> kursy;

    @FXML
    private ComboBox<String> selectionComboBox;

    @Override
    public void start(Stage stage) throws IOException {
        Object loadedObject = wczytaj(listaKursow, "kursy.ser");
        if (loadedObject instanceof ArrayList<?> k) {
            List<Kursy> kursyArrayList = new ArrayList<Kursy>();
            for (Object kurs : k) {
                if (kurs instanceof Kursy) {
                    kursyArrayList.add((Kursy) kurs);
                }
            }
            listaKursow = FXCollections.observableArrayList(kursyArrayList);
        } else {
            listaKursow = FXCollections.observableArrayList();
            showAlert("zepsuty plik kursy.ser", "Usuń ten plik lub ponownie dodaj kursy");
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        loader.setController(this);
        Parent root = loader.load();

        osoby.setItems(OsobyList.getInstance().getLista());
        kursy.setItems(listaKursow);
        pracownicy.setItems(OsobyList.getInstance().getListaPracownikow());
        studenci.setItems(OsobyList.getInstance().getListaStudentow());

        osoby.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Osoba dane = osoby.getSelectionModel().getSelectedItem();
                addPrzyciskUsun(okienkoZDanymi(dane.wypiszDane(), dane.toString()),
                        () -> OsobyList.getInstance().remove(dane)).show();
            }
        });

        kursy.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Kursy dane = kursy.getSelectionModel().getSelectedItem();
                addPrzyciskUsun(okienkoZDanymi(wypiszDane(dane), dane.toString()),
                        () -> {
                            listaKursow.remove(dane);
                            obserwator.update(dane);
                            zapisz(new ArrayList<Kursy>(listaKursow), "kursy.ser");
                            zapisz(new ArrayList<Osoba>(OsobyList.getInstance().getLista()), "osoby.ser");
                        }).show();
            }
        });

        pracownicy.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Pracownik dane = pracownicy.getSelectionModel().getSelectedItem();
                addPrzyciskUsun(okienkoZDanymi(dane.wypiszDane(), dane.toString()),
                        () -> OsobyList.getInstance().remove(dane)).show();
            }
        });

        studenci.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Student dane = studenci.getSelectionModel().getSelectedItem();
                addPrzyciskUsun(okienkoZDanymi(dane.wypiszDane(), dane.toString()),
                        () -> OsobyList.getInstance().remove(dane)).show();
            }
        });

        selectionComboBox.getItems().addAll("Pracownik administracyjny", "Pracownik badawczo-dydaktyczny", "Student",
                "Kurs");

        scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.setTitle("Uczelnia");
        stage.show();
    }

    private Stage addPrzyciskUsun(Stage stage, Runnable action) {
        Pane root = (Pane) stage.getScene().getRoot();
        Button usun = new Button("Usuń");
        usun.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        usun.setOnAction(e -> {
            stage.close();
            action.run();
        });
        root.getChildren().add(usun);
        return stage;
    }

    private Stage okienkoZDanymi(String dane, String tytul) {
        try {
            Stage stage = new Stage();
            stage.setTitle(tytul);
            stage.setScene(new Scene(new VBox(10, new javafx.scene.control.Label(dane)), 400, 400));
            return stage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void dodajDoListy() {
        if (selectionComboBox.getValue() == null) {
            return;
        }

        switch (selectionComboBox.getValue()) {
            case "Pracownik administracyjny":
                handleAddPerson(PrzynaleznoscPol.PRACOWNIK_ADMINISTRACYJNY);
                break;
            case "Pracownik badawczo-dydaktyczny":
                handleAddPerson(PrzynaleznoscPol.PRACOWNIK_BADAWCZO_DYDAKTYCZNY);
                break;
            case "Student":
                handleAddPerson(PrzynaleznoscPol.STUDENT);
                break;
            case "Kurs":
                handleAddCourse();
                break;

            default:
                break;
        }
    }

    @FXML
    private void handleAddPerson(PrzynaleznoscPol typOsoby) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dodajOsobe.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Dodaj Osobę");
            stage.setScene(new Scene(root));
            AddOsobaController controller = (AddOsobaController) loader.getController();
            controller.setTypOsoby(typOsoby);
            stage.show();
        } catch (Exception e) {
            showAlert("Błąd", "Wystąpił błąd przy otwieraniu okna dodawania osoby.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddCourse() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dodajKurs.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Dodaj Kurs");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            showAlert("Błąd", "Wystąpił błąd przy otwieraniu okna dodawania kursu.");
            e.printStackTrace();
        }
    }

    public static void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }

    public static TextFormatter<Integer> getIntegerTextFormatter() {
        return new TextFormatter<>(
                new IntegerStringConverter(), 0, change -> {
                    if (change.getText().matches("[0-9]*")) {
                        return change;
                    }
                    return null;
                });
    }

    public static String wypiszDane(Object o) {
        StringBuilder dane = new StringBuilder();
        Class<?> clazz = o.getClass();
        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(o);
                    dane.append(field.getName())
                            .append(": ")
                            .append(value != null ? value : "null")
                            .append("\n");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            clazz = clazz.getSuperclass();
        }
        return dane.toString();
    }

    public static void zapisz(Object o, String nazwaPliku) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nazwaPliku))) {
            out.writeObject(o);
        } catch (IOException e) {
            showAlert(nazwaPliku, "Nie udało się zapisać danych do pliku. Spróbuj ponownie.");
            e.printStackTrace();
        }
    }

    public static Object wczytaj(Object o, String nazwaPliku) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nazwaPliku))) {
            o = in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            showAlert(nazwaPliku, "Nie udało się wczytać danych z pliku. Upewnij się, że plik nie jest uszkodzony.");
            e.printStackTrace();
        }
        return o;
    }

    @FXML
    private void szukaj() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("szukaj.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Wyszukiwanie i usuwanie");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            showAlert("Błąd", "Wystąpił błąd przy otwieraniu okna wyszukiwania.");
            e.printStackTrace();
        }
    }

    @FXML
    private void sortujOsoby() {
        Osoba.sortByNazwiskoIImie(OsobyList.getInstance().getLista());
        Osoba.sortByNazwiskoIImie(OsobyList.getInstance().getListaPracownikow());
        Osoba.sortByNazwiskoIImie(OsobyList.getInstance().getListaStudentow());
    }

    @FXML
    private void sortujKursy() {
        Kursy.sortKursy(listaKursow);
    }

}