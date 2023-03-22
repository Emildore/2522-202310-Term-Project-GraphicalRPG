package ca.bcit.comp2522.termproject.graphicalrpg;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
    private Stage stage;
    private Scene scene;
    private Parent root;

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
    public void switchToStart(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(("Start.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
