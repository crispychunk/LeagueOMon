package model;

// Class is a world object that holds what pokemon are in the wild as well as the LeagueOMon Levels

import java.util.List;
import java.util.Random;

public class World {

    private int worldLevel; // This determines difficulty in the scene
    private List<LeagueOMon> pokemonList; // This determines what pokemon will show up in the scene

    // EFFECT: Instantiate a world
    public World(int worldLevel, List<LeagueOMon> pokemonList) {
        this.worldLevel = worldLevel;
        this.pokemonList = pokemonList;
    }


    //GETTERS
    public List<LeagueOMon> getLeagueOMonList() {
        return this.pokemonList;
    }

    public int getWorldLevel() {
        return this.worldLevel;
    }


    //MODIFY: this
    //EFFECTS: change the current world level and pokemon List
    public void changeWorld(int num, List<LeagueOMon> newList) {
        this.worldLevel = num;
        this.pokemonList = newList;
    }


    //EFFECTS: Choose a random leagueOMon from pokemonList and return a clone of it
    public LeagueOMon chooseLeagueOMon() throws CloneNotSupportedException {
        int chosen = new Random().nextInt(this.pokemonList.size());
        LeagueOMon chosenRef = pokemonList.get(chosen);
        LeagueOMon chosenPoke = (LeagueOMon) chosenRef.clone();
        return chosenPoke;
    }





}
