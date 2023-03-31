package ca.bcit.comp2522.termproject.graphicalrpg;

public abstract class Entity {
    private String name;
    private float baseHP;
    private float currHP;
    private float baseATK;
    private float currATK;
    int posx;
    int posy;
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

    public void addCurrHP(float value) {
        this.currHP += value;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
