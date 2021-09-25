package model;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TestBlitzCrank {

    @Test

    public void testBlitzCrankSelf(){
        BlitzCrank blitzcrank = new BlitzCrank("Billy",100,90,80,70,1);
        assertEquals(blitzcrank.getATK(),90);
        assertEquals(blitzcrank.getHealth(),100);
        assertEquals(blitzcrank.getDEF(),80);
        assertEquals(blitzcrank.getSPD(),70);
        assertEquals(blitzcrank.getLevel(),1);
    }

    @Test
    public void testBasicAttack(){
        BlitzCrank blitzcrank = new BlitzCrank("Billy",100,90,80,70,1);
        BlitzCrank enemy = new BlitzCrank("Simon",100,90,80,70,1);
        blitzcrank.basicAttack(enemy);
        assertEquals(enemy.getHealth(), 18); // Proper damage is done
    }

    @Test
    public void testSecondMove(){
        BlitzCrank blitzcrank = new BlitzCrank("Billy",100,90,80,70,1);
        BlitzCrank enemy = new BlitzCrank("Simon",100,90,80,70,1);
        blitzcrank.secondMove(enemy);
        assertEquals(blitzcrank.getSPD(), 91); // Proper speed increase
        assertEquals(blitzcrank.getATK(), 117); // Proper attack increase
    }

    @Test
    public void testThirdMove(){
        BlitzCrank blitzcrank = new BlitzCrank("Billy",100,90,80,70,1);
        BlitzCrank enemy = new BlitzCrank("Simon",200,90,80,70,1);
        blitzcrank.thirdMove(enemy);
        assertEquals(enemy.getHealth(), 100); // Proper damage
        assertEquals(blitzcrank.getMana(), 80); // Proper mana used
    }


    @Test
    public void testSpecialMove(){
        BlitzCrank blitzcrank = new BlitzCrank("Billy",100,90,80,70,1);
        BlitzCrank enemy = new BlitzCrank("Simon",200,90,80,70,1);
        blitzcrank.specialMove(enemy);
        assertEquals(enemy.getHealth(), 10); // Proper damage
        assertEquals(blitzcrank.getMana(), 70); // Proper mana used
        assertTrue(enemy.isStun);
    }

    @Test
    public void testPassive(){
        BlitzCrank blitzcrank = new BlitzCrank("Billy",100,90,80,70,1);
        BlitzCrank enemy = new BlitzCrank("Simon",200,90,80,70,1);
        blitzcrank.passive(enemy);
        blitzcrank.passive(enemy);
        blitzcrank.passive(enemy);
        blitzcrank.passive(enemy);
        blitzcrank.passive(enemy);
        blitzcrank.passive(enemy);
        assertTrue(enemy.getHealth() <= enemy.maxHealth);
    }



}