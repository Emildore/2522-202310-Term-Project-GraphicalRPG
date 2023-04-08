package ca.bcit.comp2522.termproject.graphicalrpg;

import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Parent;
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
    /**
     * The start scene.
     */
    public static Scene startScene;
    /**
     * The map scene.
     */
    public static Scene mapScene;
    /**
     * The combat scene.
     */
    public static Scene combatScene;
    /**
     * The game over scene.
     */
    public static Scene gameOverScene;
    /**
     * The win scene.
     */
    public static Scene winScene;
    /**
     * The combat loader.
     */
    static FXMLLoader combatLoader;
    /**
     *  The map loader.
     */
    static FXMLLoader mapLoader;
    /**
     * The main method.
     * @param args n/a
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**
     * The start method.
     * @param primaryStage the primary stage
     */
    @Override
    public void start(final Stage primaryStage) {
        mainStage = primaryStage;
        mainStage.setResizable(false);
        mainStage.setTitle("Graphical RPG");
        mainStage.sizeToScene();

        player = new Player("Player");
        Enemy enemy = new Enemy("Enemy"); // initialize enemy here
        Combat combat = new Combat(player, enemy); // initialize combat here


        CombatSceneController.setCombat(combat);

        // Load the FXML files for the map scene and combat scene
        FXMLLoader startLoader;
        FXMLLoader gameOverLoader;
        FXMLLoader winLoader;
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


        mapScene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP -> player.moveUp();
                case DOWN -> player.moveDown();
                case LEFT -> player.moveLeft();
                case RIGHT -> player.moveRight();
                default -> {
                }
            }
            controller.updatePlayerSpritePosition();
            controller.checkCombatTrigger();
            CombatSceneController cControl = combatLoader.getController();
            cControl.setButtons();
        });
    }

    /**
     * Switches the scene.
     * @param scene the scene to switch to
     */
    public static void switchScene(final Scene scene) {
        try {
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the main stage.
     * @return the main stage
     */
    public static Stage getMainStage() {
        return mainStage;
    }

    /**
     * Sets the player.
     */
    public static void setPlayer() {
        Main.player = new Player("Player");
        MapSceneController controller = mapLoader.getController();
        controller.setPlayer();
        controller.updatePlayerSpritePosition();
        Enemy enemy = new Enemy("Enemy"); // initialize enemy here
        Combat combat = new Combat(player, enemy); // initialize combat here
        CombatSceneController.setCombat(combat);
    }

    /**
     * Loads the player.
     * @param lPlayer the player to load
     */
    public static void loadPlayer(final Player lPlayer) {
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
