package wheeloffortune;

import java.util.Scanner;

public class Player {

    private static int _winningsValue = 0;
    private String _name = "Player";

    /**
     * (SBI-21)     *
     * This constructor will prompt player for name.
     */
    public Player() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter player name: ");
        _name = input.nextLine();

        //Makes sure name is not empty or whitespaces.
        while (_name.isEmpty() || _name.contains("\t") || _name.startsWith(" ")) {
            System.out.print("Enter valid player name: ");
            _name = input.nextLine();
        }

        System.out.println("");
    }

    public static void main(String[] args) {
        Player player = new Player();
        //Display player name and winnings
        System.out.println("Player: " + player.getName());
        System.out.println("Winnings: " + player.getWinningsValue() + "\n");

        // ===============================================================
        // testing deductMoney method.
        System.out.println("Assuming buying vowels ...");

        while (true) {
            player.duductMoney(250);

            System.out.println("===============");
            if (player.getWinningsValue() <= 0) {
                System.out.println("0 MONEY LEFT\nCANNOT DEDUCT ANYMORE");
                break;

            } else {
                System.out.println(player.getWinningsValue() + " money left");
            }

            System.out.println("===============\n");
        }
        //================================================================
    }

    /**
     * (SBI-21)     *
     * This get method returns the String value of player name.
     */
    public String getName() {
        return this._name;
    }

    /**
     * (SBI-21)     *
     * This method sets the name of the player.
     */
    public void setName(String name) {
        this._name = name;
    }

    /**
     * This method returns the winningsValue.
     */
    public int getWinningsValue() {
        return _winningsValue;
    }

    /**
     * This method sets the winningsValue of player
     */
    public void setWinningsValue(int winningsAmount) {
        _winningsValue = winningsAmount;
    }

    /**
     * deducts the passed in money, returns false if cant deduct
     */
    public boolean duductMoney(int money) {
        if ((_winningsValue - money) < 0) {
            return false;
        } else {
            _winningsValue -= money;
            return true;
        }
    }

}
