package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMoveChooser {

    LeagueOMon test;
    MoveChooser chooser;

    @BeforeEach
    public void setup() {
        test = new Kayn("player",100,100,100,100,1);
        chooser = new MoveChooser();
    }

    @Test

    public void testMoveSetOne() {
        int choice = chooser.chooseMove(test);
        assertTrue(choice == 0 || choice == 1);
    }

    @Test
    public void testMoveSetTwo() {
        test.health = 69;
        int choice = chooser.chooseMove(test);
        assertTrue(choice == 1 || choice == 2);
    }

    @Test
    public void testMoveSetThree() {
        test.health = 34;
        int choice = chooser.chooseMove(test);
        assertTrue(choice == 2 || choice == 3);
    }

}
