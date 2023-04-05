package ca.bcit.comp2522.termproject.graphicalrpg;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * ExtraController. This class is the controller for the buttons.
 *
 * @author Emily & Sean
 * @version 2023/04/09
 */
public class ExtraController {

    /**
     * The load game button.
     */
    @FXML
    private Button loadButton;

    /**
     * Starts a new game.
     */
    @FXML
    public void startButton() {
        Main.switchScene(Main.mapScene);
    }

    /**
     * Exits the game.
     */
    @FXML
    public void exitButton() {
        System.exit(0);
    }

    /**
     * Restarts the game by loading the start scene.
     */
    @FXML
    public void restartButton() {
        Main.switchScene(Main.startScene);
    }

    /**
     * Loads the game.
     * @throws IOException if the file is not found
     * @throws ClassNotFoundException if the class is not found
     */
    public void load() throws IOException, ClassNotFoundException {
        System.out.println("Loading");
        FileInputStream fileIn = new FileInputStream("src\\main\\java\\ca\\bcit\\comp2522"
                + "\\termproject\\graphicalrpg\\playerInfo.ser");
        ObjectInputStream objIn = new ObjectInputStream(fileIn);
        Player loadedPlayer = (Player) objIn.readObject();
        objIn.close();
        fileIn.close();

        Main.loadPlayer(loadedPlayer);
    }

    /**
     * Sets the load button to be disabled if there is no save file.
     */
    public void setLoadButton() {
        File file = new File("src\\main\\java\\ca\\bcit\\comp2522"
                + "\\termproject\\graphicalrpg\\playerInfo.ser");
        loadButton.setDisable(!file.exists());
    }
}
