package Part2;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class SetupTypists extends JPanel {
    public JButton startRace;
    private JTabbedPane tabbedPane;
    private HashMap<Integer, String> racerColoursMap = new HashMap<>();
    private HashMap<Integer, JComboBox<String>> racerTypingStylesMap = new HashMap<>();
    private HashMap<Integer, JComboBox<String>> racerKeyboardTypesMap = new HashMap<>();
    private HashMap<Integer, JTextField> racerSymbolsMap = new HashMap<>();
    private HashMap<Integer, JTextField> racerNameMap = new HashMap<>();
    private HashMap<Integer, JCheckBox> wristSupportMap = new HashMap<>();
    private HashMap<Integer, JCheckBox> energyDrinkMap = new HashMap<>();
    private HashMap<Integer, JCheckBox> headphonesMap = new HashMap<>();


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

        // typing style
        JPanel typingStylePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typingStylePanel.add(new JLabel("Typing Style: "));
        String[] typingStyles = {"Touch Typist", "Hunt & Peck", "Phone Thumbs", "Voice-to-Text"};
        JComboBox<String> typingStyleBox = new JComboBox<>(typingStyles);
        racerTypingStylesMap.put(currentRacer, typingStyleBox);
        typingStylePanel.add(typingStyleBox);
        form.add(typingStylePanel);

        // keyboard type
        JPanel keyboardTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        keyboardTypePanel.add(new JLabel("Keyboard Type:"));
        String[] keyboardTypes = {"Mechanical", "Membrane", "Touchscreen", "Stenography"};
        JComboBox<String> keyboardTypeBox = new JComboBox<>(keyboardTypes);
        racerKeyboardTypesMap.put(currentRacer, keyboardTypeBox);
        keyboardTypePanel.add(keyboardTypeBox);
        form.add(keyboardTypePanel);

        // symbol
        JPanel symbolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        symbolPanel.add(new JLabel("Symbol: "));
        JTextField symbolField = new JTextField(5);
        racerSymbolsMap.put(currentRacer, symbolField);
        symbolPanel.add(symbolField);
        form.add(symbolPanel);

        // name
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(new JLabel("Name: "));
        JTextField nameField = new JTextField(5);
        racerNameMap.put(currentRacer, nameField);
        namePanel.add(nameField);
        form.add(namePanel);

        // colour
        JLabel chosenColourLabel = new JLabel("No colour chosen");
        JPanel colourPickerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton pickColourButton = new JButton("Choose colour");
        colourPickerPanel.add(pickColourButton);
        colourPickerPanel.add(chosenColourLabel);
        pickColourButton.addActionListener(e -> {
            Color racerColour = JColorChooser.showDialog(this, "Choose Colour", Color.WHITE);
            if (racerColour != null) {
                String hex = String.format("#%02X%02X%02X", racerColour.getRed(), racerColour.getGreen(), racerColour.getBlue());
                racerColoursMap.put(currentRacer, hex);
                chosenColourLabel.setText("Chosen colour: "+racerColoursMap.get(currentRacer));
            }
        });
        form.add(colourPickerPanel);

        // accessories
        JPanel accessoriesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        accessoriesPanel.add(new JLabel("Accessories: "));
        JCheckBox wristSupport = new JCheckBox("Wrist Support");
        JCheckBox energyDrink = new JCheckBox("Energy Drink");
        JCheckBox headphones = new JCheckBox("Noise-Cancelling Headphones");
        wristSupportMap.put(currentRacer, wristSupport);
        energyDrinkMap.put(currentRacer, energyDrink);
        headphonesMap.put(currentRacer, headphones);
        accessoriesPanel.add(wristSupport);
        accessoriesPanel.add(energyDrink);
        accessoriesPanel.add(headphones);
        
        
        return form;
    }

    public String getTypingStyle(int racer) {
        return (String) racerTypingStylesMap.get(racer).getSelectedItem();
    }

    public String getKeyboardType(int racer) {
        return (String) racerKeyboardTypesMap.get(racer).getSelectedItem();
    }

    public String getSymbol(int racer) {
        String symbol = racerSymbolsMap.get(racer).getText();
        if (symbol.isEmpty()) {
            return "⌨";
        }
        return symbol;
    }

    public String getName(int racer) {
        String name = racerNameMap.get(racer).getText();
        if (name.isEmpty()) {
            return "Racer "+(Integer.toString(racer));
        }
        return name;
    }

    public String getColour(int racer) {
        return racerColoursMap.getOrDefault(racer, "#000000");
    }

    public boolean getWristSupport(int racer) {
        return wristSupportMap.get(racer).isSelected();
    }

    public boolean getEnergyDrink(int racer) {
        return energyDrinkMap.get(racer).isSelected();
    }

    public boolean getHeadphones(int racer) {
        return headphonesMap.get(racer).isSelected();
    }

}
