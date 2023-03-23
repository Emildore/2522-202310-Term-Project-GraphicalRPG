package ca.bcit.comp2522.termproject.graphicalrpg;

import javafx.scene.input.KeyCode;

import static ca.bcit.comp2522.termproject.graphicalrpg.main.*;

public class Player extends Entity {

    private boolean leftPressed, rightPressed, upPressed, downPressed;
    private int minX, maxX, minY, maxY;

    public Player(String string) {
        super(string);
        this.minX = 0;
        this.maxX = screenWidth - tileSize;
        this.minY = 0;
        this.maxY = screenHeight - tileSize;
    }

    public void update() {
        if (leftPressed && posx > minX) {
            posx -= 5;
        }
        if (rightPressed && posx < maxX) {
            posx += 5;
        }
        if (upPressed && posy > minY) {
            posy -= 5;
        }
        if (downPressed && posy < maxY) {
            posy += 5;
        }
    }

    public void handleKeyPress(KeyCode code) {
        if (code == KeyCode.W) {
            upPressed = true;
        }
        if (code == KeyCode.S) {
            downPressed = true;
        }
        if (code == KeyCode.A) {
            leftPressed = true;
        }
        if (code == KeyCode.D) {
            rightPressed = true;
        }
    }

    public void handleKeyRelease(KeyCode code) {
        if (code == KeyCode.W) {
            upPressed = false;
        }
        if (code == KeyCode.S) {
            downPressed = false;
        }
        if (code == KeyCode.A) {
            leftPressed = false;
        }
        if (code == KeyCode.D) {
            rightPressed = false;
        }
    }

    public int getX() {
        return posx;
    }

    public int getY() {
        return posy;
    }
}
