package ca.bcit.comp2522.termproject.graphicalrpg;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

public class Main extends Application {

    public static Player player;
    private static Stage mainStage;
    public static Scene mapScene;
    static Scene combatScene;

    public Main() throws IOException {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        player = new Player("Player");
        Enemy enemy = new Enemy("Enemy"); // initialize enemy here
        Combat combat = new Combat(player, enemy); // initialize combat here
        CombatSceneController.setCombat(combat);

        // Load the FXML files for the map scene and combat scene
        FXMLLoader mapLoader = null;
        try {
            mapLoader = new FXMLLoader(getClass().getResource("Map.fxml"));
            FXMLLoader combatLoader = new FXMLLoader(getClass().getResource("Combat.fxml"));
            Parent mapRoot = mapLoader.load();

            // Set the mapPane as the root node of the mapScene
            mapScene = new Scene(mapRoot);
            mapScene.setRoot(mapLoader.getRoot());
            mainStage.setScene(mapScene);

            Parent combatRoot = combatLoader.load();
            combatScene = new Scene(combatRoot);
            CombatSceneController.setMainScene(mapScene);
            MapSceneController.setComScene(combatScene);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainStage.show();

        MapSceneController controller = mapLoader.getController();
        mapScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        player.moveUp();
                        System.out.println("player x" + player.getX());
                        System.out.println("player y" + player.getY());
                        break;
                    case DOWN:
                        player.moveDown();
                        System.out.println("player x" + player.getX());
                        System.out.println("player y" + player.getY());
                        break;
                    case LEFT:
                        player.moveLeft();
                        System.out.println("player x" + player.getX());
                        System.out.println("player y" + player.getY());
                        break;
                    case RIGHT:
                        player.moveRight();
                        System.out.println("player x" + player.getX());
                        System.out.println("player y" + player.getY());
                        break;
                    default:
                        break;
                }
                controller.updatePlayerSpritePosition();
                controller.checkCombatTrigger();
            }
        });
    }

    public static void switchScene(Scene scene) {
        try {
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static Scene getMapScene() {
        return mapScene;
    }

    public static Scene getCombatScene() {
        return combatScene;
    }

}
