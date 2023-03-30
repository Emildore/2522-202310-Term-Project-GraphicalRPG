package ca.bcit.comp2522.termproject.graphicalrpg;

public class Player extends Entity {

    private double minX = -300;
    private double maxX = 370;
    private double minY = -300;
    private double maxY = 60;

    private double posX;
    private double posY;

    public Player(String name) {
        super(name);
        this.posX = 50;
        this.posY = 50;
    }

    public void moveUp() {
        if (posY > minY) {
            posY -= 10;
        }
    }

    public void moveDown() {
        if (posY < maxY) {
            posY += 10;
        }
    }

    public void moveLeft() {
        if (posX > minX) {
            posX -= 10;
        }
    }

    public void moveRight() {
        if (posX < maxX) {
            posX += 10;
        }
    }

    public double getX() {
        return posX;
    }

    public double getY() {
        return posY;
    }

    public void setX(double x) {
        posX = x;
    }

    public void setY(double y) {
        posY = y;
    }

    public void setMinX(double x) {
        minX = x;
    }

    public void setMaxX(double x) {
        maxX = x;
    }

    public void setMinY(double y) {
        minY = y;
    }

    public void setMaxY(double y) {
        maxY = y;
    }
    public void attack(Entity enemy) {
        enemy.receiveDMG(super.attack());
    }

    public void resetHP() {
        super.resetHP();
    }

    public void haveWon() {
        super.haveWon();
    }

}