package Part2;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class SetupTypists extends JPanel {
    public JButton startRace;
    private JTabbedPane tabbedPane;
    private HashMap<Integer, String> racerColours = new HashMap<>();
    private HashMap<Integer, JComboBox<String>> racerTypingStyles = new HashMap<>();
    private HashMap<Integer, JComboBox<String>> racerKeyboardTypes = new HashMap<>();
    private HashMap<Integer, JTextField> racerSymbols = new HashMap<>();


    public SetupTypists(int noOfRacers) {
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("Customise Typists", SwingConstants.CENTER);
        this.add(title, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane();


        for (int i=1;i<=noOfRacers;i++) {
            JPanel racerPanel = createRacer(noOfRacers, i);
            tabbedPane.addTab("Racer "+i, racerPanel);
        }
        this.add(tabbedPane, BorderLayout.CENTER);

        JPanel footer = new JPanel();
        startRace = new JButton("Start Race");
        footer.add(startRace);
        this.add(footer, BorderLayout.SOUTH);
    }

    private JPanel createRacer(int noOfRacers, int currentRacer) {
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        JPanel typingStylePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typingStylePanel.add(new JLabel("Typing Style: "));
        String[] typingStyles = {"Touch Typist", "Hunt & Peck", "Phone Thumbs", "Voice-to-Text"};
        JComboBox<String> typingStyleBox = new JComboBox<>(typingStyles);
        racerTypingStyles.put(currentRacer, typingStyleBox);
        typingStylePanel.add(new JComboBox<>(typingStyles));
        form.add(typingStylePanel);

        JPanel keyboardTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        keyboardTypePanel.add(new JLabel("Keyboard Type:"));
        String[] keyboardTypes = {"Mechanical", "Membrane", "Touchscreen", "Stenography"};
        JComboBox<String> keyboardTypeBox = new JComboBox<>(keyboardTypes);
        racerKeyboardTypes.put(currentRacer, keyboardTypeBox);
        keyboardTypePanel.add(new JComboBox<>(keyboardTypes));
        form.add(keyboardTypePanel);

        JPanel symbolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        symbolPanel.add(new JLabel("Symbol: "));
        JTextField symbolField = new JTextField(5);
        racerSymbols.put(currentRacer, symbolField);
        symbolPanel.add(symbolField);
        form.add(symbolPanel);


        JLabel chosenColourLabel = new JLabel("No colour chosen");
        JPanel colourPickerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton pickColourButton = new JButton("Choose colour");
        colourPickerPanel.add(pickColourButton);
        colourPickerPanel.add(chosenColourLabel);
        pickColourButton.addActionListener(e -> {
            Color racerColour = JColorChooser.showDialog(this, "Choose Colour", Color.WHITE);
            if (racerColour != null) {
                String hex = String.format("#%02X%02X%02X", racerColour.getRed(), racerColour.getGreen(), racerColour.getBlue());
                racerColours.put(currentRacer, hex);
                chosenColourLabel.setText("Chosen colour: "+racerColours.get(currentRacer));
            }
        });
        form.add(colourPickerPanel);

        
        
        return form;
    }

    public String getTypingStyle(int racer) {
        return (String) racerTypingStyles.get(racer).getSelectedItem();
    }

    public String getKeyboardType(int racer) {
        return (String) racerKeyboardTypes.get(racer).getSelectedItem();
    }

    public String getSymbol(int racer) {
        return racerSymbols.get(racer).getText();
    }

    public String getColour(int racer) {
        return racerColours.getOrDefault(racer, "#000000");
    }
}
