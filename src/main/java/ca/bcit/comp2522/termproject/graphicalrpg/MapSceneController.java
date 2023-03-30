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
        thresholdX = 280;
        thresholdY = 50;
    }

    public void updatePlayerSpritePosition() {
        playerSprite.setX(player.getX());
        playerSprite.setY(player.getY());
    }

    public void checkCombatTrigger() {
        if (player.getX() >= (thresholdX - 10) && player.getX() <= (thresholdX + 10)
                && player.getY() >= (thresholdY - 10) && player.getY() <= (thresholdY + 10)) {
            System.out.println("Switching to combat scene");
            switchToCombat();
            player.setX(player.getX()-10);
            player.setY(player.getY()-10);
        }
    }

    private void switchToCombat() {
        if (player.getEnemyWins() != 0) {
            Combat combat = new Combat(player, new Enemy("Enemy"));
            CombatSceneController.setCombat(combat);
            Main.getMainStage().setScene(comScene);
        } else {
            Main.getMainStage().setScene(comScene);
        }
    }

    public static void setComScene(Scene nComScene) {
        comScene = nComScene;
    }

}

