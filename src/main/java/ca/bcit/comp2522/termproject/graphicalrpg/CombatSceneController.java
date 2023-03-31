package ca.bcit.comp2522.termproject.graphicalrpg;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CombatSceneController implements Initializable {
    @FXML
    private ProgressBar enemyHealthBar;
    @FXML
    private ProgressBar playerHealthBar;

    @FXML
    private Button PowerStrike;
    @FXML
    private Button Block;
    @FXML
    private Button Recover;

    private double enemyHealth;
    private double playerHealth;
    static Combat combat;
    static Scene mainScene;

    static public void setCombat(Combat nCombat) {
        combat = nCombat;

    }

    public void setButtons() {
        PowerStrike.setDisable(!(combat.getPlayer().getSkills().contains("2")));
        Block.setDisable(!(combat.getPlayer().getSkills().contains("3")));
        Recover.setDisable(!(combat.getPlayer().getSkills().contains("4")));
    }

    public void atkBut (ActionEvent e) throws InterruptedException, IOException {
        if (combat.getEnemy() != null && combat.getPlayer() != null) {
            combat.getPlayer().interact("1", combat.getEnemy());
            updateEnemyHealthBar();
            if(combat.checkEnemyStatus()) {
                combat.getPlayer().resetHP();
                combat.getPlayer().haveWon();
                if (combat.getPlayer().getEnemyWins() >= 3) {
                    Main.switchScene(Main.winScene);
                } else {
                    switchToStart();
                    resetHealthBars();
                }
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
    }

    public void powerBut (ActionEvent e) throws IOException {
        if (combat.getEnemy() != null && combat.getPlayer() != null) {
            combat.getPlayer().interact("2", combat.getEnemy());
            updateEnemyHealthBar();
            if(combat.checkEnemyStatus()) {
                combat.getPlayer().resetHP();
                combat.getPlayer().haveWon();
                if (combat.getPlayer().getEnemyWins() >= 3) {
                    Main.switchScene(Main.winScene);
                } else {
                    switchToStart();
                    resetHealthBars();
                }
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
    }


    public void blockBut (ActionEvent e) throws IOException {
        if (combat.getEnemy() != null && combat.getPlayer() != null) {
            combat.getPlayer().interact("3", combat.getEnemy());
            updateEnemyHealthBar();
            if(combat.checkEnemyStatus()) {
                combat.getPlayer().resetHP();
                combat.getPlayer().haveWon();
                if (combat.getPlayer().getEnemyWins() >= 3) {
                    Main.switchScene(Main.winScene);
                } else {
                    switchToStart();
                    resetHealthBars();
//                resetHealthBars();
                }
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
    }

    public void recoverBut (ActionEvent e) throws IOException {
        if (combat.getEnemy() != null && combat.getPlayer() != null) {
            combat.getPlayer().interact("4", combat.getEnemy());
            updateEnemyHealthBar();
            if(combat.checkEnemyStatus()) {
                combat.getPlayer().resetHP();
                combat.getPlayer().haveWon();
                if (combat.getPlayer().getEnemyWins() >= 3) {
                    Main.switchScene(Main.winScene);
                } else {
                    switchToStart();
                    resetHealthBars();
                }
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
    }

    private void updatePlayerHealthBar() {
        playerHealth = combat.getPlayer().getCurrHP() / 100;
        playerHealthBar.setProgress(playerHealth);
    }

    private void updateEnemyHealthBar() {
        enemyHealth = combat.getEnemy().getCurrHP() / 100;
        enemyHealthBar.setProgress(enemyHealth);
    }

    public void resetHealthBars() {
        playerHealthBar.setProgress(1.0);
        enemyHealthBar.setProgress(1.0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (this.enemyHealthBar != null && combat != null) {
            enemyHealth = combat.getEnemy().getCurrHP();
            enemyHealthBar.setProgress(enemyHealth);

            playerHealth = combat.getPlayer().getCurrHP();
            playerHealthBar.setProgress(playerHealth);
        } else {
            System.out.println("ERROR: Combat or enemyHealthBar is null in CombatSceneController initialize method.");
        }
    }

    public void switchToStart() throws IOException {
        Main.getMainStage().setScene(mainScene);
    }

    public static void setMainScene(Scene scene) {
        mainScene = scene;
    }
}
