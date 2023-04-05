package ca.bcit.comp2522.termproject.graphicalrpg;

/**
 * Enemy. Represents an enemy entity in the game.
 *
 * @author Emily & Sean
 * @version 2023/04/09
 */
public class Enemy extends Entity {
    /**
     * Constructs an object of type Enemy.
     * @param nName the string name of the enemy
     */
    public Enemy(final String nName) {
        super(nName);
        this.setExp(50);
    }

    /**
     * Constructs an object of type Enemy.
     */
    public Enemy() {
        super("Enemy");
        this.setExp(50);
    }

    /**
     * Constructs an object of type Enemy.
     * @param name the string name of the enemy
     * @param exp the experience value of the enemy
     */
    public Enemy(final String name, final int exp) {
        super(name);
        this.setExp(exp);
    }
}
