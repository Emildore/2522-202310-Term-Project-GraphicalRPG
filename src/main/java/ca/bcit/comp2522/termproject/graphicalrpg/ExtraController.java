package ca.bcit.comp2522.termproject.graphicalrpg;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ExtraController {
    @FXML
    private Button startButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button exitButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button restartButton;
    @FXML
    public void startButton() throws IOException {
        Main.setPlayer();
        Main.switchScene(Main.mapScene);
    }
    @FXML
    public void exitButton() {
        System.exit(0);
    }
    @FXML
    public void restartButton() {
        Main.switchScene(Main.startScene);
    }


}
