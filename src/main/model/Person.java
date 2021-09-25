package model;

// Class to create a person object in the game. Can be you or other people. They can hold pokemon and engage in battle.

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private List<LeagueOMon> pokemonList;
    private List<LeagueOMon> downList;
    private List<String> peopleClear;
    private JsonWriter jsonWriter;
    private String savePoint;



    // EFFECT: Create a new player
    public Person(String name) {
        this.name = name;
        pokemonList = new ArrayList<>();
        downList = new ArrayList<>();
        peopleClear = new ArrayList<>();
        savePoint = "INTRO";
    }

    // EFFECT: Create a new player with certain savepoint
    public Person(String name, String savepoint) {
        this.name = name;
        pokemonList = new ArrayList<>();
        downList = new ArrayList<>();
        peopleClear = new ArrayList<>();
        savePoint = savepoint;
    }

    //GETTERS
    public String getName() {
        return this.name; //Stub
    }

    public List<LeagueOMon> getLeagueOMon() {
        return this.pokemonList;
    }

    public List<LeagueOMon> getDownList() {
        return this.downList;
    }

    public String getSavePoint() {
        return this.savePoint;
    }

    // MODIFY : this
    // EFFECT: add pokemon to player list
    public void addPokemon(LeagueOMon pokemon) {
        pokemonList.add(pokemon);
    }

    // REQUIRES: pokemon must be in pokemonList
    // MODIFY: this
    // EFFECT: remove pokemon from player list
    public void removePokemon(LeagueOMon pokemon) {
        pokemonList.remove(pokemon);
    }

    // MODIFY: this
    // EFFECT: remove pokemon from player list
    public void removeAllPokemon() {
        if (pokemonList.size() != 0) {
            for (LeagueOMon l : pokemonList) {
                pokemonList.remove(l);

            }
        }
    }


    // MODIFY: this
    // EFFECTS: set player name to parameter name
    public void setName(String name) {
        this.name = name;
    }

    // MODIFY: this
    // EFFECTS : remove leagueOMon from pokemonList and send it to dead list
    public void setDead(LeagueOMon leagueOMon) {
        pokemonList.remove(leagueOMon);
        downList.add(leagueOMon);
    }

    //MODIFY: this
    // EFFECT: add curr to downList
    public void addDownList(LeagueOMon curr) {
        this.downList.add(curr);
    }


    // MODIFY: JsonWriter
    // EFFECT: Save Player Data to PlayerData.json
    public void saveToFile(String source1, String source2) {
        jsonWriter = new JsonWriter(source1);
        JSONObject playerDetail = new JSONObject();
        playerDetail.put("name",this.name);
        playerDetail.put("savePoint",this.savePoint);
        try {
            jsonWriter.open();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        jsonWriter.saveToFile(playerDetail.toString(4));
        jsonWriter.close();
        saveToFileLeagueOMon(source2);
    }

    // MODIFY: JsonWriter
    // EFFECT: Save LeagueOMon data to LeagueOMon.json
    public void saveToFileLeagueOMon(String source) {
        jsonWriter = new JsonWriter(source);
        JSONArray leagueOMonList = new JSONArray();
        JSONObject curr;
        for (LeagueOMon x : pokemonList) {
            curr = x.saveLeagueOMonData();
            leagueOMonList.put(curr);
        }
        JSONArray downList = new JSONArray();
        for (LeagueOMon x : this.downList) {
            curr = x.saveLeagueOMonData();
            downList.put(curr);
        }
        JSONObject totalList = new JSONObject();
        totalList.put("leagueOMon",leagueOMonList);
        totalList.put("downList",downList);
        try {
            jsonWriter.open();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to open file");
        }
        jsonWriter.saveToFile(totalList.toString(4));
        jsonWriter.close();
    }

    // MODIFY: this
    // EFFECT: Set the savepoint of the person to savePoint parameter
    public void setSavePoint(String savePoint) {
        this.savePoint = savePoint;
    }
}

