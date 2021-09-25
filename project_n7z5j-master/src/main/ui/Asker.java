package ui;

import javax.swing.*;

public class Asker {
    ActionCreator creator;
    MainPanel panel;
    JButton enterButton;
    JTextField inputBox;

    public Asker(MainPanel panel, StoryGui story) {

        creator = new ActionCreator(story,panel);
    }

}
