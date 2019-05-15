package wheeloffortune;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * saves all the choices in a list , prompts the user to choose a choice, and
 * displays the menu choices to the user
 */
public class Menu {

    private List<MenuChoice> _choices = null;
    private String _title = null;
    private Scanner _keyboard = null;

    private Menu() {}

    /**
     * Assign the title to this instance, a Scanner that will read from the
     * keyboard the new ArrayList to hold the menu _choices
     */
    Menu(String title) {
        _title = title;
        _keyboard = new Scanner(System.in);
        _choices = new ArrayList<>();
    }

    /**
     * adds some choices and ask asks the user to enter a value depending on
     * user's answer, it says "You chose [choice]" until user hits quit(5)
     */
    public static void main(String[] args) {
        Menu menu = new Menu("Wheel of Fortune");
        MenuChoice spinWheel = menu.addChoice("Spin Wheel");
        MenuChoice buyAVowel = menu.addChoice("Buy a vowel");
        MenuChoice solveThePuzzle = menu.addChoice("Solve the puzzle");
        MenuChoice help = menu.addChoice("Help");
        MenuChoice quit = menu.addChoice("Quit");

        while (true) {
            MenuChoice choice = menu.chooseFromMenu();
            System.out.println("You chose: " + choice.getText() + "\n");
            if (choice == quit) {
                break;
            }
        }
    }

    /**
     * Create a new instance of MenuChoice and Add this new menu choice to our
     * list and returning the menu choice
     */
    MenuChoice addChoice(String text) {
        MenuChoice choice = new MenuChoice(text);
        _choices.add(choice);
        return choice;
    }

    /**
     * displaying menu, asking user to enter the choice,reading line from
     * keyboard, converting the text into an integer and returning it to the
     * Menu choice
     */
    MenuChoice chooseFromMenu() {
        displayMenu();
        int choice = 0;
        String input;

        while (true) {
            System.out.print("Enter choice: ");
            input = _keyboard.nextLine();

            // enforces to be an Integer
            if (!(input.matches("\\d+"))) {
                System.out.println("INVALID");
                continue;
            }

            choice = Integer.parseInt(input);

            // if entered 1-5, loop breaks
            if (!(choice > 0 && choice <= _choices.size())) {
                System.out.println("INVALID");
            } else {
                break;
            }
        }

        return _choices.get(choice - 1);
    }

    /**
     * creating a for loop to get and print choices the loop ends when choice
     * size is completed
     */
    void displayMenu() {
        //System.out.println("-= " + _title + " =-"); //moved to wof.main
        int num = 1;
        for (MenuChoice choice : _choices) {
            System.out.println(num + ") " + choice.getText());
            num++;
        }
        System.out.println("");
    }

    public String getTitle() {
        return _title;
    }

}
