package model;

// Your run of the mill fodder LeagueOMon to pave way for you to level up

public class Minion extends LeagueOMon {
    public Minion(String name, int health, int attack, int defence, int speed, int level) {
        super(name, health, attack, defence, speed, level);
        setMoveName("Tackle","Shoot","Swish","Rush");
        this.leagueOMonID = 5;
        imageDirectory = "./data/Images/Characters/Paimon.png";
    }


    @Override
    // MODIFY : this and enemy
    // EFFECTS: Perform a attack with 1.6 base attack
    public void specialMove(LeagueOMon enemy) {
        int damage = (int) (this.attack * 1.6);
        enemy.takeDamage(damage);
    }

    @Override
    // MODIFY : this and enemy
    // EFFECTS: Perform a attack with 1.5 base attack
    public void basicAttack(LeagueOMon enemy) {
        int damage = (int) (this.attack * 1.5);
        enemy.takeDamage(damage);
    }

    @Override
    // MODIFY : this and enemy
    // EFFECTS: Perform a attack with 1.5 base attack
    public void secondMove(LeagueOMon enemy) {
        int damage = (int) (this.attack * 1.5);
        enemy.takeDamage(damage);
    }

    @Override
    //MODIFY: enemy
    //EFFECT: reduce enemy defence by 10%
    public void thirdMove(LeagueOMon enemy) {
        enemy.defBuff -= enemy.defence * 0.1;
        text.printFast(enemy.getName() + " defense decrease by 10%");
    }

    @Override
    public void passive(LeagueOMon enemy) {
    }


}
