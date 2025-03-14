module uczelnia {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.base;

    opens uczelnia to javafx.fxml;
    opens uczelnia.controllers to javafx.fxml;
    exports uczelnia;
    exports uczelnia.osoby;
    exports uczelnia.controllers;
}
