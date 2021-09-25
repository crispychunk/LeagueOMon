package ui;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;


public class MainApp extends JFrame {
    //Setup Window to be played
    // https://stackoverflow.com/questions/34437354/running-code-after-a-button-is-clicked-with-gui
    MainPanel mainPanel;

    //EFFECT: Run the main app
    MainApp() {
        super("Road to Gold");
        try {
            mainPanel = new MainPanel(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        add(mainPanel);
        setSize(800,530);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //Run program
    public static void main(String[] args) throws IOException {

        new MainApp();
    }

}