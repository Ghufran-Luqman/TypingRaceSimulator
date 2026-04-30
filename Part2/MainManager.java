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
            String passage = setupScreen.getPassage();
            boolean autocorrect = setupScreen.getAutocorrect();
            boolean caffeine = setupScreen.getcaffeineMode();
            boolean nightShift = setupScreen.getNightShift();
            SetupTypists setupTypists = new SetupTypists(noOfRacers);

            deckPanel.add(setupTypists, "Setup Typists");

            setupTypists.startRace.addActionListener(startEvent -> {
                //grab data etc.
                TypingRace race = new TypingRace(passage.length(), noOfRacers);

                if (autocorrect) {
                        race.setSlideBackAmount(race.getSlideBackAmount()/2);
                }


                for (int i=1;i<=noOfRacers;i++) {
                    String typingStyle = setupTypists.getTypingStyle(i);
                    String keyboardType = setupTypists.getKeyboardType(i);
                    String symbol = setupTypists.getSymbol(i);
                    String name = setupTypists.getName(i);
                    double accuracy = 0.75;
                    boolean wristSupport = setupTypists.getWristSupport(i);
                    boolean energyDrink = setupTypists.getEnergyDrink(i);
                    boolean headphones = setupTypists.getHeadphones(i);

                    Typist typist = new Typist(symbol.charAt(0), name, accuracy);

                    //typing styles
                    if (typingStyle.equals("Touch Typist")) {
                        typist.setAccuracy(0.9);
                    }
                    else if (typingStyle.equals("Hunt & Peck")) {
                        typist.setAccuracy(0.7);
                    }
                    else if (typingStyle.equals("Phone Thumbs")) {
                        typist.setAccuracy(0.8);
                    }
                    else if (typingStyle.equals("Voice-to-Text")) {
                        typist.setAccuracy(0.93);
                    }

                    // global modifiers
                    if (caffeine) {
                        typist.setBurnoutRisk(0.2);
                    }
                    if (nightShift) {
                        typist.setAccuracy(typist.getAccuracy() - 0.05);
                    }


                    //keyboard type
                    // "Mechanical", "Membrane", "Touchscreen", "Stenography"
                    if (keyboardType.equals("Mechanical")) {
                        typist.setAccuracy(typist.getAccuracy() + 0.05);
                    }
                    //membrane should be default
                    else if (keyboardType.equals("Touchscreen")) {
                        typist.setAccuracy(typist.getAccuracy() - 0.05);
                    }
                    else if (keyboardType.equals("Stenography")) {
                        typist.setAccuracy(typist.getAccuracy() + 0.1);
                        typist.setBurnoutRisk(0.15);
                    }

                    if (wristSupport) {
                        typist.setBurnoutDurationModifier(1);
                    }
                    if (energyDrink) {
                        typist.setHasEnergyDrink(energyDrink);
                    }
                    if (headphones) {
                        typist.setAccuracy(typist.getAccuracy() + 0.05);
                    }

                        
                    // add typist
                    race.addTypist(typist, i);
                }

                cardLayout.show(deckPanel, "Race");

                new Thread(() -> {
                    race.startRace(caffeine);
                }).start();
            });

            cardLayout.show(deckPanel, "Setup Typists");
            
        });

        frame.add(deckPanel);
        frame.setVisible(true);
    }
}
