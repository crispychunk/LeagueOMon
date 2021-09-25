package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


// Note that the passive test may pass or fail depending if rng is in your favour
public class TestKayn {

    Kayn enemy;
    Kayn player;

    @BeforeEach
    public void setup() {
        player = new Kayn("Bobby",100,90,80,70,1);
        enemy = new Kayn("Bobby",200,100,100,100,1);
    }

    @Test
    public void testKaynSelf() {
        assertEquals(player.getATK(),90);
        assertEquals(player.getHealth(),100);
        assertEquals(player.getDEF(),80);
        assertEquals(player.getSPD(),70);
        assertEquals(player.getLevel(),1);
    }

    @Test
    public void testBasicAttack() {
        player.basicAttack(enemy);
        assertEquals(enemy.getHealth(), 138);
        assertEquals(enemy.getDEF(), 90);
    }

    @Test
    public void testSecondMove() {
        player.secondMove(enemy);
        assertEquals(enemy.getHealth(), 102);
        assertEquals(player.getMana(), 70);
    }

    @Test
    public void testThirdMove() {
        player.thirdMove(enemy);
        assertEquals(enemy.getHealth(), 165);
        assertEquals(player.getMana(), 80);
        player.thirdMove(enemy);
        player.thirdMove(enemy);
        player.thirdMove(enemy);
        player.thirdMove(enemy);
        player.thirdMove(enemy);
        player.thirdMove(enemy);
        assertTrue(enemy.getATK() < 100);
        assertTrue(enemy.getDEF() < 100);
    }

    @Test
    public void testSpecialMove() {
        player.secondMove(enemy);
        player.secondMove(enemy);
        assertEquals(enemy.getHealth(), 4);
        enemy.specialMove(player);
        assertEquals(player.getHealth(),0);
        assertEquals(enemy.getHealth(),54);
        assertEquals(enemy.getATK(), 120);
    }

    @Test
    public void testPassive() {
        player.passive(enemy);
        player.passive(enemy);
        player.passive(enemy);
        player.passive(enemy);
        player.passive(enemy);
        player.passive(enemy);
        player.passive(enemy);
        player.passive(enemy);
        player.passive(enemy);
        player.passive(enemy);
        player.passive(enemy);
        player.passive(enemy);
        player.passive(enemy);
        player.passive(enemy);
        player.passive(enemy);
        player.passive(enemy);
        player.passive(enemy);
        player.passive(enemy);
        player.passive(enemy);
        player.passive(enemy);
        player.passive(enemy);
        player.passive(enemy);
        player.passive(enemy);
        player.passive(enemy);
        assertTrue(enemy.getDEF() < 100);

    }
}
