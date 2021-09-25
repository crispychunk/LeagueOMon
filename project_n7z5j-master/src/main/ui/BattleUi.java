package ui;

import model.LeagueOMon;
import model.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class BattleUi {
    private ImageIcon backgroundImage;
    private ImageIcon playerLeagueOMonImage;
    private ImageIcon enemyLeagueOMonImage;
    private LeagueOMon playerLeagueOMon;
    private LeagueOMon enemyLeagueOMon;
    private Person player;
    private Person enemy;
    private JButton firstMove;
    private JButton secondMove;
    private JButton thirdMove;
    private JButton fourthMove;
    private MainPanel mainPanel;
    private JButton changePokemon;
    JLabel playerBox;
    JLabel enemyBox;
    MouseListener mouseListener;
    ActionCreator creator;


    //Create a battle GUI
    public BattleUi(Person player, Person enemy, ImageIcon backgroundImage, MainPanel panel) {
        this.mainPanel = panel;
        this.player = player;
        this.enemy = enemy;
        playerBox = new JLabel();
        enemyBox = new JLabel();
        this.playerLeagueOMon = player.getLeagueOMon().get(0);
        this.enemyLeagueOMon = enemy.getLeagueOMon().get(0);
        this.creator = new ActionCreator();
        this.backgroundImage = backgroundImage;
        firstMove = new JButton("firstMove");
        secondMove = new JButton("secondMove");
        thirdMove = new JButton("ThirdMove");
        fourthMove = new JButton("fourthMove");
        changePokemon = new JButton("Change");
        setup();
    }

    //EFFECT: run Battle UI setup
    public void setup() {
        mainPanel.setBackgroundImage("./data/Images/battle_background.png");
        mainPanel.setCharacterVisible(false);
        firstMove.setBounds(350,400,100,40);
        secondMove.setBounds(450,400,100,40);
        thirdMove.setBounds(350,440,100,40);
        fourthMove.setBounds(450,440,100,40);
        changePokemon.setBounds(120,10,80,20);
        intro();
    }

    //EFFECT: run the introduction dialogue
    public void intro() {
        //mainPanel.playClip("./data/battle1.wav");
        LinkedList<String> text = new LinkedList<>();
        text.add("You are fighting " + enemy.getName());
        text.add(enemy.getName() + " sends out " + enemyLeagueOMon.getName());
        text.add("You send out " + playerLeagueOMon.getName());
        mouseListener = creator.battleIntro(text,mainPanel.getText(),this);
        mainPanel.addMouseListener(mouseListener);
    }

    //EFFECT: Create the battle Gui
    public void battle() {
        mainPanel.add(firstMove);
        mainPanel.add(secondMove);
        mainPanel.add(thirdMove);
        mainPanel.add(fourthMove);
        mainPanel.add(changePokemon);
        playerLeagueOMonImage = new ImageIcon(playerLeagueOMon.getImage());
        enemyLeagueOMonImage = new ImageIcon(enemyLeagueOMon.getImage());
        mainPanel.setPlayerBox(playerLeagueOMonImage);
        mainPanel.setEnemyBox(enemyLeagueOMonImage);
        mainPanel.setTextVisible(false);
        battleSetup();
        changePokemonSetup();
    }

    //EFFECT: Change Player LeagueOMon setup
    public void changePokemonSetup() {
        changePokemon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String) mainPanel.pokemonList.getSelectedItem();
                for (LeagueOMon l : player.getLeagueOMon()) {
                    if (name.equals(l.getName())) {
                        changePokemon(l);
                    }
                }
            }
        });
    }

    //EFFECT: Change Player LeagueOMon on the GUI
    public void changePokemon(LeagueOMon pokemon) {
        playerLeagueOMon = pokemon;
        playerLeagueOMonImage = new ImageIcon(playerLeagueOMon.getImage());
        mainPanel.setPlayerBox(playerLeagueOMonImage);
        firstMove.removeAll();
        secondMove.removeAll();
        thirdMove.removeAll();
        fourthMove.removeAll();
        firstMove.setText(playerLeagueOMon.getMove(0));
        secondMove.setText(playerLeagueOMon.getMove(1));
        thirdMove.setText(playerLeagueOMon.getMove(2));
        fourthMove.setText(playerLeagueOMon.getMove(3));
    }

    //EFFECT: BattleSETUP V2
    public void battleSetup() {
        mainPanel.removeMouseListener(mouseListener);
        firstMove.setText(playerLeagueOMon.getMove(0));
        secondMove.setText(playerLeagueOMon.getMove(1));
        thirdMove.setText(playerLeagueOMon.getMove(2));
        fourthMove.setText(playerLeagueOMon.getMove(3));
        firstMove.addActionListener(creator.moveCodeOne(playerLeagueOMon,enemyLeagueOMon));
        secondMove.addActionListener(creator.moveCodeTwo(playerLeagueOMon,enemyLeagueOMon));
        thirdMove.addActionListener(creator.moveCodeThree(playerLeagueOMon,enemyLeagueOMon));
        fourthMove.addActionListener(creator.moveCodeFour(playerLeagueOMon,enemyLeagueOMon));
    }


}
