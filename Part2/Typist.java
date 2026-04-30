package Part2;

/**
 * Write a description of class Typist here.
 *
 * Starter code generously abandoned by Ty Posaurus, your predecessor,
 * who typed with two fingers and considered that "good enough".
 * He left a sticky note: "the slide-back thing is optional probably".
 * It is not optional. Good luck.
 *
 * @author [name here]
 * @version 2, started on 28/03/2026
 */
public class Typist
{
    // Fields of class Typist
    // Hint: you will need six fields. Think carefully about their types.
    // One of them tracks how far along the passage the typist has reached.
    // Another tracks whether the typist is currently burnt out.
    // A third tracks HOW MANY turns of burnout remain (not just whether they are burnt out).
    // The remaining three should be fairly obvious.

    private String name;
    private char symbol;
    private int progress;
    private boolean burntOut;
    private int turnsRemaining;
    private double accuracy;
    private boolean justMistyped;
    private double burnoutRisk = 0.05;
    private boolean hasEnergyDrink = false;
    private boolean accuracyIncreasedFromEnergyDrink = false;
    private boolean accuracyDecreasedFromEnergyDrink = false;
    private int burnoutDurationModifier = 0;
    private int burnoutCount = 0;
    private double originalAccuracy;
    private int correctCharsTyped = 0;
    private int totalCharsTyped = 0;
    private double originalBurnoutRisk;




    // Constructor of class Typist
    /**
     * Constructor for objects of class Typist.
     * Creates a new typist with a given symbol, name, and accuracy rating.
     *
     * @param typistSymbol  a single Unicode character representing this typist (e.g. '①', '②', '③')
     * @param typistName    the name of the typist (e.g. "TURBOFINGERS")
     * @param typistAccuracy the typist's accuracy rating, between 0.0 and 1.0
     */
    public Typist(char typistSymbol, String typistName, double typistAccuracy)
    {
        this.symbol = typistSymbol;
        this.name = typistName;
        this.accuracy = typistAccuracy;
    }


    // Methods of class Typist

    /**
     * Sets this typist into a burnout state for a given number of turns.
     * A burnt-out typist cannot type until their burnout has worn off.
     *
     * @param turns the number of turns the burnout will last
     */
    public void burnOut(int turns)
    {
        this.burntOut = true;
        this.turnsRemaining = turns;
        this.burnoutCount++;
    }

    /**
     * Reduces the remaining burnout counter by one turn.
     * When the counter reaches zero, the typist recovers automatically.
     * Has no effect if the typist is not currently burnt out.
     */
    public void recoverFromBurnout()
    {
        if (burntOut) {
            this.turnsRemaining = (this.turnsRemaining) - 1;
            if (this.turnsRemaining <= 0) {
                this.burntOut = false;
            }
        }
    }

    /**
     * Returns the typist's accuracy rating.
     *
     * @return accuracy as a double between 0.0 and 1.0
     */
    public double getAccuracy()
    {
        return this.accuracy;
    }

    /**
     * Returns the typist's current progress through the passage.
     * Progress is measured in characters typed correctly so far.
     * Note: this value can decrease if the typist mistypes.
     *
     * @return progress as a non-negative integer
     */
    public int getProgress()
    {
        return this.progress;
    }

    /**
     * Returns the name of the typist.
     *
     * @return the typist's name as a String
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Returns the character symbol used to represent this typist.
     *
     * @return the typist's symbol as a char
     */
    public char getSymbol()
    {
        return this.symbol;
    }

    /**
     * Returns whether the typist has an energy drink enabled
     * 
     * @return status of energy drink as boolean
     */

    public boolean getHasEnergyDrink() {
        return this.hasEnergyDrink;
    }

    /**
     * Returns if the accuracy was increased from the energy drink already
     * 
     * @return status as a boolean
     */
    public boolean getAccuracyIncreasedFromEnergyDrink() {
        return this.accuracyIncreasedFromEnergyDrink;
    }

    /**
     * Returns if the accuracy was decreased from the energy drink already
     * 
     * @return status as a boolean
     */
    public boolean getAccuracyDecreasedFromEnergyDrink() {
        return this.accuracyDecreasedFromEnergyDrink;
    }

    /**
     * Returns the modifier for burnout duration
     * Default is 0
     * 
     * @return modifier as an integer
     */
    public int getBurnoutDurationModifier() {
        return this.burnoutDurationModifier;
    }

    /**
     * Returns the number of turns of burnout remaining.
     * Returns 0 if the typist is not currently burnt out.
     *
     * @return burnout turns remaining as a non-negative integer
     */
    public int getBurnoutTurnsRemaining()
    {
        return this.turnsRemaining;
    }

    /**
     * Returns the typist's burnout risk (default is 0.05)
     * 
     * @return burnout risk as a double
     */
    public double getBurnoutRisk() {
        return this.burnoutRisk;
    }

    /**
     * Returns the number of times the typist was burnt out
     * 
     * @return burnout count as an integer
     */
    public int getBurnoutCount() {
        return this.burnoutCount;
    }

