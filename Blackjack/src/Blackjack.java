import javax.smartcardio.Card;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        new Blackjack();
    }
    Blackjack() {
        game();
    }

    public void game() {
        System.out.println("Hello, you many players would like to play Blackjack today?");
        int players = sc.nextInt();

        ArrayList<Cards> deck = Cards.createDeck();
        shuffle(deck);

        ArrayList<ArrayList<Cards>> hands = deal(players, deck);

        for (int i = 0; i < hands.size(); i++) {
            System.out.println("Player " + (i + 1) + "'s hand: ");
            ArrayList<Cards> hand = hands.get(i);
            for (Cards card : hand) {
                System.out.println(card.getValue() + " of " + card.getSuit());
            }
            System.out.println();
        }
    }

    public void shuffle(ArrayList<Cards> deck) {
        System.out.println("Shuffling the deck...");
        Collections.shuffle(deck);
    }

    public Arraylist

}