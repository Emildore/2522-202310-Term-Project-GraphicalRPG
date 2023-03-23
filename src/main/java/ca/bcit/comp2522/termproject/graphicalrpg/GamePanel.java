package ca.bcit.comp2522.termproject.graphicalrpg;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static ca.bcit.comp2522.termproject.graphicalrpg.main.*;

public class GamePanel {
    private final Player player;
    private long lastTime = 0;
    int thresholdX = 5;
    int thresholdY = 5;

    public GamePanel(Player player) {
        this.player = player;
    }

    public void loop(GraphicsContext gc, long now) {
        if (lastTime == 0) {
            lastTime = now;
        }
        double elapsedTime = (now - lastTime) / 1000000000.0;
        lastTime = now;
        update(elapsedTime);
        draw(gc);
    }

    private void update(double elapsedTime) {
        player.update();
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
        if (player.getX() >= thresholdX && player.getY() >= thresholdY) {
            thresholdY += 10;
            thresholdX += 10;
            System.out.println("Bounds x: " + thresholdX);
            System.out.println("Bounds y: " + thresholdY);
            return true;
        }
        return false;
    }
}

