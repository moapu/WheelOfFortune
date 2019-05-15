package wheeloffortune;

import java.util.Arrays;
import java.util.List;

public class Letters {

    private final List<Character> _vowels = Arrays.asList('A', 'E', 'I', 'O', 'U');
    private int _vowelGuessed;

    public static void main(String[] args) {
        Letters letters = new Letters();
        System.out.println(letters.isVowel('k'));
        System.out.println(letters.isConsonant('6'));
    }

    /**
     * It will return true, if the given letter is a vowel
     */
    public boolean isVowel(char letter) {
        return _vowels.contains(letter);
    }

    /**
     * It will return true if the given letter is a consonant and it will return
     * false if the given letter is not a consonants
     */
    public boolean isConsonant(char letter) {
        return !_vowels.contains(letter);
    }

    /**
     * returns the vowels that the user has guessed correctly
     */
    public int getCorrectVowels() {
        return this._vowelGuessed;
    }

    /**
     * increments the vowelGuessed every time it gets called
     */
    public void incrementVowelGuessed() {
        this._vowelGuessed++;
    }

}
