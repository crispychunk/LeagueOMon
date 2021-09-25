package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLeagueOMon {

    LeagueOMon test;


    @BeforeEach
    void setup() {
        test = new Kayn("Tester",100,100,100,100,1);
    }


    @Test

    void testGetters() {
        assertEquals(test.getATK(),100);
        assertEquals(test.getDEF(),100);
        assertEquals(test.getSPD(),100);
        assertEquals(test.getLevel(),1);
        assertEquals(test.getMana(),100);
        assertTrue(test.getMoveNames().equals("Strike , Blade Reach , Reaping Slash , Umbral Trespass"));
        assertTrue(test.getMove(0).equals("Strike"));
        assertTrue(test.getName().equals("Tester"));
        assertEquals(test.findMoveIndex("Strike"),0);
    }

    @Test
    void testChange() {
        test.changeATK(5);
        assertEquals(test.getATK(),105);
        test.changeDEF(5);
        assertEquals(test.getDEF(),105);
        test.changeHealth(-20);
        assertEquals(test.getHealth(),80);
        test.changeSPD(5);
        assertEquals(test.getSPD(),105);
    }

    @Test
    void testUpdate() {
        test.isBurn = true;
        test.update();
        assertEquals(test.getHealth(),90);
        test.isStun = true;
        test.isBurn = false;
        test.update();
        test.update();
        test.update();
        test.update();
        test.update();
        test.update();
        test.update();
        test.update();
        test.update();
        test.update();
        assertTrue(test.skipTurn);
    }

    @Test
    void testTakeDamage()  {
        LeagueOMon enemy = new TKench("enemy",100,100,100,100,1);
        test.basicAttack(enemy);
        assertEquals(enemy.getHealth(),20);
        test.basicAttack(enemy);
        assertEquals(enemy.getHealth(),0);
        LeagueOMon enemy2 = new TKench("enemy",100,100,1000,100,1);
        test.basicAttack(enemy2);
        assertEquals(enemy2.getHealth(),99);
    }

    @Test
    void testReset() {
        test.changeSPD(5);
        test.changeDEF(5);
        test.changeATK(5);
        test.reset();
        assertEquals(test.getATK(),100);
        assertEquals(test.getDEF(),100);
        assertEquals(test.getSPD(),100);

    }
}
