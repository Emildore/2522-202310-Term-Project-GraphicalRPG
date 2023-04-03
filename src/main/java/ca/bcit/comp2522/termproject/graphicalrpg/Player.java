package ca.bcit.comp2522.termproject.graphicalrpg;

import java.io.Serializable;
import java.util.ArrayList;

public class Player extends Entity {

    private double minX = -300;
    private double maxX = 370;
    private double minY = -300;
    private double maxY = 60;

    // Combat
    private int level;
    private boolean win;
    private boolean lose;
    private int expThreshold;
    private Entity target;
    private ArrayList<String> skills;
    private double posX;
    private double posY;

    public Player(String name) {
        super(name);
        this.posX = 50;
        this.posY = 50;

        // Combat
        win = false;
        lose = false;
        expThreshold = 50;
        level = 1;
        skills = new ArrayList<>();
        skills.add("1");

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

    public void setWin(boolean win) {
        this.win = win;
    }

    public void setLose(boolean lose) {
        this.lose = lose;
    }

    public int getLevel() {
        return level;
    }

    public boolean getWin() {
        return win;
    }

    public boolean getLose() {
        return lose;
    }

    @Override
    public float attack() {
        return this.getCurrATK();
    }

    public float doubleAttack() {
        return this.attack() * 2;
    }

    public double block() {
        return this.getBaseHP() * 0.5;
    }

    public int getExpThreshold() {
        return expThreshold;
    }

    public void setExpThreshold(int expThreshold) {
        this.expThreshold = expThreshold;
    }

    public void levelUp() {
        this.level += 1;
        if (this.level == 2) {
            skills.add("2");
        } else if (this.level == 3) {
            skills.add("3");
        } else if (this.level == 4) {
            skills.add("4");
        }
    }

    public void displaySkills() {
        System.out.println("***\nMoves");
        if (this.getLevel() >= 1) {
            System.out.println("1: Attack");
        }
        if (this.getLevel() >= 2) {
            System.out.println("2: Power Strike");
        }
        if (this.getLevel() >= 3) {
            System.out.println("3: Block");
        }
    }

    public void interact(String selection, Entity nTarget) {
        if (selection.equals("1")) {
            target = nTarget;
            if (target != null) {
                target.receiveDMG(this.getCurrATK());
                System.out.println(target.getName() + ": " + target.getCurrHP());
            }
        }
        if (selection.equals("2")) {
            target = nTarget;
            target.receiveDMG(this.doubleAttack());
            System.out.println(target.getName() + ": " + target.getCurrHP());
        }

        if (selection.equals("3")) {
            target.setCurrATK((float) (target.getCurrATK() * 0.9));
        }

        if (selection.equals("4")) {
            if (this.getCurrHP() < this.getBaseHP()) {
                this.setCurrHP((float) (this.getCurrHP() + (getBaseHP() * 0.2)));
            } else {
                this.setCurrHP(this.getBaseHP());
            }
        }
    }


    public void checkLevelUp() {
        if (this.getExp() >= this.getExpThreshold()) {
            this.levelUp();
            System.out.println("level up!\nPlayer Level: " + this.getLevel());
            if (this.getExp() > this.getExpThreshold()) {
                this.setExp(this.getExp() - this.getExpThreshold());
                this.checkLevelUp();
            } else {
                this.setExp(0);
            }
        }
    }

    public ArrayList<String> getSkills() {
        return skills;
    }
}