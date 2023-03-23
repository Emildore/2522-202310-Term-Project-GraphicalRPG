package ca.bcit.comp2522.termproject.graphicalrpg;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class main extends Application {
    public static int screenWidth;
    public static int screenHeight;
    public static int tileSize;
    final int originalTileSize = 16;
    final int scale = 3;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        tileSize = originalTileSize * scale;
        screenWidth = maxScreenCol * tileSize;
        screenHeight = maxScreenRow * tileSize;

        Group root = new Group();
        Canvas canvas = new Canvas(screenWidth, screenHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);

        Player player = new Player("player");

        Scene scene = new Scene(root, screenWidth, screenHeight);
        scene.setFill(Color.BLACK);

        scene.setOnKeyPressed(event -> player.handleKeyPress(event.getCode()));
        scene.setOnKeyReleased(event -> player.handleKeyRelease(event.getCode()));

        primaryStage.setTitle("Graphical RPG");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        GamePanel gamePanel = new GamePanel(player);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                gamePanel.loop(gc, now);
            }
        }.start();
    }

}
