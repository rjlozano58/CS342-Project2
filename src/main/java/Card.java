public class Card {
    String suit = "";
    int value = 0;

    public Card(String theSuit, int theValue){
        suit = theSuit;
        if (theValue > 0 && theValue <= 10) {
            value = theValue;
        } else if (theValue <= 0){
            throw new IllegalArgumentException("Card value must be a positive integer.");
        }
        else {
            throw new IllegalArgumentException("Card value must be less than 11.");
        }
    }
}

