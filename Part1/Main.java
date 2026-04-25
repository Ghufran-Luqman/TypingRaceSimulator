public class Main {
    public static void main(String[] a) {
        TypingRace race = new TypingRace(68);
        Typist typist1 = new Typist('①', "TURBOFINGERS", 0.85);
        Typist typist2 = new Typist('②', "QWERTY_QUEEN", 0.80);
        Typist typist3 = new Typist('③', "HUNT_N_PECK", 0.86);
        
        race.addTypist(typist1, 1);
        race.addTypist(typist2, 2);
        race.addTypist(typist3, 3);

        race.startRace();
    }
}
