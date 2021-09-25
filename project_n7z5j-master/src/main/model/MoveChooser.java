package model;

import java.util.Random;
// This is the AI for enemies in the game. At this point, it is a randoms selector base on the hp of their fighting
// LeagueOMon

public class MoveChooser {
    //EFFECT: Choose a random move depending on how much hp the 'enemy' has
    // hp > 70% enemy use either basic or second move
    // 70% > hp > 35% enemy uses second or 3rd move
    // hp < 35% enemy uses either 3rd or 4th move
    // return -1 if none of the above is true
    public int chooseMove(LeagueOMon enemy) {
        int ans = -1;
        if (enemy.getHealth() > enemy.maxHealth * 0.7) {
            if (new Random().nextInt(2) == 0) {
                return 0;
            } else {
                return 1;
            }
        } else if (enemy.getHealth() <= enemy.maxHealth * 0.7 && enemy.getHealth() > enemy.maxHealth * 0.35) {
            boolean val = new Random().nextInt(2) == 0;
            if (val) {
                return 1;
            } else {
                return 2;
            }
        } else if (enemy.getHealth() <= enemy.maxHealth * 0.35) {
            boolean val = new Random().nextInt(3) == 0;
            if (val) {
                return 3;
            } else {
                return 2;
            }
        }
        return ans;
    }
}
