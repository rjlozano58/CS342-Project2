// Rogelio Lozano and Pradyun Shrestha
// CS 342 - Software Design - Prof. McCarthy
// Project 2: Blackjack
// Description: Class for handling the logic of a Blackjack game

import java.util.ArrayList;

public class BlackjackGameLogic {

    // Method to determine the winner based on the player's and dealer's hands
    public String whoWon(ArrayList<Card> playerHand, ArrayList<Card> dealerHand) {
        int playerTotal = handTotal(playerHand); // Calculate the total value of the player's hand
        int dealerTotal = handTotal(dealerHand); // Calculate the total value of the dealer's hand

        // Determine the winner based on the hand totals
        if ((dealerTotal <= 21 && playerTotal <= 21) && (playerTotal == dealerTotal)) {
            return "draw"; // It's a tie
        } else if ((dealerTotal <= 21 && playerTotal <= 21) && (playerTotal > dealerTotal)) {
            return "player"; // The player wins
        } else if ((dealerTotal <= 21 && playerTotal <= 21) && (playerTotal < dealerTotal)) {
            return "dealer"; // The dealer wins
        }

        return ""; // Return an empty string if none of the conditions are met
    }

    // Method to calculate the total value of a hand
    public int handTotal(ArrayList<Card> hand) {
        int totalAceAs1 = 0; // Total value of the hand with all aces counted as 1
        int totalAceAs11 = 0; // Total value of the hand with all aces counted as 11

        // Calculate the total values
        for (Card card : hand) {
            totalAceAs1 += card.value;
            totalAceAs11 += (card.value == 1) ? 11 : card.value; // If the card is an ace, count it as 11
        }

        // Return the best total value (<= 21)
        return (totalAceAs11 <= 21) ? totalAceAs11 : totalAceAs1;
    }

    // Method to determine if the dealer should draw another card
    public boolean evaluateBankerDraw(ArrayList<Card> hand) {
        int dealerTotal = handTotal(hand); // Calculate the total value of the dealer's hand
        return dealerTotal <= 16; // The dealer draws if their total is 16 or less
    }

    // Method to print the hand with suit symbols
    public String printHand(ArrayList<Card> hand) {
        StringBuilder returnString = new StringBuilder();

        // Construct the string representation of the hand
        for (Card card : hand) {
            switch (card.suit) {
                case "H":
                    returnString.append("♥").append(card.value).append(" ");
                    break;
                case "S":
                    returnString.append("♠").append(card.value).append(" ");
                    break;
                case "C":
                    returnString.append("♣").append(card.value).append(" ");
                    break;
                case "D":
                    returnString.append("♦").append(card.value).append(" ");
                    break;
            }
        }
        return returnString.toString();
    }

    // Method to print the hand with the first card hidden
    public String printFirstHidden(ArrayList<Card> hand) {
        StringBuilder returnString = new StringBuilder("X "); // Hide the first card

        // Construct the string representation of the hand, starting from the second card
        for (int i = 1; i < hand.size(); i++) {

            Card card = hand.get(i);
            switch (card.suit) {
                case "H":
                    returnString.append("♥").append(card.value).append(" ");
                    break;
                case "S":
                    returnString.append("♠").append(card.value).append(" ");
                    break;
                case "C":
                    returnString.append("♣").append(card.value).append(" ");
                    break;
                case "D":
                    returnString.append("♦").append(card.value).append(" ");
                    break;
            }
        }
        return returnString.toString();
    }
}
