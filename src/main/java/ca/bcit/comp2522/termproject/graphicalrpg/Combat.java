package ca.bcit.comp2522.termproject.graphicalrpg;

import java.util.Scanner;

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

    public void playerATK () {
        if (enemy != null && player != null) {
            enemy.receiveDMG(player.attack());
            System.out.println("Enemy: " + enemy.getCurrHP());
        }
    }

    public void enemyATK () {
        if (enemy != null && player != null) {
            player.receiveDMG(enemy.attack());
            System.out.println("player: " + player.getCurrHP());
        }
    }

    public boolean checkEnemyHP() {
        if (!(enemy.getCurrHP() > 0)) {
            System.out.println("Enemy has been defeated");
            return true;
        }
        return false;
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


    public void initiateCombat() {
        while (player.getCurrHP() > 0 && enemy.getCurrHP() > 0) {

            // User input
            System.out.println("Moves:\n"
                    + "1: Attack");
            Scanner input = new Scanner(System.in);
            if (input.nextLine().equals("1")) {
                // Player Turn
                playerATK();
                if (!(enemy.getCurrHP() > 0)) {
                    System.out.println("Enemy has been defeated");
                    break;
                }
            }
            // Enemy Turn
            player.receiveDMG(enemy.attack());
            System.out.println("Player: " + player.getCurrHP());
            if (!(player.getCurrHP() > 0)) {
                System.out.println("Player has been defeated");
                break;
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

}

