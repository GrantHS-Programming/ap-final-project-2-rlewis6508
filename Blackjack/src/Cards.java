import java.util.ArrayList;
import java.util.Scanner;

public class Cards {
    private final String suit;
    private final String value;
    Scanner sc = new Scanner(System.in);
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
        if (value.equals("A")) {
            System.out.println("You have an Ace in your hand, would you like it to have a value of 1 or 11?");
            int aceValue = sc.nextInt();
            if (aceValue == 11) {
                return 11;
            } else if (aceValue == 1) {
                return 1;
            }
        } else if (value.equals("K") || value.equals("Q") || value.equals("J") || value.equals("10")) {
            return 10;
        } else {
            try {
                int numericValue = Integer.parseInt(value);
                if (numericValue >= 2 && numericValue <= 9) {
                    return numericValue;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid card value: " + value);
            }
        }
        return 0;
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