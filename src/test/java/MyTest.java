// Importing necessary classes and packages for testing
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;

// Test class for Card
class CardTest {

	// Test for a valid card
	@Test
	void testValidCard() {
		Card card = new Card("Hearts", 5);
		assertEquals("Hearts", card.suit);
		assertEquals(5, card.value);
	}

	// Test for an invalid card value (too high)
	@Test
	void testInvalidCardValueTooHigh() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Card("Spades", 11);
		});
		assertEquals("Card value must be less than 11.", exception.getMessage());
	}

	// Test for an invalid card value (negative)
	@Test
	void testInvalidCardValueNegative() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Card("Diamonds", -1);
		});
		assertEquals("Card value must be a positive integer.", exception.getMessage());
	}

	// Test for an invalid card value (zero)
	@Test
	void testInvalidCardValueZero() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Card("Clubs", 0);
		});
		assertEquals("Card value must be a positive integer.", exception.getMessage());
	}
}

// Test class for BlackjackDealer
class BlackjackDealerTest {
	BlackjackDealer dealer;

	// Set up method to initialize the dealer and generate the deck before each test
	@BeforeEach
	void setUp() {
		dealer = new BlackjackDealer();
		dealer.generateDeck();
	}

	// Test for generating the deck
	@Test
	void testGenerateDeck() {
		assertEquals(52, dealer.deckSize());
	}

	// Test for dealing a hand
	@Test
	void testDealHand() {
		ArrayList<Card> hand = dealer.dealHand();
		assertEquals(2, hand.size());
		assertEquals(50, dealer.deckSize());
	}

	// Test for drawing one card
	@Test
	void testDrawOne() {
		Card drawnCard = dealer.drawOne();
		assertNotNull(drawnCard);
		assertEquals(51, dealer.deckSize());
	}

	// Test for shuffling the deck
	@Test
	void testShuffleDeck() {
		ArrayList<Card> originalDeck = new ArrayList<>(dealer.deck);
		dealer.shuffleDeck();
		assertNotEquals(originalDeck, dealer.deck);
	}

	// Test for checking the deck size
	@Test
	void testDeckSize() {
		assertEquals(52, dealer.deckSize());
	}
}

// Test class for BlackjackGameLogic
class BlackjackGameLogicTest {
	BlackjackGameLogic gameLogic = new BlackjackGameLogic();

	// Test for determining the winner
	@Test
	void testWhoWon() {
		ArrayList<Card> playerHand = new ArrayList<>();
		playerHand.add(new Card("S", 10));
		playerHand.add(new Card("D", 8));

		ArrayList<Card> dealerHand = new ArrayList<>();
		dealerHand.add(new Card("C", 10));
		dealerHand.add(new Card("H", 7));

		assertEquals("player", gameLogic.whoWon(playerHand, dealerHand));
	}

	// Test for calculating the total hand value
	@Test
	void testHandTotal() {
		ArrayList<Card> hand = new ArrayList<>();
		hand.add(new Card("S", 1));
		hand.add(new Card("D", 10));
		assertEquals(21, gameLogic.handTotal(hand));
	}

	// Test for evaluating if the banker should draw another card
	@Test
	void testEvaluateBankerDraw() {
		ArrayList<Card> hand = new ArrayList<>();
		hand.add(new Card("C", 10));
		hand.add(new Card("H", 6));
		assertTrue(gameLogic.evaluateBankerDraw(hand));
	}

	// Test for printing the hand
	@Test
	void testPrintHand() {
		ArrayList<Card> hand = new ArrayList<>();
		hand.add(new Card("H", 10));
		hand.add(new Card("S", 1));
		assertEquals("♥10 ♠1 ", gameLogic.printHand(hand));
	}

	// Test for printing the hand with the first card hidden
	@Test
	void testPrintFirstHidden() {
		ArrayList<Card> hand = new ArrayList<>();
		hand.add(new Card("H", 10));
		hand.add(new Card("S", 1));
		assertEquals("X ♠1 ", gameLogic.printFirstHidden(hand));
	}
}
// Test class for BlackjackGame
class BlackjackGameTest {
	BlackjackGame game;

	// Set up method to initialize the game, generate the deck, and set the current bet before each test
	@BeforeEach
	void setUp() {
		game = new BlackjackGame();
		game.theDealer.generateDeck();
		game.currentBet = 100;
	}

	// Test for evaluating winnings for a blackjack
	@Test
	void testEvaluateWinningsForBlackjack() {
		game.playerHand.add(new Card("S", 1));
		game.playerHand.add(new Card("D", 10));
		assertEquals(150, game.evaluateWinnings());
	}

	// Test for evaluating winnings for a win
	@Test
	void testEvaluateWinningsForWin() {
		game.playerHand.add(new Card("S", 10));
		game.playerHand.add(new Card("D", 8));
		assertEquals(100, game.evaluateWinnings());
	}
}