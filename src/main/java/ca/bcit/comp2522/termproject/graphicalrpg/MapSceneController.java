package ca.bcit.comp2522.termproject.graphicalrpg;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * MapSceneController. This is the controller for the map scene.
 *
 * @author Emily & Sean
 * @version 2023/04/09
 */
public class MapSceneController implements Initializable {
    /**
     * The ten.
     */
    private static final int TEN = 10;

    /**
     * The combat scene.
     */
    private static Scene comScene;
    /**
     * The player sprite.
     */
    @FXML
    private ImageView playerSprite;

    /**
     * The player.
     */
    private Player player;
    /**
     * The threshold for the combat trigger.
     */
    private double thresholdRX;
    /**
     * The threshold for the combat trigger.
     */
    private double thresholdRY;
    /**
     * The threshold for the combat trigger.
     */
    private double thresholdLX;
    /**
     * The threshold for the combat trigger.
     */
    private double thresholdLY;
    /**
     * The threshold for the combat trigger.
     */
    private double thresholdBX;
    /**
     * The threshold for the combat trigger.
     */
    private double thresholdBY;

    /**
     * Initializes the controller class.
     * @param url the url
     * @param resourceBundle the resource bundle
     */
    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
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

    /**
     * Updates the player sprite position.
     */
    public void updatePlayerSpritePosition() {
        playerSprite.setX(player.getX());
        playerSprite.setY(player.getY());
    }

    /**
     * Sets the player.
     */
    public void setPlayer() {
        player = Main.player;
        updatePlayerSpritePosition();
    }

    /**
     * Checks if the player is in the combat trigger area.
     */
    public void checkCombatTrigger() {
        if (player.getX() >= (thresholdRX - TEN) && player.getX() <= (thresholdRX + TEN)
                && player.getY() >= (thresholdRY - TEN) && player.getY() <= (thresholdRY + TEN)) {
            System.out.println("Switching to combat scene\nMinion Fight");
            switchToCombat();
            player.setX(player.getX() - TEN);
            player.setY(player.getY() - TEN);
        } else if (player.getX() >= (thresholdLX - TEN) && player.getX() <= (thresholdLX + TEN)
                && player.getY() >= (thresholdLY - TEN) && player.getY() <= (thresholdLY + TEN)) {
            System.out.println("Switching to combat scene\nMinion Fight");
            switchToCombat();
            player.setX(player.getX() + TEN);
            player.setY(player.getY() - TEN);
        } else if (player.getX() >= (thresholdBX - TEN) && player.getX() <= (thresholdBX + TEN)
                && player.getY() >= (thresholdBY - TEN) && player.getY() <= (thresholdBY + TEN)) {
            System.out.println("Switching to combat scene\nBoss Fight");
            switchToBossCombat();
            player.setX(player.getX() + TEN);
            player.setY(player.getY() + TEN);
        }
    }

    /**
     * Switches to the combat scene.
     */
    private void switchToCombat() {
        if (player.getEnemyWins() != 0) {
            Combat combat = new Combat(player, new Enemy("Enemy"));
            CombatSceneController.setCombat(combat);
            Main.getMainStage().setScene(comScene);
        } else {
            Main.getMainStage().setScene(comScene);
        }
    }

    /**
     * Switches to the boss combat scene.
     */
    private void switchToBossCombat() {
        Combat combat = new Combat(player, new Boss("Boss"));
        CombatSceneController.setCombat(combat);
        Main.getMainStage().setScene(comScene);
    }

    /**
     * Sets the combat scene.
     * @param nComScene the combat scene
     */
    public static void setComScene(final Scene nComScene) {
        comScene = nComScene;
    }

    /**
     * Exits the map.
     */
    @FXML
    public void exitMap() {
        System.exit(0);
    }

    /**
     * Saves the game.
     * @throws IOException the io exception
     */
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
