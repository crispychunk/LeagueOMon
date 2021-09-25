package ui;

import model.BlitzCrank;
import model.LeagueOMon;
import model.Minion;
import model.Person;

import javax.swing.*;
import java.awt.event.MouseListener;

public class StoryGui {
    private MainPanel panel;
    private StoryText storyText;
    public Person player;
    ActionCreator creator;
    MouseListener mouseListener;

    public StoryGui(MainPanel panel) {
        this.panel = panel;
        this.player =   new Person("");
        this.storyText = new StoryText(player);
        this.creator = new ActionCreator(this,panel);
    }

    //EFFECT: Depending on the savepoint of player.getsavepoint(), run the appropriate method
    public void runSavePoint() {
        switch (player.getSavePoint()) {
            case "INTRO" :
                intro();
            case "INTRO_GET_NAME":
                introGetName();
                break;
            case "INTRO_GET_RANK":
                introGetRank();
                break;
            case "INTRO_INFORMATION":
                introStoryThree();
                break;

        }
        runSavePointContinue();
    }

    //EFFECT: Depending on the savepoint of player.getsavepoint(), run the appropriate method
    public void runSavePointContinue() {
        switch (player.getSavePoint()) {
            case "INTRO_KENCH":
                introKench();
                break;
            case "INTRO_YI":
                introYi();
                break;
            case "CHOOSE_STARTER":
                chooseStarter();
                break;
            case "RIVAL_BATTLE":
                rivalBattle();
                break;
        }

    }

    //EFFECT: Setting up background, and have player be introduced
    public void intro() {
        panel.setTextVisible(true);
        panel.setCharacter("./data/Images/Characters/professor_oak.png");
        panel.setBackgroundImage("./data/Images/choose_background.png");
        panel.setCharacterVisible(true);
        panel.setButtonVisible(false);
        storyText.introStory();
        mouseListener = creator.nextLine(storyText,panel.getText());
        panel.addMouseListener(mouseListener);

    }

    //EFFECT: Getting the name of the player
    public void introGetName() {
        panel.removeMouseListener(mouseListener);
        System.out.println("Now going to intro 2");
        panel.setText("What is your name? (Press mouse after entering");
        panel.add(panel.inputField);
        mouseListener = creator.getName();
        panel.addMouseListener(mouseListener);
        panel.repaint();
    }

    //EFFECT: After mouse click, set the player name to the inputField
    public void getName() {
        panel.removeMouseListener(mouseListener);
        player.setName(panel.inputField.getText());
        this.storyText = new StoryText(player);
        player.saveToFile("./data/PlayerData.json","./data/PlayerDataLeagueOMon.json");
        panel.setText("<html>...<html>");
        panel.inputField.setVisible(false);
        introTwo();
    }

    //EFFECT: Continue the introduction
    public void introTwo() {
        storyText.introStoryTwo();
        mouseListener = creator.nextLine(storyText,panel.getText());
        panel.addMouseListener(mouseListener);
    }

    //EFFECT: Ask for rank
    public void introGetRank() {
        panel.removeMouseListener(mouseListener);
        panel.inputField.setVisible(true);
        panel.inputField.setText("");
        storyText.introGetRank();
        mouseListener = creator.nextLine(storyText,panel.getText());
        panel.addMouseListener(mouseListener);
        panel.repaint();
    }

    //EFFECT: Ask for rank after input
    public void introStoryThree() {
        panel.removeMouseListener(mouseListener);
        panel.inputField.setVisible(false);
        panel.repaint();
        storyText.introStoryThree();
        mouseListener = creator.nextLine(storyText,panel.getText());
        panel.addMouseListener(mouseListener);
    }

    //EFFECT: Introduce Tahm Kench
    public void introKench() {
        panel.removeMouseListener(mouseListener);
        storyText.introKench();
        panel.setCharacter("./data/Images/Characters/kench.png");
        mouseListener = creator.nextLine(storyText,panel.getText());
        panel.addMouseListener(mouseListener);
    }

    //EFFECT: Introduce Master Yi
    private void introYi() {
        panel.removeMouseListener(mouseListener);
        storyText.introYi();
        panel.setCharacter("./data/Images/Characters/Yi.png");
        mouseListener = creator.nextLine(storyText,panel.getText());
        panel.addMouseListener(mouseListener);
    }

    //MODIFY: this
    //EFFECT: Get the player to choose a starter
    public void chooseStarter() {
        panel.setText("Which one will you choose?");
        panel.removeMouseListener(mouseListener);
        panel.setCharacter("./data/Images/Characters/professor_oak.png");
        panel.option1 = new JButton("Master Yi");
        panel.option2 = new JButton("T Kench");
        panel.option1.setBounds(350,350,100,40);
        panel.option2.setBounds(350,300,100,40);
        panel.add(panel.option1);
        panel.add(panel.option2);
        panel.option1.addActionListener(creator.chooseYi(player));
        panel.option2.addActionListener(creator.chooseKench(player));
    }

    //MODIFY: this
    //EFFECT: Give the player a minion as backup. Run the outside lab story
    public void outsideLab() {
        panel.pokemonList.setVisible(true);
        panel.addPokemonList(player.getLeagueOMon().get(0).getName());
        panel.option1.setVisible(false);
        panel.option2.setVisible(false);
        panel.setCharacterVisible(false);
        panel.setBackgroundImage("./data/Images/road66.png");
        storyText.outsideLab();
        panel.setText("Nice choice and Good Luck! I added ");
        LeagueOMon minion = new Minion("Emergency Food",1000,100,100,100,1);
        player.addPokemon(minion);
        panel.addPokemonList(player.getLeagueOMon().get(1).getName());
        mouseListener = creator.nextLine(storyText,panel.getText());
        panel.addMouseListener(mouseListener);
    }


    //MODIFY: this
    //EFFECT: Battle the rival
    public void rivalBattle() {
        panel.removeMouseListener(mouseListener);
        panel.removePokemonList();
        panel.addPokemonList(player.getLeagueOMon().get(0).getName());
        panel.addPokemonList(player.getLeagueOMon().get(1).getName());
        Person lent = new Person("Soap");
        LeagueOMon blitz = new BlitzCrank("Blitz",100,30,40,100,1);
        lent.addPokemon(blitz);
        new BattleUi(player,lent,new ImageIcon("./data/Images/road66.png"),panel);

    }



}
