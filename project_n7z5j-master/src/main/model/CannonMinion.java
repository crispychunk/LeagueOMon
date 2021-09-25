package model;

// Cannon minion, wild pokemon use for grinding

public class CannonMinion extends LeagueOMon {

    public CannonMinion(String name, int health, int attack, int defence, int speed, int level) {
        super(name, health, attack, defence, speed, level);
        setMoveName("Cannon Shoot","Cannon Shoot","Taunt","Big cannon shoot");
        this.leagueOMonID = 2;
    }

    @Override
    // MODIFY: this and enemy
    // EFFECTS : deal 1.9x base damage to enemy
    public void specialMove(LeagueOMon enemy) {
        int damage = (int) ((this.attack + this.atkBuff) * 1.9);
        enemy.takeDamage(damage);
    }

    @Override
    // MODIFY: this and enemy
    // EFFECTS : deal 1.8x base damage to enemy
    public void basicAttack(LeagueOMon enemy) {
        int damage = (int) ((this.attack + this.atkBuff) * 1.8);
        enemy.takeDamage(damage);

    }

    @Override
    // MODIFY: this and enemy
    // EFFECTS : deal 1.8x base damage to enemy
    public void secondMove(LeagueOMon enemy) {
        int damage = (int) ((this.attack + this.atkBuff) * 1.8);
        enemy.takeDamage(damage);

    }

    @Override
    // Lowers atk and defence by 20%
    public void thirdMove(LeagueOMon enemy) {
        enemy.atkBuff -= enemy.attack * 0.2;
        enemy.defBuff -= enemy.defence * 0.2;
        text.printFast(enemy.getName() + " attack decrease by 20%");
        text.printFast(enemy.getName() + " defense decrease by 20%");
    }

    @Override
    // No passive
    public void passive(LeagueOMon enemy) {
        //Stub
    }
}
