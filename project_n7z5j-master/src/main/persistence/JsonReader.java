package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
// ********* Code taken from JsonSerializationDemo *********
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;
    private String sourceLeagueOMon;
    int leagueOMonID;
    int defence;
    int level;
    int attack;
    String leagueOMonName;
    int health;
    int speed;

    // Constructor is based on JsonSerializationDemo's construction, except this has 2 sources.
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String sourcePlayer,String sourceLeagueOMon) {
        this.source = sourcePlayer;
        this.sourceLeagueOMon = sourceLeagueOMon;
    }

    // EFFECTS: reads source file as string and returns it

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECT: Read Both saveFiles and create a new Person object which will be the player and load all saved data to it
    public Person jsonReadSave() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        String name = jsonObject.getString("name");
        String savepoint = jsonObject.getString("savePoint");
        Person player = new Person(name,savepoint);
        loadAliveLeagueOMons(player,this.sourceLeagueOMon);
        loadDownLeagueOMons(player,this.sourceLeagueOMon);
        return player;
    }

    //EFFECT: load DownList of LeagueOMon's from save and add it to player
    private void loadDownLeagueOMons(Person player, String sourceLeagueOMon) throws IOException {
        String jsonData = readFile(sourceLeagueOMon);
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray leagueOMonList = jsonObject.getJSONArray("downList");
        for (int x = 0; x < leagueOMonList.length(); x++) {
            JSONObject leagueOMon = leagueOMonList.getJSONObject(x);
            leagueOMonID = (int) leagueOMon.get("leagueOMonID");
            defence = (int) leagueOMon.get("defence");
            level = (int) leagueOMon.get("level");
            attack = (int) leagueOMon.get("attack");
            leagueOMonName = (String) leagueOMon.get("name");
            health = (int) leagueOMon.get("health");
            speed = (int) leagueOMon.get("speed");
            LeagueOMon curr = leagueOMonLoader(leagueOMonName,health,attack,defence,speed,level,leagueOMonID);
            player.addDownList(curr);
        }
    }

    //EFFECT: load RegularList of LeagueOMon's from save and add it to player
    public void loadAliveLeagueOMons(Person player, String sourceLeagueOMon) throws IOException {
        String jsonData = readFile(sourceLeagueOMon);
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray leagueOMonList = jsonObject.getJSONArray("leagueOMon");
        for (int x = 0; x < leagueOMonList.length(); x++) {
            JSONObject leagueOMon = leagueOMonList.getJSONObject(x);
            leagueOMonID = (int) leagueOMon.get("leagueOMonID");
            defence = (int) leagueOMon.get("defence");
            level = (int) leagueOMon.get("level");
            attack = (int) leagueOMon.get("attack");
            leagueOMonName = (String) leagueOMon.get("name");
            health = (int) leagueOMon.get("health");
            speed = (int) leagueOMon.get("speed");
            LeagueOMon curr = leagueOMonLoader(leagueOMonName,health,attack,defence,speed,level,leagueOMonID);
            player.addPokemon(curr);
        }

    }

    // Create A leagueOMon actual class base on its input parameter and type t
    public LeagueOMon leagueOMonLoader(String name, int health, int attack, int defence, int speed, int level, int t) {
        switch (t) {
            case 1:
                return new BlitzCrank(name,health,attack,defence,speed,level);
            case 2:
                return new CannonMinion(name,health,attack,defence,speed,level);
            case 3:
                return new Kayn(name,health,attack,defence,speed,level);
            case 4:
                return new MasterYi(name,health,attack,defence,speed,level);
            case 5:
                return new Minion(name,health,attack,defence,speed,level);
            case 6:
                return new TKench(name,health,attack,defence,speed,level);
        }
        return null;
    }
}
