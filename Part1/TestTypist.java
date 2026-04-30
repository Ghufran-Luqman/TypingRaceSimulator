public class TestTypist {
    public static void main(String[] a) {
        Typist playerTest = new Typist('t', "test player", 0.3);

        playerTest.resetToStart();

        System.out.println("Testing that normal forward movement is possible via typeCharacter():");
        System.out.println("Starting progress: " + playerTest.getProgress());
        int numberOfCharacters = 3;
        for (int i=1;i<=numberOfCharacters;i++) { // type 3 characters
            playerTest.typeCharacter();
        }

        printTestCase("getProgress()", numberOfCharacters, playerTest.getProgress());

        // --------------------------------------------------
        lineBreak(50);

        playerTest.resetToStart();

        System.out.println("Testing that resetToStart() clears both progress and burnout state:");
        playerTest.typeCharacter();

        System.out.println("Current progress (before reset): "+playerTest.getProgress());
        playerTest.burnOut(1);
        System.out.println("isBurntOut (before reset): "+playerTest.isBurntOut());
        System.out.println("getBurnoutTurnsRemaining (before reset): "+playerTest.getBurnoutTurnsRemaining());

        // ----------
        lineBreak(10);

        playerTest.resetToStart();

        printTestCase("getProgress() (after reset)", 0, playerTest.getProgress());

        // ----------
        lineBreak(10);

        printTestCase("isBurntOut() (after reset)", false, playerTest.isBurntOut());

        // ----------
        lineBreak(10);
        printTestCase("getBurnoutTurnsRemaining()", 0, playerTest.getBurnoutTurnsRemaining());

        // --------------------------------------------------
        lineBreak(50);

        playerTest.resetToStart();

        System.out.println("Testing if burnout correctly counts down turn by turn:");
        playerTest.burnOut(2);
        playerTest.recoverFromBurnout();
        
        printTestCase("isBurntOut()", true, playerTest.isBurntOut());

        // ----------
        lineBreak(10);

        printTestCase("getBurnoutTurnsRemaining()", 1, playerTest.getBurnoutTurnsRemaining());

        // ----------
        lineBreak(10);

        System.out.println("Testing burnout clears at 0:\n");
        playerTest.recoverFromBurnout();

        printTestCase("isBurntOut()", false, playerTest.isBurntOut());

        // ----------
        lineBreak(10);

        printTestCase("getBurnoutTurnsRemaining()", 0, playerTest.getBurnoutTurnsRemaining());

        // --------------------------------------------------
        lineBreak(50);
        playerTest.resetToStart();

        numberOfCharacters = 3;
        for (int i=1;i<=numberOfCharacters;i++) { // type 3 characters
            playerTest.typeCharacter();
        }

        System.out.println("Testing if progress can go below zero after calling slideBack():");
        playerTest.slideBack(5);
        printTestCase("getProgress()", 0, playerTest.getProgress());

        // --------------------------------------------------
        lineBreak(50);
        playerTest.resetToStart();

        System.out.println("Testing that accuracy cannot be set outside the 0.0-1.0 range:");
        playerTest.setAccuracy(-0.3);

        printTestCase("getAccuracy()", 0.0, playerTest.getAccuracy());
        
        // ----------
        lineBreak(10);
        playerTest.setAccuracy(1.7);
        printTestCase("getAccuracy()", 1.0, playerTest.getAccuracy());
    }



    public static<T> void printTestCase(String methodName, T expected, T actual) {
        System.out.println(methodName + ":\nExpected: " + expected + "\nActual: " + actual);
        testIfSuccess(expected, actual);
    }

    public static void lineBreak(int numberOfDashes) {
        System.out.print("\n");
        for (int i=0;i<numberOfDashes;i++) {
            System.out.print("-");
        }
        System.out.println("\n");
    }

    public static<T> void testIfSuccess(T a, T b) {
        if (a.equals(b)) {
            System.out.println("Success");
        }
        else {
            System.out.println("Failure");
        }
    }
}