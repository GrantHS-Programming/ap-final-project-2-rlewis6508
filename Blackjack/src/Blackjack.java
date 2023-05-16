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

    //main game code, asks the user how players are playing, creates and shuffles a deck, creates and deals hands to numPlayers
    public void game() {
        System.out.println("Hello, you many players would like to play Blackjack today?");
        int numPlayers = sc.nextInt();

        ArrayList<Cards> deck = Cards.createDeck();
        shuffle(deck);

        ArrayList<ArrayList<Cards>> hands = deal(numPlayers, deck);
        ArrayList<Cards> dealerHand = dealer(deck);

        //prints each player's cards and value of hand
        for (int i = 0; i < hands.size(); i++) {
            System.out.println("Player " + (i + 1) + "'s hand: ");
            ArrayList<Cards> hand = hands.get(i);
            int handValue = getHandValue(hand);
            for (Cards card : hand) {
                System.out.println(card.getValue() + " of " + card.getSuit());
            }
            System.out.println("Hand value: " + handValue);
            System.out.println();
        }

        //prints the dealers cards and value of hand
        System.out.println("Dealer's hand: ");
        for (Cards card : dealerHand){
            System.out.println(card.getValue() + " of " + card.getSuit());
        }
        int dealerHandValue = getHandValue(dealerHand);
        System.out.println("Dealer's hand value: " + dealerHandValue);

        //hit or stay
        System.out.println("\nWould you like to draw another card (hit) or stay with your current hand? h/s");
        String hit = sc.nextLine();
        //deals another card
        if (hit.equalsIgnoreCase("h")){
            Cards card = deck.remove(0);
            hands.get(0).add(card);
            //same code as initial deal, prints the players new cards and hand value
            for (int i = 0; i < hands.size(); i++) {
                System.out.println("Player " + (i + 1) + "'s hand: ");
                ArrayList<Cards> hand = hands.get(i);
                int handValue = getHandValue(hand);
                for (Cards cards : hand) {
                    System.out.println(card.getValue() + " of " + card.getSuit());
                }
                System.out.println("Hand value: " + handValue);
                System.out.println();
            }
        }


        //if the dealers hand is less than 17, it must hit and take another card, if not
        while (dealerHandValue < 17) {
            System.out.println("Dealer has to draw another card.");
            Cards card = deck.remove(0);
            dealerHand.add(card);
            dealerHandValue = getHandValue(dealerHand);
            System.out.println("Dealer's new hand value: " + dealerHandValue);
        }

        if (dealerHandValue > 21) {
            System.out.println("The dealer busted.");
        }
        else {
            System.out.println("Dealer stays.");
        }

        System.out.println();

        //determines who wins the hand
        for (int i = 0; i < hands.size(); i++) {
            ArrayList<Cards> hand = hands.get(i);
            int handValue = getHandValue(hand);

            if (handValue == 21){
                System.out.println("Blackjack! You automatically win.");
            }
            else if (handValue > 21) {
                System.out.println("Player " + (i + 1) + " busts.");
            }
            else if (handValue == dealerHandValue) {
                System.out.println("Player " + (i + 1) + " pushed with the dealer.");
            }
            else if (handValue > dealerHandValue || dealerHandValue > 21) {
                System.out.println("Player " + (i + 1) + " wins!");
            }
            else {
                System.out.println("Player " + (i + 1) + " loses.");
            }
        }
    }

    //shuffles the deck of cards
    public void shuffle(ArrayList<Cards> deck) {
        System.out.println("\nShuffling the deck...\n");
        Collections.shuffle(deck);
    }

    //deals two cards to each player who is playing
    public ArrayList<ArrayList<Cards>> deal(int numPlayers, ArrayList<Cards> deck){
        ArrayList<ArrayList<Cards>> hands = new ArrayList<>();

        for (int i = 0; i < numPlayers; i++) {
            hands.add(new ArrayList<>());
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < numPlayers; j++) {
                Cards card = deck.remove(0);
                hands.get(j).add(card);
            }
        }
        return hands;
    }

    //deals cards to the dealer
    public ArrayList<Cards> dealer(ArrayList<Cards> deck){
        ArrayList<Cards> dealerHand = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Cards card = deck.remove(0);
            dealerHand.add(card);
        }
        return dealerHand;
    }

    //gets the value of player x hand
    public int getHandValue(ArrayList<Cards> hand){
        int value = 0;
        int numAces = 0;
        for (Cards card : hand) {
            int cardValue = card.getNumericValue();
            if (cardValue == 11) {
                numAces++;
            }
            value += cardValue;
        }
        while (numAces > 0 && value < 12) {
            value+= 10;
            numAces--;
        }
        return value;
    }
}