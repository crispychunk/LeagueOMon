package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTKench {

    TKench player;
    TKench enemy;

    @BeforeEach
    void setup() {
        player = new TKench("NB",100,90,80,70,1);
        enemy = new TKench("NB",200,90,80,70,1);
    }


    @Test
    public void testSelf() {
        assertEquals(player.getATK(),90);
        assertEquals(player.getHealth(),100);
        assertEquals(player.getDEF(),80);
        assertEquals(player.getSPD(),70);
        assertEquals(player.getLevel(),1);
    }

    @Test
    public void testBasicAttack() {
        player.basicAttack(enemy);
        assertEquals(145,enemy.getHealth());
    }

    @Test
    public void testSecondMove() {
        player.secondMove(enemy);
        assertEquals(88,player.getDEF());
        assertEquals(72,enemy.getDEF());
    }

    @Test
    public void testThirdMove() {
        player.thirdMove(enemy);
        assertEquals(enemy.getHealth(),100);
        assertEquals(player.getMana(),80);
    }

    @Test
    public void testSpecialMove() {
        player.specialMove(enemy);
        assertEquals(60,player.getMana());
        assertEquals(180,player.getATK());
        assertEquals(210,player.getSPD());
        assertEquals(32,player.getDEF());
    }

    @Test
    public void testPassive() {
        enemy.basicAttack(player);
        player.passive(enemy);
        player.passive(enemy);
        assertEquals(player.getHealth(),50);
    }
}
