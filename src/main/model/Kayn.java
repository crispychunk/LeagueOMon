package model;

// Kayn champion specifications

import java.util.Random;

public class Kayn extends LeagueOMon {

    public Kayn(String name, int health, int attack, int defence, int speed, int level) {
        super(name, health, attack, defence, speed, level);
        setMoveName("Strike","Blade Reach","Reaping Slash","Umbral Trespass");
        this.leagueOMonID = 3;
    }

    @Override
    //MODIFY: this and enemy
    //EFFECTS: Use 40 mana and Life steal 50% of the damage done to the enemy with a 2.5x damage modifier and raise
    // attack by 20%
    public void specialMove(LeagueOMon enemy) {
        int damage = (int) ((this.attack + this.atkBuff) * 2.5);
        int initialHP = enemy.getHealth();
        enemy.takeDamage(damage);
        int finalHP = enemy.getHealth();
        this.health += (initialHP - finalHP) / 2;
        this.changeATK((int) (this.attack * 0.2));
    }

    @Override
    //MODIFY: this and enemy
    //EFFECTS: Deal 1.8x damage to enemy and lower enemy def by 10%
    public void basicAttack(LeagueOMon enemy) {
        int damage = (int) ((this.attack + this.atkBuff) * 1.8);
        enemy.takeDamage(damage);
        enemy.changeDEF((int) (enemy.defence * -0.1));
        text.printFast(enemy.getName() + " defense decrease by 10%");
    }

    @Override
    //MODIFY: this and enemy
    //EFFECTS: Use 30 mana and deal 2.2x damage to enemy
    public void secondMove(LeagueOMon enemy) {
        int damage = (int) ((this.attack + this.atkBuff) * 2.2);
        enemy.takeDamage(damage);

        this.mana -= 30;
    }

    @Override
    //MODIFY: this and enemy
    //EFFECTS: use 20 mana deal 1.5 damage and has a 1/3 chance to stun and if so,
    // lower enemy attack and defence by 20%
    public void thirdMove(LeagueOMon enemy) {
        int damage = (int) ((this.attack + this.atkBuff) * 1.5);
        this.mana -= 20;
        enemy.takeDamage(damage);
        boolean val = new Random().nextInt(3) == 0;
        if (val) {
            enemy.changeDEF((int) (enemy.defence * -0.2));
            enemy.changeATK((int) (enemy.attack * -0.2));
            text.printFast(enemy.getName() + " attack decrease by 20%");
            text.printFast(enemy.getName() + " defense decrease by 20%");

        }
    }

    @Override
    //MODIFY: this and enemy
    //EFFECTS: have a 20% chance to lower enemy def by 10% per turn
    public void passive(LeagueOMon enemy) {
        boolean val = new Random().nextInt(5) == 0;
        if (val) {
            enemy.changeDEF((int) (enemy.defence * -0.1));
            text.printFast(enemy.getName() + " defense decrease by 10%");
        }
    }

}
