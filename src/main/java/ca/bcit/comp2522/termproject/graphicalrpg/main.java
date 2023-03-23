package ca.bcit.comp2522.termproject.graphicalrpg;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class main extends Application {
    public static int screenWidth;
    public static int screenHeight;
    public static int tileSize;
    final int originalTileSize = 16;
    final int scale = 3;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    static Scene mapScene;
    static Scene combatScene;
    static Parent combatRoot;
    static Group mapRoot;
    static Stage mainStage;
    static Player player;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        mainStage = primaryStage;

        // Set up map dimensions
        tileSize = originalTileSize * scale;
        screenWidth = maxScreenCol * tileSize;
        screenHeight = maxScreenRow * tileSize;

        // Set up combat scene
        player = new Player("John");
        Combat combatSetter = new Combat(player, new Enemy("Enemy"));
        CombatSceneController.setCombat(combatSetter);
        combatRoot = FXMLLoader.load(Objects.requireNonNull(CombatSceneController.class.getResource(("Combat.fxml"))));
        combatScene = new Scene(combatRoot);

        // Set up map scene
        mapRoot = new Group();
        Canvas canvas = new Canvas(screenWidth, screenHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        mapRoot.getChildren().add(canvas);



        mapScene = new Scene(mapRoot, screenWidth, screenHeight);
        mapScene.setFill(Color.BLACK);

        mapScene.setOnKeyPressed(event -> player.handleKeyPress(event.getCode()));
        mapScene.setOnKeyReleased(event -> player.handleKeyRelease(event.getCode()));

        // Display stage
        primaryStage.setScene(mapScene);
        primaryStage.show();

        GamePanel gamePanel = new GamePanel(player);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    gamePanel.loop(gc, now);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }

}
