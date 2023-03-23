package ca.bcit.comp2522.termproject.graphicalrpg;

import static ca.bcit.comp2522.termproject.graphicalrpg.main.tileSize;

public abstract class Entity {
    private String name;
    private float baseHP;
    private float currHP;
    private float baseATK;
    private float currATK;
    int posx;
    int posy;
    private int enemyWins;

    public Entity(String nName) {
        name = nName;
        baseHP = 100;
        currHP = baseHP;
        baseATK = 10;
        currATK = baseATK;
        posx = 2*tileSize;
        posy = 2*tileSize;
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
}
