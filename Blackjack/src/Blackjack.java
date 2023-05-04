import java.util.ArrayList;
public class Blackjack {

    public static void main(String[] args) {
        new Blackjack();
    }

    public Blackjack() {
        shuffle();
    }

    public void shuffle() {
        ArrayList<Cards> deck = Cards.createDeck();
        for (Cards card : deck) {
            System.out.println(card.getValue() + " of " + card.getSuit());
        }
    }
}