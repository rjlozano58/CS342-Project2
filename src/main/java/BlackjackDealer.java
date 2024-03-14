// Rogelio Lozano and Pradyun Shrestha
// CS 342 - Software Design - Prof. McCarthy
// Project 2: Blackjack
// Description: Class representing a dealer in a Blackjack game

import java.util.ArrayList;
import java.util.Collections;

public class BlackjackDealer {
    // List to hold the deck of cards
    public ArrayList<Card> deck = new ArrayList<>();

    // Method to generate a standard deck of cards
    public void generateDeck(){
        // Array of card suits
        String[] suits = {"S", "H", "D", "C"};
        // Array of card values (10 is repeated for Jack, Queen, King)
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

        // Loop through each suit and value to create the deck
        for (String suit : suits) {
            for (int value : values) {
                deck.add(new Card(suit, value)); // Adding a new card to the deck
            }
        }
    }

    // Method to print the entire deck of cards
    public void printDeck(){
        for (int i = 0; i < deck.size(); i++){
            // Printing the suit and value of each card in the deck
            System.out.println(deck.get(i).suit + " " + deck.get(i).value);
        }
    }

    // Method to deal a hand of two cards
    public ArrayList<Card> dealHand(){
        ArrayList<Card> hand = new ArrayList<Card>();
        for (int i = 0; i < 2; i++){
            int randInt = (int)Math.floor(Math.random() * deck.size()); // Random index to draw a card
            hand.add(deck.get(randInt)); // Adding the card to the hand
            deck.remove(randInt); // Removing the card from the deck
        }
        return hand;
    }

    // Method to draw one card from the top of the deck
    public Card drawOne(){
        Card topCard = new Card(deck.get(0).suit, deck.get(0).value); // Getting the top card
        deck.remove(0); // Removing the top card from the deck
        return topCard; // Returning the drawn card
    }

    // Method to shuffle the deck
    public void shuffleDeck(){
        Collections.shuffle(deck); // Shuffling the deck using Collections.shuffle()
    }

    // Method to get the current size of the deck
    public int deckSize(){
        return deck.size(); // Returning the size of the deck
    }
}
