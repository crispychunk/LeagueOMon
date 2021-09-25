package ui;

import model.LeagueOMon;
import model.MoveChooser;
import model.Person;

public class Battle {
    Person player;
    Person enemy;
    PrintText text;
    MoveChooser moveChooser;

    //REQUIRES: Both the player and enemy to have at least a LeagueOMon in leagueOMon list
    // EFFECT: Create a battle stage where two trainers duke it out
    public Battle(Person player, Person enemy) {
        this.player = player;
        this.enemy = enemy;
        text = new PrintText();
        moveChooser = new MoveChooser();
        LeagueOMon currFighter = player.getLeagueOMon().get(0);
        LeagueOMon enemyFighter = enemy.getLeagueOMon().get(0);
        battleIntro(currFighter,enemyFighter);
        battleAction(text, currFighter, enemyFighter, player, enemy);
        // Somebody lost and won
        currFighter.reset(); // Reset all Buff and deBuff
        enemyFighter.reset();
        if (player.getLeagueOMon().size() == 0) {
            text.print("Unfortunately, your skills were too bad for professor Oak to boost you... ");
            text.print("You end your League career knowing you did not make it to challenger...");
            System.exit(0);
        }
        if (enemy.getLeagueOMon().size() == 0) {
            text.print("you defeated " + enemy.getName());
        }

    }

    // EFFECT: Get the user to choose between 4 moves and return the index of the move in moveName of the current
    // LeagueOMon
    public int battleChoose(PrintText text, Boolean choosing,LeagueOMon currFighter) {
        int moveNum = 0;
        while (choosing) {
            String ans = text.ask("What moves will you choose? " + "( " + currFighter.getMoveNames() + ")");
            moveNum = currFighter.findMoveIndex(ans);
            if (moveNum == -1) {
                text.print("There is no such move, try again");
            } else {
                choosing = false;
            }
        }
        return moveNum;

    }

    //MODIFY: currFighter and enemyFighter
    //EFFECT: Have currFighter use the move with index of moveNum on enemyFighter. Print what move is used
    public void attackMove(LeagueOMon currFighter, int moveNum, LeagueOMon enemyFighter) {
        if (moveNum == 0) {
            System.out.println(currFighter.getName() + " use " + currFighter.getMove(0));
            currFighter.basicAttack(enemyFighter);
        } else if (moveNum == 1) {
            System.out.println(currFighter.getName() + " use " + currFighter.getMove(1));
            currFighter.secondMove(enemyFighter);
        } else if (moveNum == 2) {
            System.out.println(currFighter.getName() + " use " + currFighter.getMove(2));
            currFighter.thirdMove(enemyFighter);
        } else if (moveNum == 3) {
            System.out.println(currFighter.getName() + " use " + currFighter.getMove(3));
            currFighter.specialMove(enemyFighter);
        }
    }

    //EFFECT: Introduce the enemy as well as the player and the first LeagueOMon that they send out
    public void battleIntro(LeagueOMon currFighter, LeagueOMon enemyFighter) {
        text.print("You are fighting " + enemy.getName());
        text.print(enemy.getName() + " sends out " + enemyFighter.getName());
        text.print("You send out " + currFighter.getName());
    }

    //MODIFY: currFighter, enemyFighter,p,enemy
    // EFFECT: Decide if currFighter or enemyFighter goes first. Ask the user to choose a move and attack enemy
    // LeagueOMon. Get enemy to attack players LeagueOMon using a random move generator. Fight until either side runs
    // out of alive LeagueOMon. If player runs out first, the game ends and the player is given a message. If the
    // enemy wins then the player receives exp.
    public void battleAction(PrintText t, LeagueOMon leagueOMon, LeagueOMon enemyFighter, Person p, Person enemy) {
        while (p.getLeagueOMon().size() != 0 && enemy.getLeagueOMon().size() != 0) {
            leagueOMon.passive(enemyFighter);
            enemyFighter.passive(leagueOMon);
            int moveNum = battleChoose(text,true,leagueOMon);
            if (leagueOMon.getSPD() > enemyFighter.getSPD()) {
                attackMove(leagueOMon,moveNum, enemyFighter);
                if (enemyFighter.getHealth() != 0) {
                    int enemyMoveNum = moveChooser.chooseMove(enemyFighter);
                    attackMove(enemyFighter, enemyMoveNum, leagueOMon);
                }
            } else {
                int enemyMoveNum = moveChooser.chooseMove(enemyFighter);
                attackMove(enemyFighter, enemyMoveNum, leagueOMon);
                if (leagueOMon.getHealth() != 0) {
                    attackMove(leagueOMon,moveNum, enemyFighter);
                }

            }
            leagueOMon = battleCheckDead(t, leagueOMon,enemyFighter,p);
            enemyFighter = battleCheckDead(t, enemyFighter,leagueOMon,enemy);
        }
    }

    //MODIFY: currFighter and player
    // EFFECT: check if the currFighter has 0 hp. If so remove them from the pokemonList in player and add them to the
    // dead list. After, update the currFighter to the next LeagueOMon in the pokemonList if there is any left and print
    // a message saying the person is sending out the leagueOMon
    public LeagueOMon battleCheckDead(PrintText text,LeagueOMon leagueOMon,LeagueOMon enemy, Person person) {
        if (leagueOMon.getHealth() <= 0) {
            text.print(leagueOMon.getName() + " fainted!");
            enemy.getEXP(leagueOMon.getLevel());
            leagueOMon.reset();

            person.setDead(leagueOMon);
            if (person.getLeagueOMon().size() != 0) {
                leagueOMon = person.getLeagueOMon().get(0);
                text.print(person.getName() + " sends out " + leagueOMon.getName());
            }
        }
        return leagueOMon;
    }

}
