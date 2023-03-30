package ca.bcit.comp2522.termproject.graphicalrpg;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MapSceneController implements Initializable {

    @FXML
    private AnchorPane mapPane;

    @FXML
    private ImageView playerSprite;

    private Player player;

    static Scene comScene;
    private double thresholdX;
    private double thresholdY;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the player object and save their initial position
        player = Main.player;
        updatePlayerSpritePosition();

        // Define the combat trigger area
        thresholdX = 50;
        thresholdY = 280;
    }

    public void updatePlayerSpritePosition() {
        playerSprite.setX(player.getX());
        playerSprite.setY(player.getY());
    }

    public void checkCombatTrigger() {
        if (player.getX() >= (thresholdX - 225) && player.getX() <= (thresholdX + 225)
                && player.getY() >= (thresholdY - 225) && player.getY() <= (thresholdY + 225)) {
            System.out.println("Switching to combat scene");
            switchToCombat();
        }
    }

    private void switchToCombat() {

       Main.getMainStage().setScene(comScene);
    }

    public static void setComScene(Scene nComScene) {
        comScene = nComScene;
    }

}

