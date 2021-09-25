package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestPerson {
    Person player;
    LeagueOMon kench;
    LeagueOMon blitz;
    JsonReader reader;

    @BeforeEach
    public void setup() {
        player = new Person("Simon");
        kench = new TKench("donny", 100, 100, 100, 100, 1);
        blitz = new BlitzCrank("jacob", 100, 100, 100, 100, 1);
        reader = new JsonReader("./data/PlayerDataTest.json","./data/PlayerDataLeagueOMonTest.json");
    }

    @Test
    public void testSetName() {
        assertTrue(player.getName() == "Simon");
        player.setName("BOB");
        assertTrue(player.getName() == "BOB");
    }

    @Test
    public void testPersonSelf(){
        assertEquals(player.getName(), "Simon");
    }

    @Test
    public void testGetLeagueOMonList () {
        player.addPokemon(kench);
        player.addPokemon(blitz);
        assertEquals(player.getLeagueOMon().size(),2);
        assertTrue(player.getLeagueOMon().get(0) == kench);
        assertTrue(player.getLeagueOMon().get(1) == blitz);
    }

    @Test
    public void testAddPokemon(){
        assertEquals(player.getLeagueOMon().size(),0);
        player.addPokemon(kench);
        assertEquals(player.getLeagueOMon().size(),1);
        assertTrue(player.getLeagueOMon().get(0) == kench);
    }
    @Test
    public void testRemovePokemon(){
        player.addPokemon(kench);
        player.addPokemon(blitz);
        assertEquals(player.getLeagueOMon().size(),2);
        player.removePokemon(blitz);
        assertEquals(player.getLeagueOMon().size(),1);
        assertFalse(player.getLeagueOMon().contains(blitz));
    }

    @Test
    public void testSetDead(){
        player.addPokemon(kench);
        player.addPokemon(blitz);
        player.setDead(kench);
        assertFalse(player.getLeagueOMon().contains(kench));
        assertTrue(player.getDownList().contains(kench));
    }

    @Test
    public void testAddDownList() {
        player.addDownList(kench);
        assertTrue(player.getDownList().contains(kench));
    }

    @Test
    public void testSaveToData() {
        player.addPokemon(kench);
        player.addPokemon(blitz);
        player.addPokemon(kench);
        Person newPerson = null;
        player.saveToFile("./data/PlayerDataTest.json","./data/PlayerDataLeagueOMonTest.json");
        try {
            newPerson = reader.jsonReadSave();
        } catch (IOException e) {
            fail("file not found!");
        }
        assertEquals(newPerson.getName(),player.getName());
        assertEquals(newPerson.getSavePoint(),player.getSavePoint());
    }

    @Test
    public void testSaveToFileLeagueOMon() {
        player.addPokemon(kench);
        player.addPokemon(blitz);
        player.addPokemon(kench);
        player.addDownList(kench);
        Person newPerson = null;
        player.saveToFile("./data/PlayerDataTest.json","./data/PlayerDataLeagueOMonTest.json");
        try {
            newPerson = reader.jsonReadSave();
        } catch (IOException e) {
            fail("file not found!");
        }
        assertEquals(newPerson.getLeagueOMon().size(),player.getLeagueOMon().size());
        assertEquals(newPerson.getDownList().size(),player.getDownList().size());

    }

    @Test
    public void testSavePoint() {
        assertTrue(player.getSavePoint().equals("OAK"));
        player.setSavePoint("RIVAL");
        assertFalse(player.getSavePoint().equals("OAK"));
        assertTrue(player.getSavePoint().equals("RIVAL"));
    }
}
