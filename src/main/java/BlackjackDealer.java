import java.util.ArrayList;
import java.util.Collections;


public class BlackjackDealer {
    public ArrayList<Card> deck = new ArrayList<>();
    public void generateDeck(){
        String[] suits = {"S", "H", "D", "C"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

        for (String suit : suits) {
            for (int value : values) {
                deck.add(new Card(suit, value));
            }
        }
    }

    public void printDeck(){
        for (int i = 0; i < deck.size(); i++){
            System.out.println(deck.get(i).suit + " " + deck.get(i).value);
        }
    }

    public ArrayList<Card> dealHand(){
        ArrayList<Card> hand = new ArrayList<Card>();
        for (int i = 0; i < 2;i++){
            int randInt = (int)Math.floor(Math.random() * deck.size());
            hand.add(deck.get(randInt));
            deck.remove(randInt);
        }
        return hand;
    }

    public Card drawOne(){
        Card topCard = new Card(deck.get(0).suit,deck.get(0).value);
        deck.remove(0);
        return topCard;
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    public int deckSize(){
        return deck.size();
    }

}
