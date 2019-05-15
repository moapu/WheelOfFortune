package wheeloffortune;

/**
 * Board will hold the puzzle and players, making displaying this info easier.
 */
public class Board {

    private final Player _player;
    private final Puzzle _puzzle;

    //For checking letters not guessed.
    private final String _alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//SBI-24
    private final Letters _letters = new Letters();//SBI-24

    /**
     * Constructor initializes puzzle and player.
     */
    public Board(Puzzle puzzle, Player player) {
        _puzzle = puzzle;
        _player = player;
    }

    /**
     * Main method tests instance methods of this class.
     */
    public static void main(String[] args) {
        Player player = new Player();
        Puzzle puzzle = new Puzzle();
        Board board = new Board(puzzle, player);
        board.display();
    }

    /**
     * (SBI-20)     *
     * This method will cycle through puzzle, printing guessed letters, and _
     * for letters not guessed.
     */
    public void display() {
        for (int i = 0; i < _puzzle.getPuzzle().length(); i++) {
            //If this puzzle letter was guessed, or its a space, print it.
            if (_puzzle.isGuessed(_puzzle.getPuzzle().charAt(i))
                    || _puzzle.getPuzzle().charAt(i) == ' ') {
                System.out.print(_puzzle.getPuzzle().charAt(i) + " ");
            } else {
                System.out.print('_' + " ");//Letter stays hidden.
            }
        }

        System.out.println("\n");//Skip 2 lines.

        //Display player name and winnings        
        System.out.println("Player: " + _player.getName());
        System.out.println("Winnings: $" + _player.getWinningsValue() + "\n");

        //SBI-24 - Display vowels and consonants not guessed.
        System.out.println("Consonants Not Guessed:");
        System.out.println(consNotGuessed() + "\n");

        System.out.println("Vowels Not Guessed:");
        System.out.println(vowelsNotGuessed() + "\n");
    }

    /**
     * SBI-24     *
     * Returns a String of consonants not yet guessed.
     */
    public String consNotGuessed() {
        String notGuessed = "";

        //Cycle through alphabet
        for (int i = 0; i < _alphabet.length(); i++) {
            //If this letter was not guessed and is consonant.
            if (!_puzzle.isGuessed(_alphabet.charAt(i))
                    && _letters.isConsonant(_alphabet.charAt(i))) {
                //Add letter to notGuessed.
                notGuessed += _alphabet.charAt(i) + " ";
            }
        }

        return notGuessed;
    }

    /**
     * SBI-24     *
     * Returns a String of vowels not yet guessed.
     */
    public String vowelsNotGuessed() {
        String notGuessed = "";

        //Cycle through alphabet
        for (int i = 0; i < _alphabet.length(); i++) {
            //If this letter was not guessed and is vowel.
            if (!_puzzle.isGuessed(_alphabet.charAt(i))
                    && _letters.isVowel(_alphabet.charAt(i))) {
                //Add letter to notGuessed.
                notGuessed += _alphabet.charAt(i) + " ";
            }
        }

        return notGuessed;
    }

}
