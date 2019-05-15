package wheeloffortune;

/**
 * Stores the puzzle, and letters that have been guessed.
 */
public class Puzzle {

    private final String _puzzle = "PENN STATE ABINGTON";
    private String _lettersGuessed = "";

    /**
     * Shows that this class methods work.
     */
    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle();

        System.out.print("Is 'A' a correct guess?   ");
        System.out.println(puzzle.isGuessCorrect('A'));//Prints true
        System.out.print("Is 'A' still a correct guess?   ");
        System.out.println(puzzle.isGuessCorrect('A'));//Prints false since 'A' already guessed.
        System.out.println("Second guess was not correct since 'A' was already guessed");
        System.out.print("Is 'X' a correct guess?   ");
        System.out.println(puzzle.isGuessCorrect('X'));//Prints false since 'X' not in puzzle.
        System.out.println("False because 'X' is not in puzzle");

        System.out.println(puzzle.vowelsInPuzzle());
        System.out.println(puzzle.isCorrectSolution("Penn State abington"));
    }

    /**
     * Determines if guess is correct.
     */
    public boolean isGuessCorrect(char guess) {
        //First check if char already guessed.
        if (isGuessed(guess)) {
            return false;//Incorrect guess.
        } else//if letter wasnt guessed, add to letters guessed.
        {
            _lettersGuessed += Character.toUpperCase(guess);
        }

        return guess(guess);
    }

    /**
     * (SBI-25)
     * returns the amount of vowels in the puzzle
     * All it does is accept a string, and return true if it's correct
     */
    public boolean isCorrectSolution(String possibleSolution) {
        return possibleSolution.toUpperCase().equals(_puzzle);
    }

    /**
     * (SBI-17)
     * returns the amount of vowels in the puzzle
     * counting only vowels which are in the puzzle. counting once for multiple
     * occurrence of the same vowel.
     */
    public int vowelsInPuzzle() {
        String[] vowels = {"A", "E", "I", "O", "U"};

        int numofVowels = 0;
        for (String c : vowels) {
            if (_puzzle.contains(c)) {
                numofVowels++;
            }

        }

        return numofVowels;
    }

    /**
     * (SBI-22)     *
     * Determines if letter has already been guessed.
     */
    public boolean isGuessed(char c) {
        //Cycle through _lettersGuessed, checking if this letter was guessed.
        for (int i = 0; i < _lettersGuessed.length(); i++) {
            if (_lettersGuessed.charAt(i) == c) {
                return true;//Letter has been guessed
            }
        }
        return false;
    }

    /**
     * (SBI-23)     *
     * Determines if guessed char is in the puzzle.
     */
    public boolean guess(char c) {
        //Cycle through puzzle, checking for guessed char.
        for (int i = 0; i < _puzzle.length(); i++) {
            if (_puzzle.charAt(i) == Character.toUpperCase(c)) {
                return true;
            }
        }
        return false;//Incorrect guess.
    }

    /**
     * returns letters already guessed.
     */
    public String getLettersGuessed() {
        return _lettersGuessed;
    }

    /**
     * sets the letter guessed to the class variable.
     */
    public void setLettersGuessed(String s) {
        _lettersGuessed = s;
    }

    /**
     * returns the puzzle String.
     */
    public String getPuzzle() {
        return _puzzle;
    }

}
