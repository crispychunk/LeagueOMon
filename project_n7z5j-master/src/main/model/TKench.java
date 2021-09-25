package model;


import java.util.Random;
// Tom Kench Champion

public class TKench extends LeagueOMon  {

    // TKench has a special ability that allows him to heal himself for 5% every 2 turns
    int counter;

    public TKench(String name, int health, int attack, int defence, int speed, int level) {
        super(name, health, attack, defence, speed, level);
        this.counter = 0;
        setMoveName("Lick","Sneer","Spit","Transform");
        this.imageDirectory = "./data/Images/Characters/kench.png";
        this.leagueOMonID = 6;
    }

    @Override
    //MODIFY: this and enemy
    //EFFECTS: Tom Kench uses 40% of his remaining mana to transform
    // and has his atk 2x,speed 3x greatly increase but defense greatly decreased (0.6x)
    public void specialMove(LeagueOMon enemy) {
        this.atkBuff += this.attack;
        this.defBuff -= this.defence * 0.6;
        this.spdBuff += this.speed * 2;
        this.mana -= this.mana * 0.4;
        text.printFast(this.getName() + " attack increased by 100%");
        text.printFast(this.getName() + " speed increased by 200%");
        text.printFast(this.getName() + " defense decrease by 60%");
    }


    // MODIFY : this and enemy
    // EFFECTS: Perform a basic attack(1.5x) on the enemy and deal base ATk with a 10% chance to stun the enemy
    @Override
    public void basicAttack(LeagueOMon enemy) {
        int damage = (int)((this.attack + this.atkBuff) * 1.5);
        enemy.takeDamage(damage);
        boolean val = new Random().nextInt(10) == 0;
        if (val) {
            enemy.isStun = true;
        }

    }

    @Override
    //MODIFY: this and enemy
    //EFFECTS: Increase defence by 10% and lower enemy defence by 10%
    public void secondMove(LeagueOMon enemy) {
        this.defBuff += (this.defence * 0.1);
        enemy.defBuff -= enemy.defence * 0.1;
        text.printFast(this.getName() + " defense increased by 10%");
        text.printFast(enemy.getName() + " defense decrease by 10%");
    }

    @Override
    //MODIFY: this and enemy
    //EFFECTS: Use 20 mana and with 2x dmg modifier deal damage to enemy
    public void thirdMove(LeagueOMon enemy) {
        int damage = (this.attack + this.atkBuff) * 2;
        enemy.takeDamage(damage);
        this.mana -= 20;

    }

    /*
    @Override
    //MODIFY: this and enemy ** STill needs to be implemented
    // EFFECTS : Give LeagueOMon experience points if experience greater than expNextLevel, level leagueOMon up
    // Per level increase, change TKench stat by a set amount
    public void getEXP(int num) {
        this.experience += num;
        while (this.experience >= this.expNextLevel) {
            this.level += 1;
            this.experience -= this.expNextLevel;
            this.expNextLevel = level * 8;



            System.out.println(this.name + " leveled up to " + this.level);
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String ans = input.readLine();



            // Kench own stat increase
            this.maxHealth += 20;
            this.attack += 4;
            this.defence += 6;
            this.speed += 2;
        }
    }
    */

    //MODIFY: this
    //EFFECTS: Kench gains a shield every 2 turns and turns it into health
    public void passive(LeagueOMon enemy) {
        counter++;
        if (counter == 2) {
            this.health += this.maxHealth * 0.05;
            text.printFast(enemy.getName() + "healed with his passive.");
            counter = 0;
        }
    }
}
