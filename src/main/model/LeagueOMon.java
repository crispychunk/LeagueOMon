package model;

import org.json.JSONObject;
import ui.PrintText;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// LeagueOMon class. This class contains all the similar methods and fields that all LeagueOMon shares.  

public abstract class LeagueOMon implements LeagueOMonMoveset,Cloneable {

    protected PrintText text;
    protected List<String> moveName;
    protected String name;
    protected int leagueOMonID;
    // These stat only change with level ups
    protected int level;
    protected int health;
    protected int maxHealth;
    protected int attack;
    protected int defence;
    protected int speed;
    protected int atkBuff;
    protected int defBuff;
    protected int spdBuff;
    protected int experience;
    protected int mana;
    protected int expNextLevel;
    protected String imageDirectory;


    // Status effects
    protected boolean isStun;
    protected boolean isBurn;
    protected boolean isConfuse;


    // Boolean value for fights
    protected boolean skipTurn;


    // EFFECTS: Initialize a new pokemon with specified stats
    public LeagueOMon(String name, int health, int attack, int defence, int speed, int level) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.attack = attack;
        this.defence = defence;
        this.speed  = speed;
        this. level = level;
        this.expNextLevel = level * 8;
        this.experience = 0;
        this.isStun = false;
        this.isBurn = false;
        this.isConfuse = false;
        this.skipTurn = false;
        this.mana = 100;
        this.text = new PrintText();
    }

    // Getters
    public int getHealth() {
        return this.health;//Stub
    }

    public int getATK() {
        return this.attack + this.atkBuff;
    }

    public int getDEF() {
        return this.defence + this.defBuff;
    }

    public int getSPD() {
        return this.speed + this.spdBuff;
    }

    public int getLevel() {
        return this.level;
    }

    public int getMana() {
        return this.mana;
    }

    public String getName() {
        return this.name;
    }

    public int getID() {
        return this.leagueOMonID;
    }

    // MODIFY : this
    // EFFECTS : Give LeagueOMon experience points if experience greater than expNextLevel, level leagueOMon up
    public void getEXP(int num) {
        this.experience += num;
        text.print(this.name + " gained " + num + " experience points");
        while (this.experience >= this.expNextLevel) {
            this.level += 1;
            this.experience -= this.expNextLevel;
            this.expNextLevel = level * 5;
            text.print(this.name + " leveled up to " + this.level);
            this.health += 10;
            this.attack += 2;
            this.defence += 2;
            this.speed += 2;
        }

    }



    // MODIFY : this
    // EFFECTS: resets all buffs and deBuffs on the LeagueOMon
    public void reset() {
        this.atkBuff = 0;
        this.defBuff = 0;
        this.spdBuff = 0;
    }

    // MODIFY : this
    // EFFECTS : Change attack of current pokemon by num
    protected void changeATK(int num) {
        this.atkBuff += num;
    }

    // MODIFY : this
    // EFFECTS : Change defence of current pokemon by num
    protected void changeDEF(int num) {
        this.defBuff += num;
    }

    // MODIFY : this
    // EFFECTS : Change speed of current pokemon by num
    protected void changeSPD(int num) {
        this.spdBuff += num;
    }

    // MODIFY : this
    // EFFECTS : Change hp of current pokemon by num
    protected void changeHealth(int num) {
        this.health += num;
    }

    //MODIFY: this
    //EFFECTS: add 4 names to the moveName list. This is the name of all the moves in order
    public void setMoveName(String move1,String move2, String move3, String move4) {
        moveName = new ArrayList<>();
        moveName.add(move1);
        moveName.add(move2);
        moveName.add(move3);
        moveName.add(move4);
    }

    //REQUIRES: move name in moveName list
    //EFFECTS: receive a move name and find the index of the move in moveName
    public int findMoveIndex(String move) {
        return moveName.indexOf(move);
    }

    //REQUIRES: 0 <= num <= 3
    //EFFECTS: returns the object in index of num in MoveName
    public String getMove(int num) {
        return moveName.get(num);
    }

    //EFFECTS: return all objects in moveName in a String format of a , b , c
    public String getMoveNames() {
        return moveName.get(0) + " , " + moveName.get(1) + " , " + moveName.get(2) + " , " + moveName.get(3);
    }

    // MODIFY: this
    //EFFECT: updates the pokemon status on universal statuses
    protected void update() {
        if (this.isBurn) {
            this.health -= this.maxHealth * 0.1;
        }
        if (this.isStun) {
            boolean val = new Random().nextInt(4) == 0;
            if (val) {
                this.skipTurn = true;
            }
        }

    }

    // MODIFY: this
    // EFFECTS: reduce the leagueOMon hp according to damage formula and return the damage done
    protected void takeDamage(int num) {
        int damage = num - (this.defence + defBuff);
        if (damage <= 0) {
            this.health -= 1;
            text.printFast(this.name + " took 1 damage.");
        } else {
            this.health -= damage;
            text.printFast(this.name + " took " + damage + " damage.");
        }
        if (this.health < 0) {
            this.health = 0;
        }
    }

    //EFFECT: create a deep copy of the object. This is use to create new LeagueOMon from a set list
    // This code snippet is taken from :
    // https://www.geeksforgeeks.org/clone-method-in-java-2/#:~:text=Creating%20a%20copy%20using%20clone()%
    // 20method&text=clone()%20to%20obtain%20the,called%20on%20that%20class's%20object.
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    //EFFECT: Create a JSON Object containing all the information of the leagueOMon and return it
    public JSONObject saveLeagueOMonData() {
        JSONObject leagueOMonData = new JSONObject();
        leagueOMonData.put("leagueOMonID",this.leagueOMonID);
        leagueOMonData.put("name",this.name);
        leagueOMonData.put("health",this.health);
        leagueOMonData.put("attack",this.attack);
        leagueOMonData.put("defence",this.defence);
        leagueOMonData.put("speed",this.speed);
        leagueOMonData.put("level",this.level);
        return leagueOMonData;
    }



    //EFFECT: Create a list with a certain amount of leagueOMon in it with 3 different LeagueOMon
    public List<LeagueOMon> makeLeagueOMonList(LeagueOMon p1, int n1, LeagueOMon p2, int n2, LeagueOMon p3, int n3) {
        List<LeagueOMon> testList = new ArrayList<>();
        for (int x = 0; x < n1; x++) {
            testList.add(p1);
        }
        for (int x = 0; x < n2; x++) {
            testList.add(p2);
        }
        for (int x = 0; x < n3; x++) {
            testList.add(p3);
        }
        return testList;
    }

    public String getImage() {
        System.out.println(this.imageDirectory);
        return this.imageDirectory;
    }
}