    /**
     * Returns the original accuracy of the typist (the accuracy before the race)
     * 
     * @return original accuracy as a double
     */
    public double getOriginalAccuracy() {
        return this.originalAccuracy;
    }

    public double getAccuracyPercentage() {
        if (totalCharsTyped==0) { // we cannot divide by 0
            return 100.0; // so technically accuracy is 100% since correctCharsTyped=totalCharsTyped
        }
        return (((double) correctCharsTyped/totalCharsTyped)* 100);
    }

    /**
     * Returns if the typist has just mistyped
     * This is calculated if they have called the 'slideBack' method
     * and have not yet called the 'advanceTypist' method
     * 
     * @return true if they have just mistyped
     */
    public boolean hasJustMistyped() {
        return this.justMistyped;
    }


    /**
     * Resets the attribute 'justMistyped' (i.e. sets it to false)
     * 
     * */
    public void resetJustMistyped() {
        this.justMistyped = false;
    }

    /**
     * Resets the typist to their initial state, ready for a new race.
     * Progress returns to zero, burnout is cleared entirely.
     */
    public void resetToStart()
    {
        this.progress = 0;
        this.burntOut = false;
        this.turnsRemaining = 0;
        this.justMistyped = false;
        this.accuracyIncreasedFromEnergyDrink = false;
        this.accuracyDecreasedFromEnergyDrink = false;
        this.burnoutCount = 0;
        this.correctCharsTyped = 0;
        this.totalCharsTyped = 0;
        this.burnoutRisk = this.originalBurnoutRisk;
    }

    /**
     * Returns true if this typist is currently burnt out, false otherwise.
     *
     * @return true if burnt out
     */
    public boolean isBurntOut()
    {
        return this.burntOut;
    }

    /**
     * Advances the typist forward by one character along the passage.
     * Should only be called when the typist is not burnt out.
     */
    public void typeCharacter()
    {
        if (!(burntOut)) {
            this.progress++;
            this.correctCharsTyped++;
            this.totalCharsTyped++;
        }
    }

    /**
     * Moves the typist backwards by a given number of characters (a mistype).
     * Progress cannot go below zero — the typist cannot slide off the start.
     *
     * @param amount the number of characters to slide back (must be positive)
     */
    public void slideBack(int amount)
    {
        this.justMistyped = true;
        this.totalCharsTyped++; // as they have mistyped
        int newProgress = this.progress - amount;
        if (newProgress < 0) { // if negative
            this.progress = 0; // then put progress equal to zero
        }
        else { // if not negative
            this.progress = newProgress;
        }
    }

    /**
     * Sets the accuracy rating of the typist.
     * Values below 0.0 should be set to 0.0; values above 1.0 should be set to 1.0.
     *
     * @param newAccuracy the new accuracy rating
     */
    public void setAccuracy(double newAccuracy)
    {
        if (newAccuracy < 0.0) {
            this.accuracy = 0.0;
        }

        else if (newAccuracy > 1.0) {
            this.accuracy = 1.0;
        }

        else {
            this.accuracy = newAccuracy;
        }
    }

    /**
     * Sets whether or not the typist has an energy drink modifier
     * 
     * @param newHasEnergyDrink whether they have an energy drink modifier
     */
    public void setHasEnergyDrink(boolean newHasEnergyDrink) {
        this.hasEnergyDrink = newHasEnergyDrink;
    }

    /**
     * Sets the value of if the accuracy increased from the energy drink
     * Used after increasing the value from the energy drink
     * 
     * @param value the status of whether or not they have increased the accuracy
     */
    public void setAccuracyIncreasedFromEnergyDrink(boolean value) {
        this.accuracyIncreasedFromEnergyDrink = value;
    }

    /**
     * Sets the value of if the accuracy decreased from the energy drink
     * Used after decreasing the value from the energy drink
     * 
     * @param value the status of whether or not they have decreased the accuracy
     */
    public void setAccuracyDecreasedFromEnergyDrink(boolean value) {
        this.accuracyDecreasedFromEnergyDrink = value;
    }

    /**
     * Sets the new burnout risk for the typist
     * Default is 0.05
     * 
     * @param newBurnoutRisk the new burnout risk
     */
    public void setBurnoutRisk(double newBurnoutRisk) {
        this.burnoutRisk = newBurnoutRisk;
    }

    /**
     * Sets the burnout duration modifier
     * Default is 0
     * 
     * @param value the new burnout duration modifier
     */
    public void setBurnoutDurationModifier(int value) {
        this.burnoutDurationModifier = value;
    }

    /**
     * Stores the original accuracy into another attribute
     * This is to preserve the original accuracy 
     * as the typist's actual accuracy is likely to change
     * during the race
     * 
     */
    public void setOriginalAccuracy() {
        this.originalAccuracy = this.getAccuracy();
    }

    /**
     * Saves the original burnout risk before any modifiers are applied
     * so that they can be reset
     */
    public void setOriginalBurnoutRisk() {
        this.originalBurnoutRisk = this.burnoutRisk;
    }

    /**
     * Sets the symbol used to represent this typist.
     *
     * @param newSymbol the new symbol character
     */
    public void setSymbol(char newSymbol)
    {
        this.symbol = newSymbol;
    }

}
