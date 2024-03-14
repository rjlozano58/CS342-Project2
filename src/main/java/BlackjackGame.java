import java.util.ArrayList;

// Class representing a Blackjack game
public class BlackjackGame {
    // List to hold the player's hand of cards
    public ArrayList<Card> playerHand = new ArrayList<Card>();
    // List to hold the banker's (dealer's) hand of cards
    public ArrayList<Card> bankerHand = new ArrayList<Card>();
    // Object representing the dealer of the game
    public BlackjackDealer theDealer = new BlackjackDealer();
    // Object for handling game logic, such as determining the winner
    public BlackjackGameLogic gameLogic = new BlackjackGameLogic();

    // Field to store the current bet placed by the player
    public double currentBet = 0;
    // Field to keep track of the player's total winnings
    public double totalWinnings = 0;

    // Method to evaluate the winnings based on the outcome of the game
    public double evaluateWinnings() {
        // If the player has a blackjack (two cards totaling 21)
        if (playerHand.size() == 2 && this.gameLogic.handTotal(playerHand) == 21) {
            return (currentBet * 1.5); // Player wins 1.5 times the bet
        } else if (this.gameLogic.handTotal(playerHand) <= 21) { // If the player's hand is 21 or under
            return currentBet; // Player wins the amount of the bet
        } else if (this.gameLogic.handTotal(playerHand) > 21) { // If the player busts (hand total over 21)
            return -1 * (currentBet); // Player loses the amount of the bet
        } else if (this.gameLogic.handTotal(bankerHand) > 21) { // If the banker (dealer) busts
            return -1 * (currentBet); // Player wins the amount of the bet
        }
        return 0.0;
    }

    }
