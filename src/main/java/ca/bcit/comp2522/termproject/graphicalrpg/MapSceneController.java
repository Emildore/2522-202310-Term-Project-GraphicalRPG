package ca.bcit.comp2522.termproject.graphicalrpg;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MapSceneController implements Initializable {

    @FXML
    private static AnchorPane mapPane;

    private static Scene comScene;

    @FXML
    private ImageView playerSprite;

    @FXML
    private Button exitButton;

    private Player player;

    private double thresholdRX;
    private double thresholdRY;

    private double thresholdLX;

    private double thresholdLY;

    private double thresholdBX;
    private double thresholdBY;

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

        thresholdBX = -90;
        thresholdBY = -300;
    }

    public void updatePlayerSpritePosition() {
        playerSprite.setX(player.getX());
        playerSprite.setY(player.getY());
    }

    public void setPlayer() {
        player = Main.player;
        updatePlayerSpritePosition();
    }
    public void checkCombatTrigger() {
        if (player.getX() >= (thresholdRX - 10) && player.getX() <= (thresholdRX + 10)
                && player.getY() >= (thresholdRY - 10) && player.getY() <= (thresholdRY + 10)) {
            System.out.println("Switching to combat scene\nMinion Fight");
            switchToCombat();
            player.setX(player.getX()-10);
            player.setY(player.getY()-10);
        } else if (player.getX() >= (thresholdLX - 10) && player.getX() <= (thresholdLX + 10)
                && player.getY() >= (thresholdLY - 10) && player.getY() <= (thresholdLY + 10)) {
            System.out.println("Switching to combat scene\nMinion Fight");
            switchToCombat();
            player.setX(player.getX()+10);
            player.setY(player.getY()-10);
        } else if (player.getX() >= (thresholdBX - 10) && player.getX() <= (thresholdBX + 10)
                && player.getY() >= (thresholdBY - 10) && player.getY() <= (thresholdBY + 10)) {
            System.out.println("Switching to combat scene\nBoss Fight");
            switchToBossCombat();
            player.setX(player.getX()+10);
            player.setY(player.getY()+10);
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

    private void switchToBossCombat() {
        Combat combat = new Combat(player, new Boss("Boss"));
        CombatSceneController.setCombat(combat);
        Main.getMainStage().setScene(comScene);
    }

    public static void setComScene(Scene nComScene) {
        comScene = nComScene;
    }

    @FXML
    public void exitMap() {
        System.exit(0);
    }


    // SAVING
    public void save() throws IOException {
        FileOutputStream fileOut = new FileOutputStream("src\\main\\java\\ca\\bcit\\comp2522"
                + "\\termproject\\graphicalrpg\\playerInfo.ser");
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
        objOut.writeObject(player);
        objOut.close();
        fileOut.close();
        System.out.println("Saved");
    }
}
