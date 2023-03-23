package ca.bcit.comp2522.termproject.graphicalrpg;

public abstract class Entity {
    private String name;
    private float baseHP;
    private float currHP;
    private float baseATK;
    private float currATK;
    int posx;
    int posy;

    public Entity(String nName) {
        name = nName;
        baseHP = 100;
        currHP = baseHP;
        baseATK = 10;
        currATK = baseATK;
        posx = 0;
        posy = 0;
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

    public String getName() {
        return name;
    }
}
