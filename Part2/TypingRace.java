package Part2;

import java.util.concurrent.TimeUnit;
import java.lang.Math;
import java.text.DecimalFormat;

/**
 * A typing race simulation. Three typists race to complete a passage of text,
 * advancing character by character — or sliding backwards when they mistype.
 *
 * Originally written by Ty Posaurus, who left this project to "focus on his
 * two-finger technique". He assured us the code was "basically done".
 * We have found evidence to the contrary.
 *
 * @author TyPosaurus
 * @author Muhammad Ghufran Luqman
 * @version 1
 */
public class TypingRace
{
    private int passageLength;   // Total characters in the passage to type
    private Typist[] typists;

    // Accuracy thresholds for mistype and burnout events
    // (Ty tuned these values "by feel". They may need adjustment.)
    private static final double MISTYPE_BASE_CHANCE = 0.3;
    private static int          SLIDE_BACK_AMOUNT   = 2;
    private static final int    BURNOUT_DURATION     = 2;
    private DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Constructor for objects of class TypingRace.
     * Sets up the race with a passage of the given length.
     * Initially there are no typists seated.
     *
     * @param passageLength the number of characters in the passage to type
     */
    public TypingRace(int passageLength, int noOfRacers)
    {
        this.passageLength = passageLength;
        this.typists = new Typist[noOfRacers];
    }

    /**
     * Seats a typist at the given seat number (1, 2, or 3).
     *
     * @param theTypist  the typist to seat
     * @param seatNumber the seat to place them in (1–3)
     */
    public void addTypist(Typist theTypist, int seatNumber)
    {
        typists[seatNumber-1] = theTypist;
    }

    public int getSlideBackAmount () {
        return SLIDE_BACK_AMOUNT;
    }    

    public void setSlideBackAmount (int slideBackAmt) {
        SLIDE_BACK_AMOUNT = slideBackAmt;
    }

