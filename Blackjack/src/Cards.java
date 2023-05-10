import java.util.ArrayList;
public class Cards {
    private final String suit;
    private final String value;

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

    public int getNumericValue() {
        int numericValue = Integer.parseInt(value);
        if (value.equals("A")){
            return 11;
        }
        else if (value.equals("K") || value.equals("Q") || value.equals("J") || value.equals("10")){
            return 10;
        }
        else {
            if (numericValue > 10){
                numericValue = 10;
            }
        }
        return numericValue;
    }

    //creates a full deck of cards
    public static ArrayList<Cards> createDeck() {
        ArrayList<Cards> deck = new ArrayList<>();

        ArrayList<String> suits = new ArrayList<>();
        suits.add("Hearts");
        suits.add("Diamonds");
        suits.add("Clubs");
        suits.add("Spades");

        ArrayList<String> values = new ArrayList<>();
        values.add("1");
        values.add("2");
        values.add("3");
        values.add("4");
        values.add("5");
        values.add("6");
        values.add("7");
        values.add("8");
        values.add("9");
        values.add("10");
        values.add("J");
        values.add("Q");
        values.add("K");
        values.add("A");

        for (String suit : suits) {
            for (String value : values) {
                deck.add(new Cards(suit, value));
            }
        }
        return deck;
    }
}