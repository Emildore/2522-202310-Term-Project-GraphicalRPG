package ca.bcit.comp2522.termproject.graphicalrpg;

public class Enemy extends Entity {

    public Enemy(String nName) {
        super(nName);
        this.setExp(50);
    }

    public Enemy() {
        super("Enemy");
        this.setExp(50);
    }

    public Enemy(String name, int exp) {
        super(name);
        this.setExp(exp);
    }
}
