package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestCannonMinion {

    @Test
    public void testCannonMinionSelf(){
        CannonMinion test = new CannonMinion("Billy",100,90,80,70,1);
        assertEquals(test.getATK(),90);
        assertEquals(test.getHealth(),100);
        assertEquals(test.getDEF(),80);
        assertEquals(test.getSPD(),70);
        assertEquals(test.getLevel(),1);
    }

    @Test
    public void testBasicAttack(){
        CannonMinion test = new CannonMinion("Billy",100,90,80,70,1);
        CannonMinion enemy = new CannonMinion("Billy",100,90,80,70,1);
        test.basicAttack(enemy);
        assertEquals(enemy.getHealth(), 18); // Proper damage is done
    }

    @Test
    public void testSecondMove(){
        CannonMinion test = new CannonMinion("Billy",100,90,80,70,1);
        CannonMinion enemy = new CannonMinion("Billy",100,90,80,70,1);
        test.secondMove(enemy);
        assertEquals(enemy.getHealth(), 18); // Proper damage is done
    }

    @Test
    public void testThirdMove(){
        CannonMinion test = new CannonMinion("Billy",100,90,80,70,1);
        CannonMinion enemy = new CannonMinion("Billy",100,90,80,70,1);
        test.thirdMove(enemy);
        assertEquals(enemy.atkBuff, -18); // Proper damage
        assertEquals(enemy.defBuff, -16); // Proper mana used
    }


    @Test
    public void testSpecialMove(){
        CannonMinion test = new CannonMinion("Billy",100,90,80,70,1);
        CannonMinion enemy = new CannonMinion("Billy",100,90,80,70,1);
        test.specialMove(enemy);
        assertEquals(enemy.getHealth(), 9); // Proper damage is done
    }

    @Test
    public void testPassive(){
        CannonMinion test = new CannonMinion("Billy",100,90,80,70,1);
        CannonMinion enemy = new CannonMinion("Billy",100,90,80,70,1);
        test.passive(enemy);
        assertEquals(test.getATK(),90);
        assertEquals(test.getHealth(),100);
        assertEquals(test.getDEF(),80);
        assertEquals(test.getSPD(),70);
        assertEquals(test.getLevel(),1);
    }
}
