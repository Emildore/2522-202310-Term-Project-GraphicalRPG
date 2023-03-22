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



    static Player player;
    static Enemy enemy;
    static Combat combat;

    public static void setPlayer(Player nPlayer) {
        player = nPlayer;
    }

    static public void setEnemy(Enemy nEnemy) {
        enemy = nEnemy;
    }
    static public void setCombat(Combat nCombat) {
        combat = nCombat;
    }
    public void atkBut (ActionEvent e) throws InterruptedException {
        if (combat.getEnemy() != null && combat.getPlayer() != null) {
            combat.playerATK();
            enemyHealth = combat.getEnemy().getCurrHP() / 100;
            enemyHealthBar.setProgress(enemyHealth);
            if (combat.checkEnemyHP()) {
                combat.setWinner(player);
            }
            combat.enemyATK();
            playerHealth = combat.getPlayer().getCurrHP() / 100;
            playerHealthBar.setProgress(playerHealth);
            if (combat.checkPlayerHP()) {
                combat.setWinner(enemy);
            }
        }
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
        Combat combat = new Combat(new Player("Player"),
                new Enemy("Enemy"));
        System.out.println(combat.getPlayer().getCurrHP());
        CombatSceneController.setEnemy(combat.getEnemy());
        CombatSceneController.setPlayer(combat.getPlayer());
        CombatSceneController.setCombat(combat);
        root = FXMLLoader.load(getClass().getResource(("Combat.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
