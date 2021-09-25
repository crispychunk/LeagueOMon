package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestWorld {

    World test;

    @BeforeEach
    public void setup() {
        List<LeagueOMon> testList = new ArrayList<>();
        for (int x = 0; x <= 10; x++){
            LeagueOMon test = new BlitzCrank("Billy",100,90,80,70,1);
            testList.add(test);
        }
        for (int x = 0; x <= 10; x++){
            LeagueOMon test = new TKench("Billy",100,90,80,70,1);
            testList.add(test);
        }
        test = new World(1, testList);

    }

    @Test
    public void testChooseLeagueOMon() throws CloneNotSupportedException {
        LeagueOMon chosenOne = test.chooseLeagueOMon();
        assertFalse(test.getLeagueOMonList().contains(chosenOne)); // Its because its a brand new object
    }

    @Test
    public void testGetWorld() {
        assertEquals( test.getWorldLevel(),1);
    }

    @Test
    public void testChangeWorld() {
        assertEquals( test.getWorldLevel(),1);
        List<LeagueOMon> testList = new ArrayList<>();
        for (int x = 0; x <= 10; x++){
            LeagueOMon test = new BlitzCrank("Billy",100,90,80,70,1);
            testList.add(test);
        }
        for (int x = 0; x <= 10; x++){
            LeagueOMon test = new TKench("Bob",100,90,80,70,1);
            testList.add(test);
        }
        test.changeWorld(5,testList);
        assertEquals( test.getWorldLevel(),5);
    }
}
