package ca.bcit.comp2522.termproject.graphicalrpg;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.*;

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
    public boolean loadExists = false;

    @FXML
    public void startButton() throws IOException {
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

    // LOAD
    public void load() throws IOException, ClassNotFoundException {
        System.out.println("Loading");
        FileInputStream fileIn = new FileInputStream("src\\main\\java\\ca\\bcit\\comp2522" +
                "\\termproject\\graphicalrpg\\playerInfo.ser");
        ObjectInputStream objIn = new ObjectInputStream(fileIn);
        Player loadedPlayer = (Player) objIn.readObject();
        objIn.close();
        fileIn.close();

        Main.loadPlayer(loadedPlayer);
    }

    public void setLoadButton() {
        File file = new File("src\\main\\java\\ca\\bcit\\comp2522" +
                "\\termproject\\graphicalrpg\\playerInfo.ser");
        if (file.exists()) {
            loadButton.setDisable(false);
        } else {
            loadButton.setDisable(true);
        }
    }
}
