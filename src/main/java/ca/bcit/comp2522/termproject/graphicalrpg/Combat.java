package ca.bcit.comp2522.termproject.graphicalrpg;

/**
 * Combat. Represents a combat scenario between a player and an enemy.
 *
 * @author Emily & Sean
 * @version 2023/04/09
 */
public class Combat {
    /**
     * The turn number.
     */
    private int turn;
    /**
     * The player.
     */
    private Player player;
    /**
     * The enemy.
     */
    private Enemy enemy;
    /**
     * The winner of the combat.
     */
    private Entity winner;

    /**
     * Constructor for objects of class Combat.
     * @param nPlayer the player
     * @param nEnemy the enemy
     */
    public Combat(Player nPlayer,
                  Enemy nEnemy) {
        turn = 0;
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
     *
     * @return
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
     * @param nWinner
     */
    public void setWinner(Entity nWinner) {
        winner = nWinner;
        System.out.println("Winner: " + winner.getName());
        if (winner.getName().equals(player.getName())) {
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

