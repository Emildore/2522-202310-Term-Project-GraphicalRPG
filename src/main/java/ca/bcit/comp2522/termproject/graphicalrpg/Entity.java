package ca.bcit.comp2522.termproject.graphicalrpg;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    private String name;
    private float baseHP;
    private float currHP;
    private float baseATK;
    private float currATK;
    private int posx;
    private int posy;
    private int enemyWins;
    private int exp;

    public Entity(String nName) {
        name = nName;
        baseHP = 100;
        currHP = baseHP;
        baseATK = 10;
        currATK = baseATK;
        posx = 0;
        posy = 0;
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

    public void setCurrHP(float currHP) {
        this.currHP = currHP;
    }
}
