package ui;

import model.Person;

import java.util.LinkedList;

public class StoryText {
    LinkedList<String> text;
    String savePoint;
    Person player;


    public StoryText(Person player) {
        text = new LinkedList<>();
        this.player = player;
        savePoint = "INTRO";
    }

    public LinkedList<String> getText() {
        return this.text;
    }

    public String getSavePoint() {
        return this.savePoint;
    }

    //EFFECT: text for intro for name
    public void introStory() {
        savePoint = "INTRO_GET_NAME";
        text = new LinkedList<>();
        text.add("Welcome to the Rift (Press enter to continue) ");
        text.add("Im your professor Oak!");
        text.add("Hm.....");


    }

    //EFFECT: text for intro after input name
    public void introStoryTwo() {
        savePoint = "INTRO_GET_RANK";
        text = new LinkedList<>();
        text.add("Hello " + player.getName() + '!');
        text.add("Judging from your looks, you seem to be of great caliber.From the looks of it, I assume you to"
                + " be around plat? Wait no even diamond!");
        text.add("So what rank are you? ");
    }

    //EFFECT: text for intro
    public void introGetRank() {
        savePoint = "INTRO_INFORMATION";
        text = new LinkedList<>();
        text.add("...");
    }

    //EFFECT: text for Rank intro after input
    public void introStoryThree() {
        savePoint = "INTRO_KENCH";
        text = new LinkedList<>();
        text.add(" :) I know ur silver. I've seen your match history for the past 10 games");
        text.add("Sad");
        text.add("Anyway...");
        text.add("Judging from you showing up... U seem to be of needing some"
                + " 'assistance' in order to climb the ladder.");
        text.add("Your a lucky one!. The new series of champs just came in today.");
        text.add("These are one of the few Series A Champions in existence!");
    }

    //EFFECT: text for Tahm Kench intro
    public void introKench() {
        savePoint = "INTRO_YI";
        text = new LinkedList<>();
        text.add("UNROLL THE TADPOLE, UNCLOG THE FROG, UNLOAD THE TOAD");
        text.add("UNINHIBIT THE RIBBIT, UNSTICK THE LICK");
        text.add("UNIMPRISON THE AMPHIBIAN, UNMUTE THE NEWT UNBENCH");
        text.add("We have Tahm Kench!");
        text.add("A versatile tank champion able to take many hits.");
        text.add("Ahm!, on the other hand, we have...");
    }

    //EFFECT: text for yi intro
    public void introYi() {
        savePoint = "CHOOSE_STARTER";
        text.add("KILLin you wit da wuju, Double strike like 1-2");
        text.add("Alpha strike hit all you");
        text.add("We have Master Yi!");
        text.add("He specializes in damage and speed");
        text.add("I will let you choose one of them.");
    }

    //EFFECT: text rival Battle
    public void outsideLab() {
        savePoint = "RIVAL_BATTLE";
        text.add("I've given you a minion too, just in case ;)");
        text.add("??? : HEY!!!!!!");
        text.add("??? : Its me, Soap! Do you remember me?");
        text.add("Soap: We met on the COD lobby once!. I was one of your teammates!");
        text.add("Soap: I've recently started playing League and was struggling, so Professor Oak "
                + "gave me a start LeagueOMon.");
        text.add("Soap: He told me my LeagueOMon is really strong! I want to test it out.");
        text.add("Soap: Lets have a battle!");
    }


}
