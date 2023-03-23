package ca.bcit.comp2522.termproject.graphicalrpg;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CombatSceneController implements Initializable {

    @FXML
    private ProgressBar enemyHealthBar;

    @FXML
    private ProgressBar playerHealthBar;

    private double enemyHealth;
    private double playerHealth;

    private Stage stage;
    private Scene scene;
    private Parent root;

    static Combat combat;

    static public void setCombat(Combat nCombat) {
        combat = nCombat;
    }
    public void atkBut (ActionEvent e) throws InterruptedException, IOException {
        if (combat.getEnemy() != null && combat.getPlayer() != null) {
            combat.playerATK();
            updateEnemyHealthBar();
            if (combat.checkEnemyHP()) {
                combat.setWinner(combat.getPlayer());
                combat.getPlayer().resetHP();
                combat.getPlayer().haveWon();
                switchToStart(e);
            }
            combat.enemyATK();
            updatePlayerHealthBar();
            if (combat.checkPlayerHP()) {
                combat.setWinner(combat.getEnemy());
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (this.enemyHealthBar != null) {
            enemyHealth = combat.getEnemy().getCurrHP();
            enemyHealthBar.setProgress(enemyHealth);

            playerHealth = combat.getPlayer().getCurrHP();
            playerHealthBar.setProgress(playerHealth);
        }
    }

    public void switchToStart(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(("Start.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCombat(ActionEvent event) throws IOException {
        if (combat == null) {
            Combat combatSetter = new Combat(new Player("John"),
                    new Enemy("Enemy"));
            System.out.println(combat.getPlayer().getCurrHP());
            CombatSceneController.setCombat(combatSetter);
            root = FXMLLoader.load(getClass().getResource(("Combat.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            Combat newCombat = new Combat(combat.getPlayer(),
                    new Enemy("Enemy"));
            System.out.println(combat.getPlayer().getCurrHP());
            CombatSceneController.setCombat(newCombat);
            root = FXMLLoader.load(getClass().getResource(("Combat.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
