module ca.bcit.comp2522.termproject.graphicalrpg {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.bcit.comp2522.termproject.graphicalrpg to javafx.fxml;
    exports ca.bcit.comp2522.termproject.graphicalrpg;
}