package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMasterYi {

    MasterYi enemy;
    MasterYi player;

    @BeforeEach
    void setup() {
        player = new MasterYi("NS",100,90,80,70,1);
        enemy = new MasterYi("NB",200,90,80,70,1);
    }

    @Test
    void testSelf() {
        assertEquals(player.getATK(),90);
        assertEquals(player.getHealth(),100);
        assertEquals(player.getDEF(),80);
        assertEquals(player.getSPD(),70);
        assertEquals(player.getLevel(),1);
    }

    @Test
    void testBasicAttack() {
        player.basicAttack(enemy);
        assertEquals(enemy.getHealth(),100);
    }

    @Test
    void testSecondMove() {
        player.secondMove(enemy);
        assertEquals(player.getATK(),117);
    }

    @Test
    void testThirdMove() {
        player.basicAttack(enemy);
        assertEquals(enemy.getHealth(),100);
        enemy.thirdMove(player);
        assertEquals(enemy.getHealth(),180);
        enemy.thirdMove(player);
        assertEquals(enemy.getHealth(),200);
    }

    @Test
    void testSpecialMove() {
        player.specialMove(enemy);
        assertEquals(enemy.getHealth(),20);
    }

    @Test
    void testPassive() {
        player.passive(enemy);
        assertEquals(player.getATK(),90);
        assertEquals(player.getHealth(),100);
        assertEquals(player.getDEF(),80);
        assertEquals(player.getSPD(),70);
        assertEquals(player.getLevel(),1);
    }
}
