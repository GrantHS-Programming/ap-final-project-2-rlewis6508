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
        System.out.println("Hello, welcome to the Blackjack tables!");

        ArrayList<Cards> deck = Cards.createDeck();
        shuffle(deck);

        ArrayList<Cards> playerHand = new ArrayList<>();
        ArrayList<Cards> dealerHand = new ArrayList<>();

        dealInitialCards(playerHand, dealerHand, deck);

        //prints the players hand
        System.out.println("Your hand: ");
        printHand(playerHand);
        System.out.println("Hand value: " + getHandValue(playerHand));

        //prints the dealers first card and value of hand
        System.out.println("\nDealer's hand: ");
        System.out.println(dealerHand.get(0).getValue() + " of " + dealerHand.get(0).getSuit());
        System.out.println("Hidden Card");

        playerTurn(playerHand, deck);
        dealerTurn(dealerHand, deck);
        determineWinner(playerHand, dealerHand);
    }

    //shuffles the deck of cards
    public void shuffle (ArrayList < Cards > deck) {
        System.out.println("\nShuffling the deck...\n");
        Collections.shuffle(deck);
    }

    //deals initial cards to the player and dealer
    public void dealInitialCards(ArrayList<Cards> playerHand, ArrayList<Cards> dealerHand, ArrayList<Cards> deck) {
        for (int i = 0; i < 2; i++) {
            Cards playerCard = deck.remove(0);
            Cards dealerCard = deck.remove(0);
            playerHand.add(playerCard);
            dealerHand.add(dealerCard);
        }
    }

    //players turn with hit/stay mechanic
    public void playerTurn(ArrayList<Cards> playerHand, ArrayList<Cards> deck) {
        System.out.println("\nPlayer's turn: ");

        System.out.println("Your hand:");
        printHand(playerHand);
        System.out.println("Hand value: " + getHandValue(playerHand));

        //checks if player has blackjack
        if (getHandValue(playerHand) == 21 && playerHand.size() == 2) {
            System.out.println("Blackjack! You win!");
            return;
        }

        boolean continueHitting = true;
        while (continueHitting) {
            System.out.println("\nWould you like to draw another card (hit), or stay with your current hand? (h/s)");
            String hit = sc.nextLine().toLowerCase();

            if (hit.equals("h")) {
                Cards newCard = deck.remove(0);
                playerHand.add(newCard);
                System.out.println("Your new card: " + newCard.getValue() + " of " + newCard.getSuit());

                System.out.println("Your hand: ");
                printHand(playerHand);
                System.out.println("Hand value: " + getHandValue(playerHand));

                if (getHandValue(playerHand) >= 21) {
                    continueHitting = false;
                }
            } else {
                continueHitting = false;
            }
        }
    }

    public void dealerTurn(ArrayList<Cards> dealerHand, ArrayList<Cards> deck){
        System.out.println("\nDealer's turn: ");

        System.out.println("Dealer's hand:");
        printHand(dealerHand);
        System.out.println("Hand value: " + getHandValue(dealerHand));

        while (getHandValue(dealerHand) < 17) {
            Cards newCard = deck.remove(0);
            dealerHand.add(newCard);
            System.out.println("\nDealer draws a new card: " + newCard.getValue() + " of " + newCard.getSuit());

            System.out.println("Dealer's hand:");
            printHand(dealerHand);
            System.out.println("Hand value: " + getHandValue(dealerHand));
        }

        if (getHandValue(dealerHand) > 21) {
            System.out.println("The dealer busted.");
        } else {
            System.out.println("Dealer stays.");
        }
    }

    //determines if the player or dealer won
    public void determineWinner(ArrayList<Cards> playerHand, ArrayList<Cards> dealerHand) {
        int playerHandValue = getHandValue(playerHand);
        int dealerHandValue = getHandValue(dealerHand);

        System.out.println("\nResults of this round: ");

        System.out.println("Your hand:");
        printHand(playerHand);
        System.out.println("Hand value: " + playerHandValue);

        System.out.println("\nDealer's hand:");
        printHand(dealerHand);
        System.out.println("Hand value: " + dealerHandValue);

        if (playerHandValue > 21) {
            System.out.println("You busted. Dealer wins!");
        } else if (dealerHandValue > 21) {
            System.out.println("The dealer busted. You win!");
        } else if (playerHandValue == dealerHandValue) {
            System.out.println("It's a tie (push)!");
        } else if (playerHandValue > dealerHandValue) {
            System.out.println("You win!");
        } else {
            System.out.println("Dealer wins!");
        }
    }

    //prints the cards in player or dealer hand
    public void printHand(ArrayList<Cards> hand) {
        for (Cards card : hand) {
            System.out.println(card.getValue() + " of " + card.getSuit());
        }
    }

    public int getHandValue(ArrayList<Cards> hand) {
        int value = 0;
        int numAces = 0;

        for (Cards card : hand) {
            int cardValue = card.getNumericValue();
            if (cardValue == 11) {
                numAces++;
            }
            value += cardValue;
        }

        while (numAces > 0 && value > 21) {
            value -= 10;
            numAces--;
        }
        return value;
    }
}