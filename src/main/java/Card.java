// Class representing a playing card
public class Card {
    // Fields for the suit and value of the card
    String suit = "";
    int value = 0;

    // Constructor for creating a new card with a specified suit and value
    public Card(String theSuit, int theValue){
        suit = theSuit; // Assigning the specified suit to the card
        // Validating the value of the card
        if (theValue > 0 && theValue <= 10) {
            value = theValue; // Assigning the specified value to the card if it's within the valid range
        } else if (theValue <= 0){
            // Throwing an exception if the value is not a positive integer
            throw new IllegalArgumentException("Card value must be a positive integer.");
        }
        else {
            // Throwing an exception if the value is greater than 10
            throw new IllegalArgumentException("Card value must be less than 11.");
        }
    }
}