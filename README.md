# TypingRaceSimulator

Object Oriented Programming Project — ECS414U

## Project Structure

```
TypingRaceSimulator/
├── .git/     # History of version control
├── Part1/    # Textual simulation (Java, command-line)
├── Part2/    # GUI simulation (Java swing)
└── README.md
```

## Part 1 — Textual Simulation

### How to compile

```bash
cd Part1
javac Typist.java TypingRace.java
```

### How to run

The race is started by calling `startRace()` on a `TypingRace` object.
If you have added a `main` method to TypingRace or another class in Part 1, you can then run it by doing:

```bash
java TypingRace
```

## Part 2 — GUI Simulation

The graphical version of the Typing Race Simulator is implemented in Java Swing, featuring race configuration, customisable typists and modifiers, and is started by calling `startRaceGUI()`.

### How to compile

```bash
cd Part2
javac Part2/*.java
```

### How to run

```bash
cd ..
java Part2.MainManager
```

## Dependencies

- Java Development Kit (JDK) 11 or higher
- No external libraries required for Part 1
- Part 2 uses Java Swing (included in standard JDK)

## Notes

- All code should compile and run using standard command-line tools without any IDE-specific configuration.
- The starter code in Part1 was originally written by Ty Posaurus. It contained known issues — finding and fixing them was part of the coursework.
