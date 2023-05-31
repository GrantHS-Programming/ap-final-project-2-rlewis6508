import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Blackjack {
    Scanner sc = new Scanner(System.in);
    private int playerBet;
    public static void main(String[] args) {
        new Blackjack();
    }
    Blackjack() {
        game();
    }

    //main game code, asks the user how players are playing, creates and shuffles a deck, creates and deals hands to numPlayers
    public void game() {
        System.out.println("Hello, welcome to the Blackjack tables!");
        System.out.println("You have $100 to use, enter your bet for this round: ");
        int playerBet = sc.nextInt();

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

        if (getHandValue(dealerHand) == 21 && dealerHand.size() == 2 && getHandValue(playerHand) != 21) {
            for (Cards card : dealerHand) {
                System.out.println(card.getValue() + " of " + card.getSuit());
            }
        } else {
            System.out.println(dealerHand.get(0).getValue() + " of " + dealerHand.get(0).getSuit());
            System.out.println("Hidden Card");
        }

        System.out.println();
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
            System.out.println("""
                    ──────────────────────────────────
                    ─██████████████─████████───██████─
                    ─██░░░░░░░░░░██─██░░░░██───██░░██─
                    ─██████████░░██─████░░██───██░░██─
                    ─────────██░░██───██░░██───██░░██─
                    ─██████████░░██───██░░██───██░░██─
                    ─██░░░░░░░░░░██───██░░██───██░░██─
                    ─██░░██████████───██░░██───██████─
                    ─██░░██───────────██░░██──────────
                    ─██░░██████████─████░░████─██████─
                    ─██░░░░░░░░░░██─██░░░░░░██─██░░██─
                    ─██████████████─██████████─██████─
                    ──────────────────────────────────""");
            playerBet = playerBet * 3; //blackjack, payout 3:1
            return;
        }

        boolean continueHitting = true;
        while (continueHitting) {
            System.out.println("\nWould you like to draw another card (hit), split your hand (p), or stay with your current hand (s)? (h/s/p)");
            String choice = sc.nextLine().toLowerCase();

            switch (choice) {
                case "h":
                    Cards newCard = deck.remove(0);
                    playerHand.add(newCard);
                    System.out.println("Your new card: " + newCard.getValue() + " of " + newCard.getSuit());
                    System.out.println("Your hand: ");
                    printHand(playerHand);
                    System.out.println("Hand value: " + getHandValue(playerHand));

                    if (getHandValue(playerHand) >= 21) {
                        continueHitting = false;
                    }
                    break;
                case "s":
                    continueHitting = false;
                    break;
                case "p":
                    if (playerHand.size() == 2 && playerHand.get(0).getValue().equals(playerHand.get(1).getValue())) {
                        splitHand(playerHand, deck);
                        continueHitting = false;
                    } else {
                        System.out.println("You can't split this hand");
                    }
                    break;
            }
        }
    }

    //dealers turn deals cards, continues to hit if hand value is less than 17, and busts if more than 21
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
        int payout;

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
            System.out.println("You busted. Dealer wins!"); //bust, no payout
        }
        else if (dealerHandValue > 21) {
            System.out.println("The dealer busted. You win!");
            payout = playerBet * 2; //payout is 1:1
            System.out.println("You won $" + payout);
        }
        else if (playerHandValue == dealerHandValue) {
            System.out.println("It's a push!");
            System.out.println("You get back your bet amount: $" + playerBet); //player keeps bet for push
        }
        else if (playerHandValue > dealerHandValue) {
            System.out.println("You win!");
            payout = playerBet * 2; //payout 1:1
            System.out.println("You won $" + payout);
        }
        else {
            System.out.println("Dealer wins!"); //dealer won, no payout
        }
    }

    //prints the cards in player or dealer hand
    public void printHand(ArrayList<Cards> hand) {
        for (Cards card : hand) {
            System.out.println(card.getValue() + " of " + card.getSuit());
        }
    }

    //gets the numeric value of a hand
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

    //splits your hand, removes the original cards from your hand, draws new cards for both hands, and adds the split hands back into the player's hand
    public void splitHand(ArrayList<Cards> playerHand, ArrayList<Cards> deck) {
        System.out.println("Splitting you hand");
        Cards firstCard = playerHand.get(0);
        Cards secondCard = playerHand.get(1);

        ArrayList<Cards> hand1 = new ArrayList<>();
        ArrayList<Cards> hand2 = new ArrayList<>();

        hand1.add(firstCard);
        hand2.add(secondCard);

        playerHand.remove(firstCard);
        playerHand.remove(secondCard);

        hand1.add(deck.remove(0));
        hand2.add(deck.remove(0));

        System.out.println("\nFirst hand: ");
        printHand(hand1);
        System.out.println("Hand Value: " + getHandValue(hand1));

        playerTurn(hand1, deck);

        System.out.println("\nSecond hand: ");
        printHand(hand2);
        System.out.println("Hand Value: " + getHandValue(hand2));

        playerTurn(hand2, deck);

        playerHand.addAll(hand1);
        playerHand.addAll(hand2);
    }
}