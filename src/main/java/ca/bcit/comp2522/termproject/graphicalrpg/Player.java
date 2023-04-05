package ca.bcit.comp2522.termproject.graphicalrpg;

import java.util.ArrayList;

/**
 * Player. Represents a player entity in the game.
 *
 * @author Emily & Sean
 * @version 2023/04/09
 */
public class Player extends Entity {
    /**
     * The player's level.
     */
    private int level;
    /**
     * Whether the player has won the battle.
     */
    private boolean win;
    /**
     * The experience threshold for the player to level up.
     */
    private final int expThreshold;
    /**
     * The player's skills.
     */
    private final ArrayList<String> skills;

    /**
     * Constructs an object of type Player.
     * @param name the string name of the player
     */

    public Player(final String name) {
        super(name);
        this.posX = 50;
        this.posY = 50;

        // Combat
        win = false;
        expThreshold = 50;
        level = 1;
        skills = new ArrayList<>();
        skills.add("1");

    }

    /**
     * Moves the player up.
     */
    public void moveUp() {
        double minY = -300;
        if (posY > minY) {
            posY -= 10;
        }
    }

    /**
     * Moves the player down.
     */
    public void moveDown() {
        double maxY = 60;
        if (posY < maxY) {
            posY += 10;
        }
    }

    /**
     * Moves the player left.
     */
    public void moveLeft() {
        double minX = -300;
        if (posX > minX) {
            posX -= 10;
        }
    }

    /**
     * Moves the player right.
     */
    public void moveRight() {
        double maxX = 370;
        if (posX < maxX) {
            posX += 10;
        }
    }

    /**
     * Gets the player's x coordinate.
     * @return the player's x coordinate
     */
    public double getX() {
        return posX;
    }

    /**
     * Gets the player's y coordinate.
     * @return the player's y coordinate
     */
    public double getY() {
        return posY;
    }

    /**
     * Sets the player's x coordinate.
     * @param x the player's x coordinate
     */
    public void setX(final double x) {
        posX = x;
    }

    /**
     * Sets the player's y coordinate.
     * @param y the player's y coordinate
     */
    public void setY(final double y) {
        posY = y;
    }

    /**
     * Resets the player's HP.
     */
    public void resetHP() {
        super.resetHP();
    }

    /**
     * Increases the player's win count.
     */
    public void haveWon() {
        super.haveWon();
    }

    /**
     * Sets the player's win status.
     * @param win the player's win status
     */
    public void setWin(final boolean win) {
        this.win = win;
    }

    /**
     * Gets the player's level.
     * @return the player's level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets the player's win status.
     * @return the player's win status
     */
    public boolean getWin() {
        return win;
    }

    /**
     * Attack.
     * @return the player's current attack
     */
    @Override
    public float attack() {
        return this.getCurrATK();
    }

    /**
     * Doubles the player's attack.
     * @return the player's attack
     */
    public float doubleAttack() {
        return this.attack() * 2;
    }

    /**
     * Gets the player's experience threshold.
     * @return the player's experience threshold
     */
    public int getExpThreshold() {
        return expThreshold;
    }

    /**
     * Unlocks the player's skills.
     */
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

    /**
     * Determines the player's action.
     * @param selection the player's selection
     * @param nTarget the player's target
     */
    public void interact(final String selection, final Entity nTarget) {
        Entity target;
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
            target = nTarget;
            target.setCurrATK((float) (target.getCurrATK() * 0.8));
            System.out.println("atk: " + target.getCurrATK());
        }

        if (selection.equals("4")) {
            if (this.getCurrHP() < this.getBaseHP()) {
                this.setCurrHP((float) (this.getCurrHP() + (getBaseHP() * 0.3)));
            } else {
                this.setCurrHP(this.getBaseHP());
            }
        }
    }

    /**
     * Checks if the player has leveled up.
     */
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
    /**
     * Gets the player's skills.
     * @return ArrayList of the player's skills
     */
    public ArrayList<String> getSkills() {
        return skills;
    }
}
