package ca.bcit.comp2522.termproject.graphicalrpg;

public class Boss extends Enemy {
    public Boss(String nName) {
        super(nName, 150);
        this.setCurrATK(this.getCurrATK() * 2);
        this.setCurrHP(this.getCurrHP() * 2);
    }
}
