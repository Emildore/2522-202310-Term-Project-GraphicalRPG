package ca.bcit.comp2522.termproject.graphicalrpg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CombatTesting extends Application {

    public static void main(String[] args) {


        // Initiate combat
        Combat combat = new Combat(new Player("Player"),
                new Enemy("Enemy"));
        System.out.println(combat.getPlayer().getCurrHP());
        CombatSceneController.setEnemy(combat.getEnemy());
        CombatSceneController.setPlayer(combat.getPlayer());
        CombatSceneController.setCombat(combat);
        launch(args);
//        combat.initiateCombat();
    }

    @Override
    public void start(Stage firstStage) throws Exception {
        // Link FXML to Scene
        Parent root = FXMLLoader.load(getClass().getResource(("Start.fxml")));
        Scene firstScene = new Scene(root, Color.BLACK);

        // Stage setup
        firstStage.setScene(firstScene);
        firstStage.setTitle("Graphical RPG");
        firstStage.setResizable(false);
        firstStage.setWidth(1000);
        firstStage.setHeight(630);
        firstStage.show();


    }
}
