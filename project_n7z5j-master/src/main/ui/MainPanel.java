package ui;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainPanel extends JPanel {
    private ImageIcon backgroundImage;
    private ImageIcon character;
    private JLabel text;
    JLabel playerBox;
    JLabel enemyBox;
    private ActionCreator actionCreator;
    public StoryGui story2;
    private boolean charVisible = false;
    JButton startButton;
    JButton option1;
    JButton option2;
    JButton loadButton;
    JButton saveButton;
    JFrame screen;
    Clip clip;
    public JTextField inputField;
    AudioInputStream audio; //https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
    JComboBox pokemonList;


    //EFFECT: Make the main panel where everything is going to happen
    public MainPanel(JFrame screen) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        this.screen = screen;
        /* This is removed because audio file was too large to be put into git
        audio = AudioSystem.getAudioInputStream(new File("./data/intro_music.wav"));
        clip = AudioSystem.getClip();
        clip.open(audio);
        clip.start();
         */
        pokemonList = new JComboBox();
        pokemonList.setBounds(10,10,100,20);
        this.add(pokemonList);
        playerBox = new JLabel();
        enemyBox = new JLabel();
        this.saveButton = new JButton("Save & Quit");
        this.inputField = new JTextField();
        inputField.setBounds(360,460,100,40);
        this.character = new ImageIcon();
        this.story2 = new StoryGui(this);
        actionCreator = new ActionCreator(story2, this);
        this.setLayout(null);
        setup();
    }

    //EFFECT: Continuation of mainPanel setup
    public void setup() {
        JLabel pokemon = new JLabel("Tkench");
        this.text = new JLabel();
        this.backgroundImage = new ImageIcon("./data/Images/start_background.png");
        startButton = new JButton("New");
        loadButton = new JButton("load");
        saveButton.setBounds(30,380,100,40);
        startButton.addActionListener(actionCreator.startButton2(this));
        startButton.setBounds(350,350,100,40);
        loadButton.setBounds(350,400,100,40);
        setupTwo();

    }

    //EFFECT: Continuation of mainPanel setup
    public void setupTwo() {
        text = new JLabel("...",SwingConstants.CENTER);
        text.setFont(new Font("Verdana",1,15));
        text.setBounds(200,400,400,100);
        playerBox.setBounds(10,180,400,400);
        enemyBox.setBounds(500,50,340,300);
        text.setOpaque(true);
        text.setBackground(new Color(200, 162, 231));
        text.setVisible(false);
        this.saveButton.addActionListener(actionCreator.saveAndQuit(this));
        this.loadButton.addActionListener(actionCreator.loadButton(this));
        this.add(text);
        this.add(startButton);
        this.add(loadButton);
        this.add(saveButton);
        this.add(playerBox);
        this.add(enemyBox);
    }


    @Override
    //EFFECT: Paint background image as well as the character that will be talking in the game
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(),0,0,null);
        if (charVisible) {
            g.drawImage(character.getImage(), 60, 0, null);
        }
    }

    //MODIFY: this
    //EFFECT: set start and load button visibility
    public void setButtonVisible(boolean b) {
        this.startButton.setVisible(b);
        this.loadButton.setVisible(b);
    }

    //MODIFY: this
    //EFFECT: set background
    public void setBackgroundImage(String directory) {
        this.backgroundImage = new ImageIcon(directory);
        repaint();
    }

    //EFFECT: set character thats being displayed
    public void setCharacter(String directory) {
        this.character = new ImageIcon(directory);
        repaint();
    }

    //MODIFY: this
    //EFFECT: Set text of JLabel text to parameter text
    public void setText(String text) {
        this.text.setText(text);
    }

    //MODIFY: this
    //EFFECT: set text visibility
    public void setTextVisible(Boolean bool) {
        this.text.setVisible(bool);
    }

    //MODIFY: this
    //EFFECT: set character visiblity
    public void setCharacterVisible(boolean b) {
        this.charVisible = b;
    }

    //MODIFY: this
    //EFFECT: remove all component in the panel
    public void removeComponent() {
        this.removeAll();
    }

    //MODIFY: this
    //EFFECT: get text from Jlabel text
    public JLabel getText() {
        return this.text;
    }

    //MODIFY: this
    //EFFECT: Set player image to icon
    public void setPlayerBox(ImageIcon icon) {
        playerBox.setIcon(icon);
    }

    //MODIFY: this
    //EFFECT: Set enemy image to icon
    public void setEnemyBox(ImageIcon icon) {
        enemyBox.setIcon(icon);
    }

    //MODIFY: this
    //EFFECT: play music from the directory
    public void playClip(String directory) {
        clip.stop();
        try {
            audio = AudioSystem.getAudioInputStream(new File(directory));
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            clip.open(audio);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clip.start();
    }

    //MODIFY: this
    //EFFECT: add pokemon to the Pokemonlist to be displayed
    public void addPokemonList(String name) {
        pokemonList.addItem(name);
    }


    //MODIFY: this
    //EFFECT: Remove all pokemon in pokemonlist
    public void removePokemonList() {
        pokemonList.removeAllItems();
    }
}
