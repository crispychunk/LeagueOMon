package ui;

import model.*;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// ** This is where the story is located **
// This class contains all the different stories that can be called. It is to be updated periodically to add new content

public class Story {
    Person player;
    World currWorld;
    PrintText text;
    JsonReader reader;

    // Initialize a new story
    public Story() {
        player = new Person("0");
        List<LeagueOMon> leagueOMonList = new ArrayList<>();
        currWorld = new World(1,leagueOMonList);
        text = new PrintText();
        reader = new JsonReader("./data/PlayerData.json","./data/PlayerDataLeagueOMon.json");
    }

    // MODIFY: this
    //EFFECT : Give the player intro, and also make the player choose a starter
    public void intro() {
        //panel.setBackgroundImage("./data/Images/choose_background.png");
        //JLabel professorOak = new JLabel(new ImageIcon("./data/Images/Characters/professor_oak.png"));
        //professorOak.setBounds(200,200,50,50);
        //panel.add(professorOak);
       // panel.changeText("Hello");
       // panel.repaint();

        text.print("Welcome to the Rift (Press enter to continue) ");
        text.print("Im your professor oak!");
        text.print("Hm.....");
        String ans = text.ask("What is your name?");
        player.setName(ans);
        text.print("Hello " + player.getName() + '!');
        text.print("Judging from your looks, you seem to be of great caliber.From the looks of it, I assume you to"
                + " be around plat? Wait no even diamond!");
        text.ask("So what rank are you? ");
        text.print(" :) I know ur silver. I've seen your match history for the past 10 games");
        text.print("Sad");
        text.print("Anyway...");
        text.print("Judging from you showing up... U seem to be of needing some"
                + " 'assistance' in order to climb the ladder.");
        introTwo();
    }

    //MODIFY: this
    //EFFECT : explain the starters and make the player choose one of them
    public void introTwo() {
        text.print("Your a lucky one!. The new series of champs just came in today.");
        text.print("These are one of the few Series A Champions in existence!");
        text.print("UNROLL THE TADPOLE, UNCLOG THE FROG, UNLOAD THE TOAD");
        text.print("UNINHIBIT THE RIBBIT, UNSTICK THE LICK");
        text.print("UNIMPRISON THE AMPHIBIAN, UNMUTE THE NEWT UNBENCH");
        text.print("We have Tahm Kench!");
        text.print("A versatile tank champion able to take many hits.");
        text.print("Ahm!, on the other hand, we have...");
        text.print("KILLin you wit da wuju, Double strike like 1-2");
        text.print("Alpha strike hit all you");
        text.print("We have Master Yi!");
        text.print("He specializes in damage and speed");
        text.print("I will let you choose one of them.");
        int ans = chooseOption(text,"Master Yi", "Tahm Kench", "So which one will you choose? (Master Yi,Tahm Kench) ");
        LeagueOMon starter;
        if (ans == 0) {
            starter = new MasterYi("Master Yi", 400, 100, 90, 150, 1);
        } else {
            starter = new TKench("Tahm Kench", 600, 80, 110, 90, 1);
        }
        player.addPokemon(starter);
        saveAndQuit("RIVAL");
        rivalBattle();
    }

    //MODIFY: this
    // EFFECT: after your talk with professor Oak, you go outside and meet your rival. He challanges you with his
    // LeagueOMon
    public void rivalBattle() {
        text.print("Congrats on your new LeagueOMon!. I wish you luck on your grind.");
        text.print("You walk out of Professor Oaks lab...");
        text.print("On your way out to the path you hear some footsteps behind you...");
        text.print("??? : HEY!!!!!!");
        text.print("??? : Its me, Soap! Do you remember me?");
        text.print("Soap: We met on the COD lobby once!. I was one of your teammates!");
        text.print("Soap: I've recently started playing League and was struggling, so Professor Oak "
                    + "gave me a start LeagueOMon.");
        text.print("Soap: He told me my LeagueOMon is really strong! I want to test it out.");
        text.print("Soap: Lets have a battle!");
        Person lent = new Person("Soap");
        LeagueOMon blitz = new BlitzCrank("Blitz",100,30,40,100,1);
        lent.addPokemon(blitz);
        new Battle(player,lent);
        text.print("Soap: Wow! Your LeagueOMon is really strong");
        text.print("Soap: I guess Im still not ready for rank up yet...");
        text.print("Soap: I should go train and try to head to Sharlot Town");
        text.print("Soap: I need to fight their gym leader, Kevin, in order to rank up");
        text.print("Soap: Guess I will see you there!");
        text.print("You see Soap hastily run off into the woods until he disappears into the foliage.");
        text.print("You also notice a path to your right. You wonder why Soap didn't take the path.");
        saveAndQuit("Route1");
        routeOne();
    }