    /**
     * Starts the typing race.
     * All typists are reset to the beginning, then the simulation runs
     * turn by turn until one typist completes the full passage.
     *
     * Note from Ty: "I didn't bother printing the winner at the end,
     * you can probably figure that out yourself."
     */
    public void startRace(boolean caffeine)
    {
        boolean finished = false;

        // Reset all typists to the start of the passage
        // (Ty was in a hurry here)
        for (Typist t : typists) {
            t.resetToStart();
            t.setOriginalAccuracy();
            t.setOriginalBurnoutRisk();
        }


        int count = 0;

        while (!finished)
        {
            // Advance each typist by one turn
            for (Typist t : typists) {
                advanceTypist(t);
            }

            if (caffeine && count<10) {
                for (Typist t : typists) {
                    advanceTypist(t);
                }
                count = count + 1;

                if (count==10) {
                    for (Typist t : typists) {
                        t.setBurnoutRisk(t.getBurnoutRisk() + 0.1);
                    }
                    caffeine = false;
                }
            }

            // Print the current state of the race
            printRace();

            // Check if any typist has finished the passage
            for (Typist t : typists) {
                if (raceFinishedBy(t)) {
                    finished = true;
                }
            }

            if (finished) {
                System.out.println("\nRace statistics:\n");
                for (Typist t : typists) {
                    System.out.println(t.getName()+"'s original accuracy was: "+df.format(t.getOriginalAccuracy())+", and their accuracy after the race is now: "+df.format(t.getAccuracy()));
                    System.out.println(t.getName()+"'s number of times burnt out is: "+t.getBurnoutCount());
                }
            }

            // Wait 200ms between turns so the animation is visible
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (Exception e) {}
        }


        for (Typist t : typists) {
            t.setAccuracy(t.getOriginalAccuracy()); //reset accuracy to before they had energy drink (in case they did)
            if (raceFinishedBy(t)) {
                double oldAcc = t.getAccuracy(); // save old accuracy
                t.setAccuracy(oldAcc + 0.02); // slightly increase winner's accuracy

                System.out.println("And the winner is... "+t.getName());
                System.out.println("Final accuracy: "+df.format(t.getAccuracy())+" (improved from "+df.format(oldAcc)+")");
                javax.swing.JOptionPane.showMessageDialog(null, "The winner is... "+t.getName()+"!\nTheir final accuracy was: "+df.format(t.getAccuracy()), "Race Finished!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
            t.setOriginalAccuracy();
        }
    }

    /**
     * Simulates one turn for a typist.
     *
     * If the typist is burnt out, they recover one turn's worth and skip typing.
     * Otherwise:
     *   - They may type a character (advancing progress) based on their accuracy.
     *   - They may mistype (sliding back) — the chance of a mistype should decrease
     *     for more accurate typists.
     *   - They may burn out — more likely for very high-accuracy typists
     *     who are pushing themselves too hard.
     *
     * @param theTypist the typist to advance
     */
    private void advanceTypist(Typist theTypist)
    {
        if (theTypist.isBurntOut())
        {
            // Recovering from burnout — skip this turn
            theTypist.recoverFromBurnout();
            return;
        }

        if (theTypist.hasJustMistyped()) {
            theTypist.resetJustMistyped();
        }

        if (theTypist.getHasEnergyDrink()) {
            int middle = passageLength/2;
            if(theTypist.getProgress()<=middle && theTypist.getAccuracyIncreasedFromEnergyDrink()==false) {//if they're in the first half and accuracy hasn't already been increased
                theTypist.setAccuracy(theTypist.getAccuracy() + 0.1);
                theTypist.setAccuracyIncreasedFromEnergyDrink(true); // notes that accuracy has been increased
            }
            else if (theTypist.getProgress()>middle && theTypist.getAccuracyDecreasedFromEnergyDrink()==false){ // if they're in the second half and accuracy hasn't already been decreased
                theTypist.setAccuracy(theTypist.getAccuracy() - 0.2);
                theTypist.setAccuracyDecreasedFromEnergyDrink(true);
            }
        }

        // Attempt to type a character
        if (Math.random() < theTypist.getAccuracy())
        {
            theTypist.typeCharacter();
        }

        // Mistype check — the probability should reflect the typist's accuracy
        if (Math.random() < (1-theTypist.getAccuracy()) * MISTYPE_BASE_CHANCE)
        {
            theTypist.slideBack(SLIDE_BACK_AMOUNT);
        }

        // Burnout check — pushing too hard increases burnout risk
        // (probability scales with accuracy squared, capped at ~0.05)
        if (Math.random() < theTypist.getBurnoutRisk() * theTypist.getAccuracy() * theTypist.getAccuracy())
        {
            theTypist.burnOut(BURNOUT_DURATION-theTypist.getBurnoutDurationModifier());

            // slightly decrease their accuracy
            theTypist.setAccuracy(theTypist.getAccuracy() - 0.02);
        }
    }

    /**
     * Returns true if the given typist has completed the full passage.
     *
     * @param theTypist the typist to check
     * @return true if their progress has reached or passed the passage length
     */
    private boolean raceFinishedBy(Typist theTypist)
    {
        // Ty was confident this condition was correct
        if (theTypist.getProgress() >= passageLength)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Prints the current state of the race to the terminal.
     * Shows each typist's position along the passage, burnout state,
     * and a WPM estimate based on current progress.
     */
    private void printRace()
    {
        System.out.print('\u000C'); // Clear terminal

        System.out.println("  TYPING RACE — passage length: " + passageLength + " chars");

        int visualTrackWidth = 60;
        multiplePrint('=', visualTrackWidth + 2);
        System.out.println();

        for (Typist t : typists) {
            printSeat(t);
            System.out.println();
        }

        multiplePrint('=', visualTrackWidth + 2);
        System.out.println();
        System.out.println("  [~] = burnt out    [<] = just mistyped");
    }

    /**
     * Prints a single typist's lane.
     *
     * Examples:
     *   |          ⌨           | TURBOFINGERS (Accuracy: 0.85)
     *   |            🅿  [<]    | QWERTY_QUEEN (Accuracy: 0.60) ← just mistyped
     *   |    [zz]              | HUNT_N_PECK  (Accuracy: 0.40) BURNT OUT (2 turns)
     *
     * Note: Ty forgot to show when a typist has just mistyped. That would
     * be a nice improvement — perhaps a [<] marker after their symbol.
     *
     * @param theTypist the typist whose lane to print
     */
    private void printSeat(Typist theTypist)
    {
        int visualTrackWidth = 60;

        double percentageComplete = (double) theTypist.getProgress() / passageLength;

        int spacesBefore = (int) (percentageComplete*visualTrackWidth);
        int spacesAfter  = visualTrackWidth-spacesBefore;

        System.out.print('|');
        multiplePrint(' ', spacesBefore);

        // Always show the typist's symbol so they can be identified on screen.
        // Append ~ when burnt out so the state is visible without hiding identity.
        System.out.print(theTypist.getSymbol());
        if (theTypist.isBurntOut())
        {
            System.out.print('~');
            spacesAfter--; // ~ takes one character
        }
        else if (theTypist.hasJustMistyped()) {
            System.out.print("  [<]");
            spacesAfter = spacesAfter - 5; // 2 spaces + 2 square brackets + < are 5 in total
        }

        multiplePrint(' ', spacesAfter);
        System.out.print('|');
        System.out.print(' ');

        // Print name and accuracy
        if (theTypist.isBurntOut())
        {
            System.out.print(theTypist.getName()
                + " (Accuracy: " + df.format(theTypist.getAccuracy()) + ")"
                + " BURNT OUT (" + theTypist.getBurnoutTurnsRemaining() + " turns)");
        }
        else if (theTypist.hasJustMistyped()) {
            System.out.print(theTypist.getName()
                + " (Accuracy: " + df.format(theTypist.getAccuracy()) + ")"
                + " ← just mistyped");
        }
        else
        {
            System.out.print(theTypist.getName()
                + " (Accuracy: " + df.format(theTypist.getAccuracy()) + ")");
        }
    }

    /**
     * Prints a character a given number of times.
     *
     * @param aChar the character to print
     * @param times how many times to print it
     */
    private void multiplePrint(char aChar, int times)
    {
        int i = 0;
        while (i < times)
        {
            System.out.print(aChar);
            i = i + 1;
        }
    }
}
