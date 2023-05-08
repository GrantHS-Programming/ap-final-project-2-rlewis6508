import java.util.ArrayList;
import java.util.Collections;
public class Cards {
    private String suit;
    private String value;

    public Cards(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }
    public String getValue() {
        return value;
    }

    //creates a full deck of cards
    public static ArrayList<Cards> createDeck() {
        ArrayList<String> suits = new ArrayList<String>();
        suits.add("Hearts");
        suits.add("Diamonds");
        suits.add("Clubs");
        suits.add("Spades");

        ArrayList<String> values = new ArrayList<String>();
        values.add("Ace");
        values.add("Two");
        values.add("Three");
        values.add("Four");
        values.add("Five");
        values.add("Six");
        values.add("Seven");
        values.add("Eight");
        values.add("Nine");
        values.add("Ten");
        values.add("Jack");
        values.add("Queen");
        values.add("King");

        ArrayList<Cards> deck = new ArrayList<Cards>();
        for (String suit : suits) {
            for (String value : values) {
                deck.add(new Cards(suit, value));
            }
        }
        Collections.shuffle(deck);
        return deck;
    }
}
