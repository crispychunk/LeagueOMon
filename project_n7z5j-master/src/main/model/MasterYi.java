package model;

// Master Yi move sets and specifications

public class MasterYi extends LeagueOMon {
    public MasterYi(String name, int health, int attack, int defence, int speed, int level) {
        super(name, health, attack, defence, speed, level);
        setMoveName("Strike","Wuju Style","Meditation","Alpha Strike");
        leagueOMonID = 4;
        imageDirectory = "./data/Images/Characters/Yi.png";
    }

    @Override
    // Alpha Strike
    // MODIFY: this and enemy
    // EFFECT: Deals true damage with 2x base attack to enemy and print out the damage done
    public void specialMove(LeagueOMon enemy) {
        int damage = ((this.attack + this.atkBuff) * 2) + enemy.getDEF();
        enemy.takeDamage(damage);
        //text.printFast(enemy.getName() + " took " + dmgDone + "damage.");
    }

    @Override
    // MODIFY : this and enemy
    // EFFECTS: Perform a basic attack with 2x base damage
    public void basicAttack(LeagueOMon enemy) {
        int damage = this.attack * 2;
        enemy.takeDamage(damage);
        //text.printFast(enemy.getName() + " took " + dmgDone + "damage.");
    }

    // MODIFY: this and enemy
    // EFFECTS : Increase attack damage by 30%
    @Override
    public void secondMove(LeagueOMon enemy) {
        this.atkBuff += this.attack * 0.3;
        text.printFast(this.getName() + " attack increase by 30%");
        this.leagueOMonID = 4;
    }

    @Override
    // MODIFY: this and enemy
    // EFFECTS : Heal yi for 40% of his max hp
    public void thirdMove(LeagueOMon enemy) {
        int healthC = (int) (0.4 * this.maxHealth);
        this.health += healthC;
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
        text.printFast(this.getName() + " healed for " + healthC + " hp.");
    }


    public void passive(LeagueOMon enemy){
        // No passive
    }


}
