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
        suits.add("HEARTS");
        suits.add("DIAMONDS");
        suits.add("CLUBS");
        suits.add("SPADES");

        ArrayList<String> values = new ArrayList<String>();
        values.add("ACE");
        values.add("TWO");
        values.add("THREE");
        values.add("FOUR");
        values.add("FIVE");
        values.add("SIX");
        values.add("SEVEN");
        values.add("EIGHT");
        values.add("NINE");
        values.add("TEN");
        values.add("JACK");
        values.add("QUEEN");
        values.add("KING");

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
