package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class TestJsonReader {
    Person player;
    JsonReader reader;
    @BeforeEach
    void setup(){
        reader = new JsonReader("./data/PlayerData.json","./data/PlayerDataLeagueOMon.json");
    }

    @Test
    void testReader()  {
        try {
            this.player = reader.jsonReadSave();
        } catch (IOException e) {
            fail("IOException thrown");
        }

    }
}
