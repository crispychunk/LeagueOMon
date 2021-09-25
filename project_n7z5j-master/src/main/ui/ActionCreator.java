package ui;

import model.*;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.LinkedList;

public class ActionCreator {
    private StoryGui story2;
    private MainPanel panel;

    public ActionCreator(StoryGui story, MainPanel panel) {
        this.story2 = story;
        this.panel = panel;
    }

    public ActionCreator() {
    }

    public ActionListener startButton2(MainPanel panel) {
        ActionListener test = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                story2.intro();
            }
        };
        return test;
    }

    public ActionListener loadButton(MainPanel panel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JsonReader reader = new JsonReader("./data/PlayerData.json","./data/PlayerDataLeagueOMon.json");
                try {
                    story2.player = reader.jsonReadSave();
                } catch (IOException ioException) {
                    System.out.println("ERROR READING PLAYER DATA");
                }
                panel.setTextVisible(true);
                panel.setCharacter("./data/Images/Characters/professor_oak.png");
                panel.setBackgroundImage("./data/Images/choose_background.png");
                panel.setCharacterVisible(true);
                panel.setButtonVisible(false);
                story2.runSavePoint();
            }
        };
    }

    public MouseListener nextLine(StoryText storyText, JLabel textBox) {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setNextLine(storyText,textBox);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
    }

    public void setNextLine(StoryText storyText, JLabel textBox) {
        System.out.println("Clicked!");
        if (storyText.getText().isEmpty()) {
            story2.player.setSavePoint(storyText.getSavePoint());
            story2.runSavePoint();
            return;
        } else {
            textBox.setText("<html>" + storyText.getText().getFirst() + "<html>");
            storyText.getText().removeFirst();
        }
    }

    public MouseListener getName() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clicked!");
                story2.getName();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }

    public ActionListener chooseYi(Person player) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LeagueOMon starter = new MasterYi("Master Yi", 400, 100, 90, 150, 1);
                player.removeAllPokemon();
                player.addPokemon(starter);
                System.out.println(player.getLeagueOMon());
                story2.outsideLab();
            }
        };
    }

    public ActionListener chooseKench(Person player) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LeagueOMon starter = new TKench("Tahm Kench", 600, 80, 110, 90, 1);
                player.removeAllPokemon();
                player.addPokemon(starter);
                System.out.println(player.getLeagueOMon());
                story2.outsideLab();
            }
        };
    }

    public ActionListener saveAndQuit(MainPanel panel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.story2.player.saveToFile("./data/PlayerData.json","./data/PlayerDataLeagueOMon.json");

            }
        };
    }

    public MouseListener battleIntro(LinkedList<String> text, JLabel textBox,BattleUi battle) {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (text.size() == 0) {
                    battle.battle();
                    return;
                }
                textBox.setText("<html>" + text.getFirst() + "<html>");
                text.removeFirst();

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }

    public ActionListener moveCodeOne(LeagueOMon player, LeagueOMon enemy) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.basicAttack(enemy);
                MoveChooser moveChooser = new MoveChooser();
                int ans = moveChooser.chooseMove(enemy);
                attackMove(enemy,ans,player);
                if (enemy.getHealth() == 0) {
                    System.out.println("You defeated " + enemy.getName());
                }
                if (player.getHealth() == 0) {
                    System.out.println("Your " + player.getName() + " fainted!");
                }


            }
        };
    }

    public ActionListener moveCodeTwo(LeagueOMon player, LeagueOMon enemy) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.secondMove(enemy);
                MoveChooser moveChooser = new MoveChooser();
                int ans = moveChooser.chooseMove(enemy);
                attackMove(enemy,ans,player);
                if (enemy.getHealth() == 0) {
                    System.out.println("You defeated " + enemy.getName());
                }
                if (player.getHealth() == 0) {
                    System.out.println("Your " + player.getName() + " fainted!");
                }
            }
        };
    }

    public ActionListener moveCodeThree(LeagueOMon player, LeagueOMon enemy) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.thirdMove(enemy);
                MoveChooser moveChooser = new MoveChooser();
                int ans = moveChooser.chooseMove(enemy);
                attackMove(enemy,ans,player);
                if (enemy.getHealth() == 0) {
                    System.out.println("You defeated " + enemy.getName());
                }
                if (player.getHealth() == 0) {
                    System.out.println("Your " + player.getName() + " fainted!");
                }

            }
        };
    }

    public ActionListener moveCodeFour(LeagueOMon player, LeagueOMon enemy) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.specialMove(enemy);
                MoveChooser moveChooser = new MoveChooser();
                int ans = moveChooser.chooseMove(enemy);
                attackMove(enemy,ans,player);
                if (enemy.getHealth() == 0) {
                    System.out.println("You defeated " + enemy.getName());
                }
                if (player.getHealth() == 0) {
                    System.out.println("Your " + player.getName() + " fainted!");
                }
            }
        };
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
}
