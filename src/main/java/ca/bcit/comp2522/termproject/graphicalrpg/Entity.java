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
     * The name of the entity.
     */
    private String name;
    /**
     * The base HP of the entity.
     */
    private float baseHP;
    /**
     * The current HP of the entity.
     */
    private float currHP;
    /**
     * The base ATK of the entity.
     */
    private float baseATK;
    /**
     * The current ATK of the entity.
     */
    private float currATK;
    /**
     * The x coordinate of the entity.
     */
    double posX;
    /**
     * The y coordinate of the entity.
     */
    double posY;
    private int enemyWins;
    private int exp;

    public Entity(String nName) {
        name = nName;
        baseHP = 100;
        currHP = baseHP;
        baseATK = 10;
        currATK = baseATK;
        posX = 0;
        posY = 0;
        enemyWins = 0;
    }

    public float attack() {
        return currATK;
    }

    public void receiveDMG(float dmg) {
        this.currHP -= dmg;
    }

    public float getCurrHP() {
        return currHP;
    }
    public void resetHP() {
        currHP = baseHP;
    }
    public void haveWon() {
        enemyWins++;
        System.out.println(name + " has won " + enemyWins + " times.");
    }

    public String getName() {
        return name;
    }

    public int getEnemyWins() {
        return enemyWins;
    }

    public float getCurrATK() {
        return currATK;
    }

    public float getBaseHP() {
        return baseHP;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setCurrATK(float currATK) {
        this.currATK = currATK;
    }

    public void setBaseHP(float baseHP) {
        this.baseHP = baseHP;
    }

    public void setCurrHP(float currHP) {
        this.currHP = currHP;
    }
}
