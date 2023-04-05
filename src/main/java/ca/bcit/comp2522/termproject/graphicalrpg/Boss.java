package ca.bcit.comp2522.termproject.graphicalrpg;

/**
 * Boss Enemy. Represents a boss enemy.
 *
 * @author Emily & Sean
 * @version 2023/04/09
 */
public class Boss extends Enemy {
    /**
     * Constructor for objects of class Boss.
     * @param nName the name of the boss
     */
    public Boss(String nName) {
        super(nName, 150);
        this.setCurrATK(this.getCurrATK() * 3);
        this.setCurrHP(this.getCurrHP() * 2);
    }
}
