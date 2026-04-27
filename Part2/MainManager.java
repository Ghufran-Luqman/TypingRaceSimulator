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

        setupScreen.typistsButton.addActionListener(e -> {
            int noOfRacers = setupScreen.getNoOfRacers();
            SetupTypists typistScrn = new SetupTypists(noOfRacers);

            deckPanel.add(typistScrn, "Setup Typists");

            typistScrn.startRace.addActionListener(startEvent -> {
                //grab data etc.

                cardLayout.show(deckPanel, "Race");
            });

            cardLayout.show(deckPanel, "Setup Typists");
            
        });

        frame.add(deckPanel);
        frame.setVisible(true);
    }
}
