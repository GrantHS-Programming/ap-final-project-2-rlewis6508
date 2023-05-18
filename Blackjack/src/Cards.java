import java.util.ArrayList;
import java.util.Scanner;

public class Cards {
    private final String suit;
    private final String value;
    private final boolean isAce;

    public Cards(String suit, String value) {
        this.suit = suit;
        this.value = value;
        this.isAce = value.equals("A");
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    public int getNumericValue() {
        if (isAce) {
            Scanner sc = new Scanner(System.in);
            System.out.println("You have an Ace in your hand. Choose its value: 1 or 11?");
            int aceValue = sc.nextInt();

            while (aceValue != 1 && aceValue != 11) {
                System.out.println("Invalid choice. Please enter 1 or 11:");
                aceValue = sc.nextInt();
            }
            return aceValue;
        }
        else if (value.equals("K") || value.equals("Q") || value.equals("J")) {
            return 10;
        }
        else {
            try {
                int numericValue = Integer.parseInt(value);
                if (numericValue >= 2 && numericValue <= 10) {
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