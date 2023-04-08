package ca.bcit.comp2522.termproject.graphicalrpg;

import java.io.Serializable;

/**
 * Entity. Represents an entity in the game.
 *
 * @author Emily & Sean
 * @version 2023/04/09
 */
public abstract class Entity implements Serializable {
    /**
     * The x coordinate of the entity.
     */
    double posX;
    /**
     * The y coordinate of the entity.
     */
    double posY;
    /**
     * The name of the entity.
     */
    private final String name;
    /**
     * The base HP of the entity.
     */
    private float baseHP;
    /**
     * The current HP of the entity.
     */
    private float currHP;
    /**
     * The current ATK of the entity.
     */
    private float currATK;
    /**
     * The number of times the entity has won.
     */
    private int enemyWins;
    /**
     * The amount of experience the entity has.
     */
    private int exp;

    /**
     * Constructor for objects of class Entity.
     * @param nName the name of the entity
     */
    public Entity(final String nName) {
        name = nName;
        baseHP = 100;
        currHP = baseHP;
        currATK = (float) 10;
        posX = 0;
        posY = 0;
        enemyWins = 0;
    }
    /**
     * Returns the entity's attack damage value.
     * @return a float representing the entity's attack damage value
     */
    public float attack() {
        return currATK;
    }

    /**
     * Receives damage from an entity.
     * @param dmg the amount of damage to be taken as a float
     */
    public void receiveDMG(final float dmg) {
        this.currHP -= dmg;
    }

    /**
     * Returns the entity's current HP.
     * @return a float representing the entity's current HP
     */
    public float getCurrHP() {
        return currHP;
    }

    /**
     * Resets the entity's HP to its base HP.
     */
    public void resetHP() {
        currHP = baseHP;
    }

    /**
     * Increases the entity's wins by 1.
     */
    public void haveWon() {
        enemyWins++;
        System.out.println(name + " has won " + enemyWins + " times.");
    }

    /**
     * Returns the entity's name.
     * @return a String representing the entity's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the number of times the entity has won.
     * @return an int representing the number of times the entity has won
     */
    public int getEnemyWins() {
        return enemyWins;
    }

    /**
     * Returns the current ATK of the entity.
     * @return a float representing the current ATK of the entity
     */
    public float getCurrATK() {
        return currATK;
    }

    /**
     * Returns the base HP of the entity.
     * @return a float representing the base HP of the entity
     */
    public float getBaseHP() {
        return baseHP;
    }

    /**
     * Returns the entity's experience.
     * @return an int representing the entity's experience
     */
    public int getExp() {
        return exp;
    }

    /**
     * Sets the entity's experience.
     * @param exp the amount of experience to be set as an int
     */
    public void setExp(final int exp) {
        this.exp = exp;
    }

    /**
     * Sets the entity's current ATK.
     * @param currATK the amount of ATK to be set as a float
     */
    public void setCurrATK(final float currATK) {
        this.currATK = currATK;
    }

    /**
     * Sets the entity's base HP.
     * @param baseHP the amount of HP to be set as a float
     */
    public void setBaseHP(final float baseHP) {
        this.baseHP = baseHP;
    }

    /**
     * Sets the entity's current HP.
     * @param currHP the amount of HP to be set as a float
     */
    public void setCurrHP(final float currHP) {
        this.currHP = currHP;
    }
}
