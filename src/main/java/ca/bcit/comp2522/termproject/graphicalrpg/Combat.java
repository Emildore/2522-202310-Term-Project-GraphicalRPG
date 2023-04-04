package ca.bcit.comp2522.termproject.graphicalrpg;

public class Combat {
    private int turn;
    private Player player;
    private Enemy enemy;
    private Entity winner;

    public Combat(Player nPlayer,
                  Enemy nEnemy) {
        turn = 0;
        player = nPlayer;
        enemy = nEnemy;
        System.out.println("Scenario created");
    }

    public void enemyATK () {
        if (enemy != null && player != null) {
            player.receiveDMG(enemy.attack());
            System.out.println("player: " + player.getCurrHP());
        }
    }

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

    public void setWinner(Entity nWinner) {
        winner = nWinner;
        System.out.println("Winner: " + winner.getName());
        if (winner.getName().equals(player.getName())) {
            enemy = null;
        }

    }

    public boolean checkPlayerHP() {
        if (!(player.getCurrHP() > 0)) {
            System.out.println("Player has been defeated");
            return true;
        }
        return false;
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

}

