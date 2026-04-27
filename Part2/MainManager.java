package Part2;

import javax.swing.*;
import java.awt.*;

public class MainManager {
    public static void main(String a[]) {
        JFrame frame = new JFrame("Typing Race");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);

        CardLayout cardLayout = new CardLayout();
        JPanel deckPanel = new JPanel(cardLayout);

        SetupRace setupScreen = new SetupRace();

        JPanel raceScreen = new JPanel();
        raceScreen.add(new JLabel("Race Starting..."));

        deckPanel.add(setupScreen, "Setup");
        deckPanel.add(raceScreen, "Race");

        setupScreen.startButton.addActionListener(e -> {
            // get passage from setupRace
            String passage = setupScreen.getPassage();
            System.out.println("Passage: "+passage);

            int passageLength = passage.length();
            System.out.println("Passage length: "+passageLength);

            int noOfRacers = setupScreen.getNoOfRacers();
            System.out.println("Number of racers: "+noOfRacers);

            boolean autocorrect = setupScreen.getAutocorrect();
            System.out.println("Autocorrect: "+autocorrect);

            boolean caffieneMode = setupScreen.getCaffieneMode();
            System.out.println("Caffiene Mode: "+caffieneMode);

            boolean nightShift = setupScreen.getNightShift();
            System.out.println("Night Shift: "+nightShift);



            cardLayout.show(deckPanel, "Race");
        });

        frame.add(deckPanel);
        frame.setVisible(true);
    }
}
