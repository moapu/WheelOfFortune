package wheeloffortune;

import java.util.Scanner;

public class WheelOfFortune {

    private final Player _player = new Player();
    private final Puzzle _puzzle = new Puzzle();
    private final Letters _letters = new Letters();
    private final UserInput _userInput = new UserInput();
    private final Wheel _spinWheel = new Wheel();
    private final Board _board = new Board(_puzzle, _player);

    public static void main(String[] args) {
        WheelOfFortune wheelOfFortune = new WheelOfFortune();
        wheelOfFortune.loadGame();
    }

    /**
     * Program starts and ends in this method. Menus will continue to be
     * displayed until quit is selected. Code from main was put in this method
     * to use fields of this class, in a non-static method.
     */
    private void loadGame() {
        Menu menu = new Menu("Wheel of Fortune");
        MenuChoice spin = menu.addChoice("Spin the Wheel");
        MenuChoice buy = menu.addChoice("Buy a vowel");
        MenuChoice solve = menu.addChoice("Solve the puzzle");
        MenuChoice help = menu.addChoice("Help");
        MenuChoice quit = menu.addChoice("Quit");

        //User is repeatedly displayed a menu for Wheel of Fortune game until
        //quit is selected.
        while (true) {
            System.out.println("\t-= " + menu.getTitle() + " =-\n");
            _board.display();
            MenuChoice choice = menu.chooseFromMenu();
            System.out.println("");

            if (choice == quit) {
                break;
            } else if (choice == buy) {
                // (SBI-17)
                selectedBuy();

            } else if (choice == spin) {
                // (SBI-16)
                selectedSpin();
            } else if (choice == solve) {
                // (SBI-29)
                selectedSolve();

            } else if (choice == help) {
                //(SBI-26)
                displayHelp();
            }
        }
    }

    /**
     * (SBI-17)
     *
     * when the user chooses to Buy a vowel, the following checks for invalid
     * input, if the entered lettered in the puzzle, if the letter is a vowel.
     */
    private void selectedBuy() {
        //if all the vowels are revealed, cannot buy anymore vowels
        if (_letters.getCorrectVowels() == _puzzle.vowelsInPuzzle()) {
            System.out.println("All the vowel have been guessed\n");
            return;
        }
        //if deduct money returns false, wasn't enough money
        if (!_player.duductMoney(250)) {
            System.out.println("Not enough money to buy a vowel\n");
            return;
        }

        System.out.print("Please enter vowel: ");
        char vowel = Character.toUpperCase(_userInput.readLetter());//returns one letter char
        // from user.

        System.out.println("");

        //while vowel input is not a vowel, or was guessed already.
        while (_puzzle.isGuessed(vowel) || !_letters.isVowel(vowel)) {

            //Print proper error
            if (!_letters.isVowel(vowel)) {
                System.out.print("Invalid entry, please enter a vowel: ");
            } else if (_puzzle.isGuessed(vowel)) {
                System.out.print("Sorry, letter was guessed already, try again: ");
            }
            vowel = Character.toUpperCase(_userInput.readLetter());
            System.out.println("");
        }
        //Check if guess is in puzzle
        //increment number of vowel guessed only when it is correct
        if (_puzzle.isGuessCorrect(vowel)) {
            _letters.incrementVowelGuessed();
            System.out.println("CORRECT!\n");

        } else {
            System.out.println("WRONG!\n");
        }
        //Add this letter to letters gueesed of this puzzle, whether right or wrong
        _puzzle.setLettersGuessed(_puzzle.getLettersGuessed() + vowel);
    }

    /**
     * (SBI-16)
     *
     * When a player spins the wheel, and lands on a money wedge, then prompt
     * for a consonant Ensure that only a consonant is accepted If that chosen
     * consonant has already been guessed, then re-prompt the user Also if the
     * letter is in the puzzle, increases the player's winnings by that wedge
     * amount
     */
    private void selectedSpin() {
        //Spin wheel returning a wedge value
        String landedWedge = _spinWheel.spin().getTitle();

        switch (landedWedge) {
            case "BANKRUPT":
                System.out.println("You landed on " + landedWedge + '\n');
                _player.setWinningsValue(0); //(SBI-27)
                break;
            case "LOSE A TURN":
                System.out.println("You landed on LOSE-A-TURN\n"); // (SBI-28)
                break;
            default:
                //player has landed on a money wedge.
                System.out.println("You landed on $" + landedWedge + '\n');
                System.out.print("Please enter a consonant: ");
                char cons = Character.toUpperCase(_userInput.readLetter());//returns one letter
                // char from user.
                System.out.println("");

                //while cons input is a vowel, or was guessed already.
                while (!_letters.isConsonant(cons) || _puzzle.isGuessed(cons)) {
                    //Print proper error
                    if (!_letters.isConsonant(cons)) {
                        System.out.print("Invalid entry, please enter a consonant: ");
                    }
                    if (_puzzle.isGuessed(cons)) {
                        System.out.print("Sorry, letter was guessed already, try again: ");
                    }

                    cons = Character.toUpperCase(_userInput.readLetter());
                    System.out.println("");
                }

                //Check if guess is in puzzle
                if (_puzzle.isGuessCorrect(cons)) {
                    System.out.println("CORRECT!\n");

                    //Increment player winnings by wedge value
                    _player.setWinningsValue(_player.getWinningsValue()
                            + Integer.parseInt(landedWedge));
                } else//If guess is not in puzzle
                {
                    System.out.println("WRONG!\n");
                }

                //Add this letter to letters guessed in this puzzle, whether right or wrong
                _puzzle.setLettersGuessed(_puzzle.getLettersGuessed() + cons);
                break;
        }
    }

    /**
     * (SBI-26)
     *
     * how the help message will be displayed
     */
    private static void displayHelp() {
        System.out.println("=============================================");
        System.out.println("       -=Help for Wheel of Fortune=-         \n");
        System.out.println("The player has these options from the menu:");
        System.out.println("- spin the wheel and call a consonant");
        System.out.println("- buy a vowel for $250");
        System.out.println("- solve the puzzle");
        System.out.println("- display help");
        System.out.println("- quit the game");
        System.out.println();
        System.out.println("Each consonant is worth the cash value of the \n"
                + "wedge the wheel lands on.");
        System.out.println();
        System.out.println("If the player lands on Bankrupt their winnings \n"
                + "drop to zero.");
        System.out.println();
        System.out.println("Not implemented yet: \n"
                + "- Multiple players\n"
                + "- Lose a turn");
        System.out.println("=============================================\n");
    }

    /**
     * (SBI-29)
     *
     * returns the amount of vowels in the puzzle
     * If correct, tell the player they won, display the winnings, and quit
     */
    private void selectedSolve() {
        Scanner input = new Scanner(System.in);

        System.out.print("Solve the puzzle: ");
        String solve = input.nextLine();

        if (_puzzle.isCorrectSolution(solve)) {
            System.out.println("\nYou are correct!");
            System.out.println("You won $" + _player.getWinningsValue() + "\n");
        } else {
            System.out.println("\nYou are incorrect!");
            System.out.println("The puzzle was: " + _puzzle.getPuzzle());
            System.out.println("So you get NOTHING! You LOSE! Good DAY, sir!");
        }

        System.exit(0); // quits
    }

}
