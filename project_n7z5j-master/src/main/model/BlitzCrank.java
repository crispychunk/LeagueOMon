package model;

// BlitzCrank champion

import java.util.Random;

public class BlitzCrank extends LeagueOMon implements LeagueOMonMoveset {

    int counter;

    public BlitzCrank(String name, int health, int attack, int defence, int speed, int level) {
        super(name, health, attack, defence, speed, level);
        counter = 0;
        setMoveName("Strike","Overdrive","PowerFist","Static Field");
        this.imageDirectory = "./data/Images/Characters/blitzcrank.png";
        this.leagueOMonID = 1;
    }

    @Override
    //MODIFY: this and enemy
    //EFFECTS: Use 30 mana and deal 3x damage and stun enemy
    public void specialMove(LeagueOMon enemy) {
        int damage = (this.attack + this.atkBuff) * 3;
        enemy.takeDamage(damage);
        enemy.isStun = true;
        this.mana -= 30;
    }

    @Override
    //MODIFY: this and enemy
    //EFFECTS: BlitzCrank deals 1.8x base damage
    public void basicAttack(LeagueOMon enemy) {
        int damage = (int)((this.attack + this.atkBuff) * 1.8);
        enemy.takeDamage(damage);
    }

    @Override
    //MODIFY: this and enemy
    //EFFECTS: boost speed and atk for 3 turns by 40%
    public void secondMove(LeagueOMon enemy) {
        this.spdBuff += this.speed * 0.3;
        this.atkBuff += this.attack * 0.3;

    }

    @Override
    //MODIFY: this and enemy
    //EFFECTS: Deal 2x damage and use 20 mana
    public void thirdMove(LeagueOMon enemy) {
        this.mana -= 20;
        int damage = this.attack * 2;
        enemy.takeDamage(damage);
    }

    //MODIFY: this and enemy
    //EFFECTS: Has a 50% chance of dealing 1.2x base attack damage as extra damage to enemy per turn
    public void passive(LeagueOMon enemy) {
        counter++;
        if (counter == 3) {
            this.spdBuff -= this.speed * 0.3;
            this.atkBuff -= this.attack * 0.3;
            counter = 0;
        }

        boolean val = new Random().nextInt(2) == 0;
        if (val) {
            int damage = (int) (this.attack * 1.2);
            enemy.takeDamage(damage);
        }
    }

}
