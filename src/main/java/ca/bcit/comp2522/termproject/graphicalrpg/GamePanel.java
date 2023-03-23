package ca.bcit.comp2522.termproject.graphicalrpg;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.IOException;

import static ca.bcit.comp2522.termproject.graphicalrpg.main.*;

public class GamePanel {
    private final Player player;
    private long lastTime = 0;
    int thresholdX = 5;
    int thresholdY = 5;

    boolean combatSceneCreated = false;

    public GamePanel(Player player) {
        this.player = player;
    }

    public void loop(GraphicsContext gc, long now) throws IOException {
        if (lastTime == 0) {
            lastTime = now;
        }
        double elapsedTime = (now - lastTime) / 1000000000.0;
        lastTime = now;
        update(elapsedTime);
        draw(gc);
    }

    private void update(double elapsedTime) throws IOException {
        if (player.getCurrHP() > 0) {
            checkPlayerPosition();
            player.update();
            combatSceneCreated = false;
        } else {
            System.out.println("You died");
        }
        if(checkPlayerPosition()) {
            System.out.println("Ooof");
            player.resetKeyPress();
            CombatSceneController.setCombat(new Combat(player, new Enemy("Enemy")));
            main.mainStage.setScene(main.combatScene);
        }
    }

    private void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, screenWidth, screenHeight);
        gc.setFill(Color.WHITE);
        gc.fillRect(player.getX(), player.getY(), tileSize, tileSize);
    }

    private boolean checkPlayerPosition() {
        if (player.getX() >= (thresholdX - tileSize) && player.getX() <= (thresholdX + tileSize)
                && player.getY() >= (thresholdY - tileSize) && player.getY() <= (thresholdY + tileSize)) {
            thresholdY += 10;
            thresholdX += 10;
            System.out.println("Bounds x: " + thresholdX);
            System.out.println("Bounds y: " + thresholdY);
            return true;
        }
        return false;
    }
}

