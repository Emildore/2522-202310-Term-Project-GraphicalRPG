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

    public static Scene startScene;
    public static Scene mapScene;
    public static Scene combatScene;
    public static Scene gameOverScene;
    public static Scene winScene;
    static FXMLLoader combatLoader;
    static FXMLLoader mapLoader;

    public Main() throws IOException {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        mainStage.setResizable(false);
        mainStage.setTitle("Graphical RPG");
        mainStage.sizeToScene();

        player = new Player("Player");
        Enemy enemy = new Enemy("Enemy"); // initialize enemy here
        Combat combat = new Combat(player, enemy); // initialize combat here
        CombatSceneController.setCombat(combat);
//        setPlayer();

        // Load the FXML files for the map scene and combat scene
        FXMLLoader startLoader = null;
        FXMLLoader gameOverLoader = null;
        FXMLLoader winLoader = null;
        try {
            startLoader = new FXMLLoader(getClass().getResource("Start.fxml"));
            mapLoader = new FXMLLoader(getClass().getResource("Map.fxml"));
            combatLoader = new FXMLLoader(getClass().getResource("Combat.fxml"));
            gameOverLoader = new FXMLLoader(getClass().getResource("GameOver.fxml"));
            winLoader = new FXMLLoader(getClass().getResource("Win.fxml"));

            Parent startRoot = startLoader.load();
            Parent mapRoot = mapLoader.load();
            Parent combatRoot = combatLoader.load();
            Parent gameOverRoot = gameOverLoader.load();
            Parent winRoot = winLoader.load();

            startScene = new Scene(startRoot);
            mapScene = new Scene(mapRoot);
            combatScene = new Scene(combatRoot);
            gameOverScene = new Scene(gameOverRoot);
            winScene = new Scene(winRoot);

            startScene.setRoot(startLoader.getRoot());
            mapScene.setRoot(mapLoader.getRoot());
            combatScene.setRoot(combatLoader.getRoot());
            gameOverScene.setRoot(gameOverLoader.getRoot());
            winScene.setRoot(winLoader.getRoot());
            mainStage.setScene(startScene);

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
                CombatSceneController cControl = combatLoader.getController();
                cControl.setButtons();
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

    public static void setPlayer() throws IOException {
        Player newPlayer = new Player("Player");
        Main.player = newPlayer;
        MapSceneController controller = mapLoader.getController();
        controller.setPlayer();
        controller.updatePlayerSpritePosition();
        Enemy enemy = new Enemy("Enemy"); // initialize enemy here
        Combat combat = new Combat(player, enemy); // initialize combat here
        CombatSceneController.setCombat(combat);
    }

    public static Scene getMapScene() {
        return mapScene;
    }

    public static Scene getCombatScene() {
        return combatScene;
    }

    public static void loadPlayer(Player lPlayer) {
        Main.player = lPlayer;
        MapSceneController controller = mapLoader.getController();
        controller.setPlayer();
        controller.updatePlayerSpritePosition();
        Enemy enemy = new Enemy("Enemy"); // initialize enemy here
        Combat combat = new Combat(player, enemy); // initialize combat here
        CombatSceneController.setCombat(combat);
        System.out.println("Loaded");
        System.out.println("Switching Scenes");
        Main.switchScene(Main.mapScene);
    }

}
