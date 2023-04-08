package ca.bcit.comp2522.termproject.graphicalrpg;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * CombatSceneController. This is the controller for the combat scene.
 *
 * @author Emily & Sean
 * @version 2023/04/09
 */
public class CombatSceneController implements Initializable {
    /**
     * The combat.
     */
    static Combat combat;
    /**
     * The main scene.
     */
    static Scene mainScene;
    /**
     * The enemy health bar.
     */
    @FXML
    private ProgressBar enemyHealthBar;
    /**
     * The player health bar.
     */
    @FXML
    private ProgressBar playerHealthBar;
    /**
     * The Power Strike button.
     */
    @FXML
    private Button powerStrike;
    /**
     * The Block button.
     */
    @FXML
    private Button block;
    /**
     * The Recover button.
     */
    @FXML
    private Button recover;
    /**
     * The enemy health.
     */
    private double enemyHealth;
    /**
     * The player health.
     */
    private double playerHealth;

    /**
     * Sets combat.
     * @param nCombat the combat
     */
    public static void setCombat(final Combat nCombat) {
        combat = nCombat;
    }

    /**
     * Sets buttons to be disabled if the player does not have the skill.
     */
    public void setButtons() {
        powerStrike.setDisable(!(combat.getPlayer().getSkills().contains("2")));
        block.setDisable(!(combat.getPlayer().getSkills().contains("3")));
        recover.setDisable(!(combat.getPlayer().getSkills().contains("4")));
    }

    /**
     * Player attack interaction.
     */
    public void atkBut() {
        if (combat.getEnemy() != null && combat.getPlayer() != null) {
            combat.getPlayer().interact("1", combat.getEnemy());
            combatOutcome();
        }
    }

    /**
     * Player power strike interaction.
     */
    public void powerBut() {
        if (combat.getEnemy() != null && combat.getPlayer() != null) {
            combat.getPlayer().interact("2", combat.getEnemy());
            combatOutcome();
        }
    }

    /**
     * Player block interaction.
     */
    public void blockBut() {
        if (combat.getEnemy() != null && combat.getPlayer() != null) {
            combat.getPlayer().interact("3", combat.getEnemy());
            combatOutcome();
        }
    }

    /**
     * Player recover interaction.
     */
    public void recoverBut() {
        if (combat.getEnemy() != null && combat.getPlayer() != null) {
            combat.getPlayer().interact("4", combat.getEnemy());
            combatOutcome();
        }
    }

    /**
     * Combat outcome.
     */
    private void combatOutcome() {
        updateEnemyHealthBar();
        if (combat.checkEnemyStatus()) {
            combat.getPlayer().resetHP();
            combat.getPlayer().haveWon();
            if (combat.getPlayer().getWin()) {
                Main.switchScene(Main.winScene);
                Main.setPlayer();
            } else {
                switchToStart();
            }
            resetHealthBars();
            return;
        }
        combat.enemyATK();
        updatePlayerHealthBar();
        if (combat.checkPlayerHP()) {
            combat.setWinner(combat.getEnemy());
            Main.switchScene(Main.gameOverScene);
            Main.setPlayer();
            resetHealthBars();
        }
    }

    /**
     * Updates the player health bar.
     */
    private void updatePlayerHealthBar() {
        playerHealth = combat.getPlayer().getCurrHP() / combat.getPlayer().getBaseHP();
        playerHealthBar.setProgress(playerHealth);
    }

    /**
     * Updates the enemy health bar.
     */
    private void updateEnemyHealthBar() {
        enemyHealth = combat.getEnemy().getCurrHP() / combat.getEnemy().getBaseHP();
        enemyHealthBar.setProgress(enemyHealth);
    }

    /**
     * Resets the health bars.
     */
    public void resetHealthBars() {
        playerHealthBar.setProgress(1.0);
        enemyHealthBar.setProgress(1.0);
    }

    /**
     * Initializes the controller class.
     * @param url the url
     * @param resourceBundle the resource bundle
     */
    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        if (this.enemyHealthBar != null && combat != null) {
            enemyHealth = combat.getEnemy().getCurrHP();
            enemyHealthBar.setProgress(enemyHealth);

            playerHealth = combat.getPlayer().getCurrHP();
            playerHealthBar.setProgress(playerHealth);
        } else {
            System.out.println("ERROR: Combat or enemyHealthBar is null in CombatSceneController initialize method.");
        }
    }

    /**
     * Switches to the start scene.
     */
    public void switchToStart() {
        Main.getMainStage().setScene(mainScene);
    }

    /**
     * Sets the main scene.
     * @param scene the scene
     */
    public static void setMainScene(final Scene scene) {
        mainScene = scene;
    }
}
