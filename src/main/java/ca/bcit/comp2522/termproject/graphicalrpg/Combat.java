package ca.bcit.comp2522.termproject.graphicalrpg;

/**
 * Combat. Represents a combat scenario between a player and an enemy.
 *
 * @author Emily & Sean
 * @version 2023/04/09
 */
public class Combat {
    /**
     * The player.
     */
    private final Player player;
    /**
     * The enemy.
     */
    private Enemy enemy;

    /**
     * Constructor for objects of class Combat.
     * @param nPlayer the player
     * @param nEnemy the enemy
     */
    public Combat(Player nPlayer,
                  Enemy nEnemy) {
        player = nPlayer;
        enemy = nEnemy;
        System.out.println("Combat created");
    }

    /**
     * Enemy attacks the player.
     */
    public void enemyATK () {
        if (enemy != null && player != null) {
            player.receiveDMG(enemy.attack());
            System.out.println("player: " + player.getCurrHP());
        }
    }

    /**
     * Check if the enemy has been defeated.
     * @return true if the enemy health goes below zero
     */
    public boolean checkEnemyStatus () {
        if (!(enemy.getCurrHP() > 0)) {
            if (enemy.getClass().equals(Boss.class)) {
                player.setWin(true);
            }
            System.out.println("Enemy has been defeated");
            player.setExp(player.getExp() + enemy.getExp());
            setWinner(player);
            player.checkLevelUp();
            System.out.println("Exp: " + player.getExp());
            Main.getMainStage().setScene(Main.mapScene);
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param nWinner the winning entity
     */
    public void setWinner(Entity nWinner) {
        /**
         * The winner of the combat.
         */
        System.out.println("Winner: " + nWinner.getName());
        if (nWinner.getName().equals(player.getName())) {
            enemy = null;
        }

    }

    /**
     *
     * @return
     */
    public boolean checkPlayerHP() {
        if (!(player.getCurrHP() > 0)) {
            System.out.println("Player has been defeated");
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     *
     * @return
     */
    public Enemy getEnemy() {
        return enemy;
    }

}

