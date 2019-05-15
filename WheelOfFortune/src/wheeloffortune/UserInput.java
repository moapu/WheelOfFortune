package wheeloffortune;

import java.util.Scanner;

/**
 * reads a letter from the keyboard. If anything but a single letter is entered,
 * an error will appear, and the user will repeatedly be prompted until one
 * letter is entered from the keyboard.
 */
public class UserInput {

    private final Puzzle _puzzle = new Puzzle();

    /**
     * main menu just makes an instance of UserInput and calls the readLetter
     * method.
     */
    public static void main(String[] args) {
        UserInput userInput = new UserInput();
        userInput.guessLetter();
    }

    /**
     * Prompts user to enter letter, calls readLetter() to get input.
     */
    public char guessLetter() {
        System.out.print("Enter a single letter >> ");
        char letter = readLetter();

        //Use _puzzle field to work with non-static methods.
        //If letter is in puzzle...
        if (_puzzle.guess(letter)) {
            System.out.println("Correct!");
        } else {
            System.out.println("Sorry, incorrect");
        }

        return letter;
    }

    /**
     * Reads a String from keyboard, and returns first character. This method
     * makes sure it's one letter.
     */
    public char readLetter() {
        Scanner input = new Scanner(System.in);
        String letter = input.nextLine();//Get String.

        // makes sure the user only enters one letter
        while (!letter.matches("[a-zA-Z]+") || letter.length() > 1) {
            System.out.print("Please enter a valid letter >> ");
            letter = input.nextLine();
        }

        return letter.charAt(0);//Return first care of String.
    }

}
