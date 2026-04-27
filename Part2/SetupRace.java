package Part2;

import javax.swing.*;

public class SetupRace extends JPanel {
    public JButton startButton;
    private JRadioButton shortButton;
    private JRadioButton mediumButton;
    private JRadioButton longButton;
    private JRadioButton customButton;
    private JTextField customInput;
    private JSlider noOfRacers;
    private JCheckBox autocorrect;
    private JCheckBox caffieneMode;
    private JCheckBox nightShift;

    public SetupRace() {
        JLabel title = new JLabel("Typing Race Setup");
        shortButton = new JRadioButton("Short (20)");
        mediumButton = new JRadioButton("Medium (40)");
        longButton = new JRadioButton("Long (60)");
        customButton = new JRadioButton("Custom");

        customInput = new JTextField(5);
        customInput.setVisible(false); // not visible yet

        noOfRacers = new JSlider(2, 6, 3);

        startButton = new JButton("Start Race");

        autocorrect = new JCheckBox("Autocorrect");
        caffieneMode = new JCheckBox("Caffiene Mode");
        nightShift = new JCheckBox("Night Shift");


        ButtonGroup passageLength = new ButtonGroup();
        passageLength.add(shortButton);
        passageLength.add(mediumButton);
        passageLength.add(longButton);
        passageLength.add(customButton);

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

        this.add(title);
        this.add(shortButton);
        this.add(mediumButton);
        this.add(longButton);
        this.add(customButton);
        this.add(customInput);
        this.add(noOfRacers);
        this.add(autocorrect);
        this.add(caffieneMode);
        this.add(nightShift);
        this.add(startButton);
    }

    public int getPassageLength() {
        if (shortButton.isSelected()) {
            return 20;
        }
        else if (mediumButton.isSelected()) {
            return 40;
        }
        else if (longButton.isSelected()) {
            return 60;
        }
        else if (customButton.isSelected()) {
            // convert input string to int
            try {
                return Integer.parseInt(customInput.getText());
            }
            catch (NumberFormatException e) { // happens if they typed letters
                return 40;
            }
        }

        return 40; // default if nothing is selected
    }

    public int getNoOfRacers() {
        return noOfRacers.getValue();
    }

    public boolean getAutocorrect() {
        return autocorrect.isSelected();
    }

    public boolean getCaffieneMode() {
        return caffieneMode.isSelected();
    }

    public boolean getNightShift() {
        return nightShift.isSelected();
    }

}