    public void routeOne() {
        text.print("You arrive at the edge of Route One");
        int ans = chooseOption(text,"The Path", "Into the woods", "So which one will you choose? "
                + "(The Path, Into the woods)");
        if (ans == 0) {
            routeOnePath();
        }
        if (ans == 1) {
            routeOneWoods();
        }
    }

    public void routeOneWoods() {
        List<LeagueOMon> pokemonList;
        LeagueOMon minion = new Minion("minion",10,10,10,10,1);
        LeagueOMon cannonMinion = new CannonMinion("CM",10,10,10,10,1);
        pokemonList = minion.makeLeagueOMonList(minion,10,cannonMinion,2,null,0);
        World woods = new World(1,pokemonList);
        text.print("You travel into the woods...");
        text.print("...");
        text.print("...");
        text.print("SWosh!");
        currWorld = woods;
        try {
            new BattleWild(player, currWorld.chooseLeagueOMon());
            new BattleWild(player, currWorld.chooseLeagueOMon());
            text.print("You travel deeper into the woods...");
            new BattleWild(player, currWorld.chooseLeagueOMon());
        } catch (CloneNotSupportedException e) {
            System.out.println("Failed to clone.");
        }
        routeOneWoodsContinue();
    }

    public void routeOneWoodsContinue() {
        Boolean forest = true;
        while (forest) {
            int ans = chooseOption(text,"path","forest","You see a path in front of you,"
                    + " where will you go? (forest, path)");
            if (ans == 0) {
                forest = false;
                routeOnePath();
            }
            if (ans == 1) {
                try {
                    new BattleWild(player, currWorld.chooseLeagueOMon());
                    text.print("You travel deeper into the woods...");
                } catch (CloneNotSupportedException e) {
                    System.out.println("Failed to clone.");
                }
            }
        }
    }

    public void routeOnePath() {
        text.print("Walking out of the woods you see a person");
        text.print("Youngster Joey: Hello Traveller!");
        text.print("Youngster Joey wants to fight");
        player.setSavePoint("Route1Path");
        Person youngsterJoey = new Person("Youngster Joey");
        LeagueOMon kayn = new Kayn("Rattata",200,80,70,60,1);
        LeagueOMon kayn2 = new Kayn("Rattata",200,80,79,70,2);
        youngsterJoey.addPokemon(kayn);
        youngsterJoey.addPokemon((kayn2));
        new Battle(player,youngsterJoey);
    }

    // MODIFY: this and enemy
    // EFFECT: Battle the player leagueOMon and enemy wild leagueOMon
    public void battleWild(Person player, LeagueOMon enemy) {
        //Stub
    }

    // MODIFY: this
    // EFFECT: Have the player battle the Gym leader to rank out of silver
    public void rankUpBattle() {
        //Stub

    }








    // EFFECT : Take two options and have the user choose one of them. If none of the inputs match, ask the user again
    public int chooseOption(PrintText text, String option1, String option2, String message) {
        boolean notDone = true;
        int value = -1;
        while (notDone) {
            String ans = text.ask(message);
            if (ans.equals(option1)) {
                value = 0;
                notDone = false;
            } else if (ans.equals(option2)) {
                value = 1;
                notDone = false;
            }
            if (notDone) {
                text.print("That is not a valid answer");
            }
        }
        return value;
    }

/*
    //MODIFY: this
    //EFFECT: Run the story
    public void run() {
        int ans = chooseOption(text,"load","new","load or new save? (load, new)");
        if (ans == 1) {
            newPlay();
        } else {
            loadPlay();
        }
    }
*/



    //MODIFY: this
    //Overwrite the save and start a new game
    public void newPlay(MainPanel panel) {
        player.saveToFile("./data/PlayerData.json","./data/PlayerDataLeagueOMon.json");
        intro();

    }

    //MODIFY: this
    // Load the player data to resume the game
    public void loadPlay(MainPanel panel) {
        try {
            this.player = reader.jsonReadSave();
        } catch (IOException e) {
            System.out.println("reader fail!");
        }
        loadSavePoint(panel);

    }

    // Run method base on each savePoint
    public void loadSavePoint(MainPanel panel) {
        String savePoint = this.player.getSavePoint();
        switch (savePoint) {
            case "OAK":
                intro();
                break;
            case "RIVAL":
                rivalBattle();
                break;
            case "Route1":
                routeOne();
                break;
            case "Route1Path":
                routeOnePath();
                break;
            default:
                //null
        }
    }

    //EFFECT: Either saves or save and quit at a certain checkpoint in the story
    public void saveAndQuit(String savePoint) {
        this.player.setSavePoint(savePoint);
        int ans = chooseOption(text,"quit","continue","Do you want to quit(Progress will"
                + " be save regardless) (quit, continue)");
        if (ans == 0) {
            player.saveToFile("./data/PlayerData.json","./data/PlayerDataLeagueOMon.json");
            System.exit(0);
        }
        player.saveToFile("./data/PlayerData.json","./data/PlayerDataLeagueOMon.json");
    }
}