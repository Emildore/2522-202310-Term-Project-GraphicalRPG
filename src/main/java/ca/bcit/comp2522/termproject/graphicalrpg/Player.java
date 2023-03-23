package ca.bcit.comp2522.termproject.graphicalrpg;

import javafx.scene.input.KeyCode;

public class Player extends Entity {

    private boolean leftPressed, rightPressed, upPressed, downPressed;

    public Player(String string) {
        super(string);
    }

    public void update() {
        if (leftPressed) {
            posx -= 5;
        }
        if (rightPressed) {
            posx += 5;
        }
        if (upPressed) {
            posy -= 5;
        }
        if (downPressed) {
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
