package ca.bcit.comp2522.termproject.graphicalrpg;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;

/**
 * Main. This is the main class for the Graphical RPG.
 *
 * @author Emily & Sean
 * @version 2023/04/09
 */
public class Main extends Application {
    /**
     * The player.
     */
    public static Player player;
    /**
     * The main stage.
     */
    private static Stage mainStage;
    public static Scene startScene;
    public static Scene mapScene;
    public static Scene combatScene;
    public static Scene gameOverScene;
    public static Scene winScene;
    static FXMLLoader combatLoader;
    static FXMLLoader mapLoader;

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


            // CHECK IF FILE EXISTS FOR LOADING
            ExtraController setLoadCont = startLoader.getController();
            setLoadCont.setLoadButton();


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
                        break;
                    case DOWN:
                        player.moveDown();
                        break;
                    case LEFT:
                        player.moveLeft();
                        break;
                    case RIGHT:
                        player.moveRight();
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
