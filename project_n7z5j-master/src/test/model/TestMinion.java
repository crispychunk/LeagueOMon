package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMinion {

    Minion test;
    Minion enemy;

    @BeforeEach

    void setup() {
        test = new Minion("Bill",100,90,80,70,1);
        enemy = new Minion("Bill",200,90,80,70,1);
    }

    @Test
    void testSelf() {
        assertEquals(test.getATK(), 90);
        assertEquals(test.getDEF(), 80);
        assertEquals(test.getHealth(), 100);
        assertEquals(test.getSPD(), 70);
        assertEquals(test.getLevel(), 1);
    }

    @Test
    void testBasicAttack() {
        test.basicAttack(enemy);
        assertEquals(enemy.getHealth(), 145); // Proper damage is done
    }

    @Test
    void testSecondMove() {
        test.basicAttack(enemy);
        assertEquals(enemy.getHealth(), 145); // Proper damage is done
    }

    @Test
    void testThirdMove() {
        test.thirdMove(enemy);
        assertEquals(enemy.getDEF(), 72); // Proper damage is done
    }

    @Test
    void testSpecialMove() {
        test.specialMove(enemy);
        assertEquals(enemy.getHealth(), 136); // Proper damage is done
    }

    @Test
    void testPassive() {
        test.passive(enemy);
        assertEquals(test.getATK(), 90);
        assertEquals(test.getDEF(), 80);
        assertEquals(test.getHealth(), 100);
        assertEquals(test.getSPD(), 70);
        assertEquals(test.getLevel(), 1);
    }

}
