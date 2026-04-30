package Part2;

import javax.swing.*;
import java.awt.*;

public class SetupRace extends JPanel {
    public JButton typistsButton;

    // text taken from wikipedia (links below)
    private String shortPassage = "In calling the Hubble's spectacular new image of the Eagle Nebula the Pillars of Creation, NASA scientists were tapping a rich symbolic tradition with centuries of meaning, bringing it into the modern age."; // https://en.wikipedia.org/wiki/Pillars_of_Creation taken at 14:34 27/04/2026
    private String mediumPassage = "Angular momentum (sometimes called moment of momentum or rotational momentum) is the rotational analog of linear momentum. It is an important physical quantity because it is a conserved quantity - the total angular momentum of an isolated system remains constant. Angular momentum has both a direction and a magnitude, and both are conserved. Bicycles and motorcycles, flying discs, rifled bullets, and gyroscopes owe their useful properties to conservation of angular momentum."; // https://en.wikipedia.org/wiki/Angular_momentum taken at 14:30 27/04/2026
    private String longPassage = "Thermodynamics is a branch of physics that deals with heat, work, and temperature, and their relation to energy, entropy, and the physical properties of matter and radiation. The behavior of these quantities is governed by the four laws of thermodynamics, which convey a quantitative description using measurable macroscopic physical quantities but may be explained in terms of microscopic constituents by statistical mechanics. Thermodynamics applies to various topics in science and engineering, especially physical chemistry, biochemistry, chemical engineering, and mechanical engineering, as well as other complex fields such as meteorology."; // https://en.wikipedia.org/wiki/Thermodynamics taken at 14:26 27/04/2026

    private JRadioButton shortButton;
    private JRadioButton mediumButton;
    private JRadioButton longButton;
    private JRadioButton customButton;
    private JTextField customInput;
    private JSlider noOfRacers;
    private JCheckBox autocorrect;
    private JCheckBox caffeineMode;
    private JCheckBox nightShift;

    public SetupRace() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Typing Race Setup");
        shortButton = new JRadioButton("Short");
        mediumButton = new JRadioButton("Medium", true);
        longButton = new JRadioButton("Long");
        customButton = new JRadioButton("Custom");

        customInput = new JTextField(5);
        customInput.setVisible(false); // not visible yet

        noOfRacers = new JSlider(2, 6, 3);

        typistsButton = new JButton("Next");

        autocorrect = new JCheckBox("Autocorrect (mistype penalty halved)");
        caffeineMode = new JCheckBox("Caffeine Mode (for the first 10 turns, all typists get a speed boost with an increased burnout risk)");
        nightShift = new JCheckBox("Night Shift (accuracy slightly decreases for everyone)");


        ButtonGroup passage = new ButtonGroup();
        passage.add(shortButton);
        passage.add(mediumButton);
        passage.add(longButton);
        passage.add(customButton);

        noOfRacers.setMajorTickSpacing(1);
        noOfRacers.setPaintTicks(true);
        noOfRacers.setPaintLabels(true);
        noOfRacers.setSnapToTicks(true);


        //event listener for custom:
        customButton.addActionListener(e -> {
            customInput.setVisible(true);
            this.revalidate();
            this.repaint();
        });

        // but if they click another button then hide the input field:
        shortButton.addActionListener(e -> {
            customInput.setVisible(false);
            this.revalidate();
            this.repaint();
        });

        mediumButton.addActionListener(e -> {
            customInput.setVisible(false);
            this.revalidate();
            this.repaint();
        });

        longButton.addActionListener(e -> {
            customInput.setVisible(false);
            this.revalidate();
            this.repaint();
        });

        JPanel header = new JPanel();
        header.add(title);

        JPanel passageSelection = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passageSelection.add(new JLabel("Passage:"));
        passageSelection.add(shortButton);
        passageSelection.add(mediumButton);
        passageSelection.add(longButton);
        passageSelection.add(customButton);
        passageSelection.add(customInput);

        JPanel racerSeats = new JPanel(new FlowLayout(FlowLayout.LEFT));
        racerSeats.add(new JLabel("Number of Racers:"));
        racerSeats.add(noOfRacers);

        JPanel modifiersSection = new JPanel(new FlowLayout(FlowLayout.LEFT));
        modifiersSection.add(new JLabel("Global Difficulty Modifiers:"));
        modifiersSection.add(autocorrect);
        modifiersSection.add(caffeineMode);
        modifiersSection.add(nightShift);

        
        JPanel typistsButtonPanel = new JPanel();
        typistsButtonPanel.add(typistsButton);

        this.add(header);
        this.add(passageSelection);
        this.add(racerSeats);
        this.add(modifiersSection);
        this.add(typistsButtonPanel);
    }

    public String getPassage() {
        if (shortButton.isSelected()) {
            return shortPassage;
        }
        else if (mediumButton.isSelected()) {
            return mediumPassage;
        }
        else if (longButton.isSelected()) {
            return longPassage;
        }
        else if (customButton.isSelected()) {
            return customInput.getText();
        }

        return mediumPassage; // default if nothing is selected
    }

    public int getNoOfRacers() {
        return noOfRacers.getValue();
    }

    public boolean getAutocorrect() {
        return autocorrect.isSelected();
    }

    public boolean getcaffeineMode() {
        return caffeineMode.isSelected();
    }

    public boolean getNightShift() {
        return nightShift.isSelected();
    }


}
