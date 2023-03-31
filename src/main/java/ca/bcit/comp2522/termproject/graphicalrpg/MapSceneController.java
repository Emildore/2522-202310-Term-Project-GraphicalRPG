package ca.bcit.comp2522.termproject.graphicalrpg;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MapSceneController implements Initializable {

    @FXML
    public static AnchorPane mapPane;

    @FXML
    private ImageView playerSprite;

    @FXML
    private Group mapGroup;
    @FXML
    private Button exitButton;

    @FXML
    private Button saveButton;

    private Player player;

    static Scene comScene;
    private double thresholdRX;
    private double thresholdRY;

    private double thresholdLX;

    private double thresholdLY;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the player object and save their initial position
        player = Main.player;
        updatePlayerSpritePosition();

        // Define the combat trigger area
        thresholdRX = 360;
        thresholdRY = -210;

        thresholdLX = -290;
        thresholdLY = -210;
    }

    public void updatePlayerSpritePosition() {
        playerSprite.setX(player.getX());
        playerSprite.setY(player.getY());
    }

    public void checkCombatTrigger() {
        if (player.getX() >= (thresholdRX - 10) && player.getX() <= (thresholdRX + 10)
                && player.getY() >= (thresholdRY - 10) && player.getY() <= (thresholdRY + 10)) {
            System.out.println("Switching to combat scene");
            switchToCombat();
            player.setX(player.getX()-10);
            player.setY(player.getY()-10);
        } else if (player.getX() >= (thresholdLX - 10) && player.getX() <= (thresholdLX + 10)
                && player.getY() >= (thresholdLY - 10) && player.getY() <= (thresholdLY + 10)) {
            System.out.println("Switching to combat scene");
            switchToCombat();
            player.setX(player.getX()+10);
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

    @FXML
    public void exitMap() {
        System.exit(0);
    }

}

