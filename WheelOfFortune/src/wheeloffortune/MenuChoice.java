package wheeloffortune;

public class MenuChoice {

    private String _text;

    private MenuChoice() { }

    /**
     * assign the private field to the variable
     */
    public MenuChoice(String text) {
        _text = text;
    }

    /**
     * return the text of menu choice
     */
    public String getText() {
        return _text;
    }
}
