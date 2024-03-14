// Rogelio Lozano and Pradyun Shrestha
// CS 342 - Software Design - Prof. McCarthy
// Project 2: Blackjack
// Description: Created a Blackjack game with 4 helper classes. Our UI goes through
//				the screens (Intro) -> (inital Money input) -> (round bet input) -> (main game)
//
//				The User repeats through (round bet input) and (main game) until the player
//				runs out of total money.


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class JavaFXTemplate extends Application {

	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Welcome to JavaFX");

		//bringing blackjack logic
		BlackjackGame game = new BlackjackGame();
		game.theDealer.generateDeck();
		game.theDealer.shuffleDeck();
		game.totalWinnings = 0;
		game.currentBet = 0;


		//////////////////////////////////// Game Intro Scene ////////////////////////////////////////////////
		// Create a label for the welcome message
		Label titleLabel = new Label("Welcome to Blackjack!");
		titleLabel.setFont(Font.font("Arial", 34)); // Set the font size and style
		titleLabel.setTextFill(Color.WHITE); // Set the text color
		titleLabel.setStyle("-fx-font-weight: bold;"); // Make the title bold

		// Create a button for starting the game, add nice styling
		Button playButton = new Button("Press to Play");
		playButton.setStyle("-fx-background-color: #6e0305; " +
				"-fx-text-fill: white; " +
				"-fx-font-size: 16px; " +
				"-fx-border-color: #000000; " +
				"-fx-border-width: 2px; " +
				"-fx-border-radius: 2px;");

		// Create a vertical box to hold the title label and play button
		VBox vBoxIntro = new VBox(20, titleLabel, playButton);
		vBoxIntro.setStyle("-fx-background: #1a4a09;");
		vBoxIntro.setAlignment(Pos.CENTER); // Center the vbox in the scene

		// Create the intro scene with the vbox as its root and set its dimensions
		Scene introScene = new Scene(vBoxIntro, 1500, 900);



		/////////////////////////////////////////////////////////////////////////////////////////////////////////



		//////////////////////////////////////////////(+)MAIN SCENE////////////////////////////////////////////////

		// Text fields
		TextField currBetText = new TextField();
		TextField totalMoneyText = new TextField();
		TextField dealerText = new TextField();
		TextField playerText = new TextField();

		// Other UI elements
		Text resultText = new Text("");
		resultText.setFont(Font.font(null, FontWeight.BOLD, 40));
		Label playerTitle = new Label("PLAYER"); // top left
		Label dealerTitle = new Label("DEALER"); // top Right

		// Buttons
		Button restartButton = new Button("New Round");
		Button hitButton = new Button("Hit");
		Button stayButton = new Button("Stay");
		Button dealButton = new Button("Deal");

		// Styling buttons
		restartButton.setStyle("-fx-background-color: #6e0305;-fx-text-fill: white; -fx-font-size: 16px;-fx-border-color: #000000; -fx-border-width: 2px; -fx-border-radius: 2px;");
		hitButton.setMinWidth(100);
		hitButton.setMaxWidth(100);
		hitButton.setStyle("-fx-background-color: #6e0305;-fx-text-fill: white; -fx-font-size: 16px;-fx-border-color: #000000; -fx-border-width: 2px; -fx-border-radius: 2px;");
		stayButton.setMinWidth(100);
		stayButton.setMaxWidth(100);
		stayButton.setStyle("-fx-background-color: #6e0305;-fx-text-fill: white; -fx-font-size: 16px;-fx-border-color: #000000; -fx-border-width: 2px; -fx-border-radius: 2px;");
		dealButton.setPrefHeight(25);
		dealButton.setMinWidth(100);
		dealButton.setMaxWidth(100);
		dealButton.setStyle("-fx-background-color: #6e0305;-fx-text-fill: white; -fx-font-size: 16px;-fx-border-color: #000000; -fx-border-width: 2px; -fx-border-radius: 2px;");

		// VBox holds buttons
		VBox leftButtons = new VBox(10,dealButton, hitButton,stayButton);
		leftButtons.setAlignment(Pos.CENTER);

		// Styling for labels
		playerTitle.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		dealerTitle.setFont(Font.font("Arial", FontWeight.BOLD, 40));

		// HBox  holding buttons, player hand, and dealer hand
		HBox playerAndDealer = new HBox(20,leftButtons,playerText,dealerText);

		// Create HBox with total Money in it
		Label totalMoneyLabel = new Label("Total Money:");
		totalMoneyLabel.setTextAlignment(TextAlignment.LEFT);
		HBox totalMoneyBox = new HBox(10, totalMoneyLabel, totalMoneyText);
		totalMoneyBox.setAlignment(Pos.CENTER);

		// Create HBox with current bet in it
		Label currBetLabel = new Label("Current Bet:");
		currBetLabel.setTextAlignment(TextAlignment.LEFT);
		HBox currBetBox = new HBox(10,currBetLabel, currBetText);
		currBetBox.setAlignment(Pos.CENTER);

		// Vbox holding both total Money and current bet, positioned at top of page
		VBox moneyBox = new VBox(10,totalMoneyBox, currBetBox);
		moneyBox.setAlignment(Pos.TOP_CENTER);
		moneyBox.setPrefWidth(300);
		moneyBox.setMaxWidth(400);

		// total money displayed on main screen
		totalMoneyText.setText("$"+game.totalWinnings);
		totalMoneyText.setPrefWidth(250);
		totalMoneyText.setPrefHeight(40);
		totalMoneyText.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 25px; -fx-pref-height: 50px;");

		// current Money displayed on main screen
		currBetText.setText("$"+game.currentBet);
		currBetText.setPrefWidth(250);
		currBetText.setPrefHeight(40);
		currBetText.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 25px; -fx-pref-height: 50px;");
		currBetText.setEditable(false);

		// dealer's text showing their cards
		dealerText.setMinWidth(400);
		dealerText.setMaxWidth(600);
		dealerText.setPrefHeight(100);
		dealerText.setPromptText("Dealer Hand");
		dealerText.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 50px; -fx-pref-height: 50px;");
		dealerText.setEditable(false);

		// players text showing their cards
		playerText.setPromptText("Player Hand");
		playerText.setMinWidth(400);
		playerText.setMaxWidth(600);
		playerText.setPrefHeight(100);
		playerText.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 50px; -fx-pref-height: 50px;");
		playerText.setEditable(false);


		//set player and dealer text
		playerTitle.setTextFill(Paint.valueOf("#0E86D4")); // changed from dark blue to lighter blue
		HBox playerTitleBox = new HBox(playerTitle);
		playerTitleBox.setAlignment(Pos.TOP_LEFT);
		dealerTitle.setTextFill(Paint.valueOf("#ff0011")); // changed from dark red to lighter red
		HBox dealerTitleBox = new HBox(dealerTitle);
		dealerTitleBox.setPrefWidth(300);
		dealerTitleBox.setAlignment(Pos.TOP_RIGHT);


		// Create StackPane to hold all objects
		StackPane root = new StackPane();
		root.getChildren().add(moneyBox);
		root.getChildren().add(dealerTitle);
		root.getChildren().add(playerTitle);
		root.getChildren().add(playerAndDealer);
		root.getChildren().add(resultText);
		root.getChildren().add(restartButton);
		playerAndDealer.setAlignment(Pos.CENTER);

		// align all items on the StackPane
		StackPane.setAlignment(playerTitle,Pos.TOP_LEFT);
		StackPane.setAlignment(dealerTitle,Pos.TOP_RIGHT);
		StackPane.setAlignment(playerAndDealer,Pos.CENTER);
		StackPane.setAlignment(moneyBox,Pos.TOP_CENTER);
		StackPane.setAlignment(dealButton,Pos.CENTER_RIGHT);
		StackPane.setAlignment(resultText,Pos.BOTTOM_CENTER);
		StackPane.setAlignment(restartButton,Pos.BOTTOM_RIGHT);

		root.setStyle("-fx-background: #1a4a09; -fx-padding: 50px;");

		Scene mainScene = new Scene(root,1500,900);
		primaryStage.setScene(introScene); //This is the first screen that shows up

		//////////////////////////////////////////(-)END OF MAIN SCENE/////////////////////////////////////////////////


		/////////////////////////////////////////////(+)SET ROUND BET////////////////////////////////////////////////////

		// UI elements for betting scene
		Label placeBetText = new Label("Set your bet for this round!");
		TextField newBetTextField = new TextField();
		Button submitBet = new Button("Submit");
		Label errorMessage = new Label("");

		// VBox to contain betting UI elements
		VBox bettingBox = new VBox(40,placeBetText,newBetTextField,submitBet,errorMessage);

		// Styling for UI elements
		placeBetText.setStyle(" -fx-font-size: 25px; ");
		newBetTextField.setPrefHeight(100);
		newBetTextField.setPrefWidth(250);
		newBetTextField.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 25px; -fx-pref-height: 50px;");
		submitBet.setStyle("-fx-background-color: #6e0305;-fx-text-fill: white; -fx-font-size: 16px;-fx-border-color: #000000; -fx-border-width: 2px; -fx-border-radius: 2px;");
		errorMessage.setStyle(" -fx-font-size: 25px; ");

		// StackPane to hold betting elements
		StackPane betRoot = new StackPane();
		betRoot.setStyle("-fx-background: #1a4a09; -fx-padding: 50px;");
		betRoot.getChildren().add(bettingBox);

		StackPane.setAlignment(bettingBox,Pos.CENTER);

		// Create betting scene
		Scene bettingScene = new Scene(betRoot,1500,900);

		submitBet.setOnAction(action -> {
			try {
				double betAmount = Double.parseDouble(newBetTextField.getText());
				if (betAmount < 0) {
					// Set error message if bet is negative
					errorMessage.setText("Error: Bet amount cannot be negative");
				}else if(betAmount > game.totalWinnings){
					errorMessage.setText("Error: Cannot bet more than your total money");
				} else {
					// Clear error message if bet is non-negative
					errorMessage.setText("");
					game.currentBet = betAmount;
					primaryStage.setScene(mainScene);
					currBetText.setText("$" + String.format("%.2f",game.currentBet));

					hitButton.setDisable(true);
					stayButton.setDisable(true);
					dealButton.setDisable(false);
					restartButton.setDisable(true);
				}
			} catch (NumberFormatException e) {
				// Set error message if input is not a number
				errorMessage.setText("Error: Invalid input. Please enter a number.");
			}
		});

		/////////////////////////////////////////////(-)SET ROUND BET////////////////////////////////////////////////////

		/////////////////////////////////////////////(+)SET TOTAL BET////////////////////////////////////////////////////
		// UI elements for setting total money in betting scene
		Label totalBetLabel = new Label("Set your total Money!");
		TextField totalBetTextField = new TextField();
		Button submitTotalBet = new Button("Submit");
		Label errorTotalMessage = new Label("");

		// VBox to contain total money UI elements
		VBox bettingTotalBox = new VBox(40,totalBetLabel,totalBetTextField,submitTotalBet,errorTotalMessage);

		// Styling for total money UI elements
		totalBetLabel.setStyle(" -fx-font-size: 25px; ");
		totalBetTextField.setPrefHeight(100);
		totalBetTextField.setPrefWidth(250);
		totalBetTextField.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 25px; -fx-pref-height: 50px;");
		submitTotalBet.setStyle("-fx-background-color: #6e0305;-fx-text-fill: white; -fx-font-size: 16px;-fx-border-color: #000000; -fx-border-width: 2px; -fx-border-radius: 2px;");
		errorTotalMessage.setStyle(" -fx-font-size: 25px; ");

		// StackPane to hold elements
		StackPane betTotalRoot = new StackPane();
		betTotalRoot.setStyle("-fx-background: #1a4a09; -fx-padding: 50px;");
		betTotalRoot.getChildren().add(bettingTotalBox);
		StackPane.setAlignment(bettingTotalBox,Pos.CENTER);

		// Create Initial bet scene
		Scene bettingTotalScene = new Scene(betTotalRoot,1500,900);

		submitTotalBet.setOnAction(action -> {

			try {
				double totalBetAmount = Double.parseDouble(totalBetTextField.getText());
				if (totalBetAmount <= 0) {
					// Set error message if bet is negative
					errorTotalMessage.setText("Error: Inital amount cannot be negative or 0");
				} else {
					// Clear error message if bet is non-negative
					errorMessage.setText("");
					game.totalWinnings = totalBetAmount;
					primaryStage.setScene(bettingScene);
					totalMoneyText.setText("$"+String.format("%.2f",game.totalWinnings));
				}
			} catch (NumberFormatException e) {
				// Set error message if input is not a number
				errorTotalMessage.setText("Error: Invalid input. Please enter a number.");
			}

		});

		/////////////////////////////////////////////(+)SET TOTAL BET////////////////////////////////////////////////////


		///////////////////////////////////////////(+)EVENT HANDLERS//////////////////////////////////////////////////////

		// Intro Screen play button
		playButton.setOnAction(action -> {
			primaryStage.setScene(bettingTotalScene);
		});

		// Button "Deal" deals cards, changing the proper UI elements, and checking for a black jack from the player
		dealButton.setOnAction(action -> {
			game.theDealer.generateDeck();
			game.theDealer.shuffleDeck();
			game.bankerHand.clear();
			game.playerHand.clear();
			game.playerHand.add(game.theDealer.drawOne());
			game.playerHand.add(game.theDealer.drawOne());
			game.bankerHand.add(game.theDealer.drawOne());
			game.bankerHand.add(game.theDealer.drawOne());
			playerText.setText(game.gameLogic.printHand(game.playerHand));
			dealerText.setText(game.gameLogic.printFirstHidden(game.bankerHand));
			hitButton.setDisable(false);
			stayButton.setDisable(false);
			dealButton.setDisable(true);

			if (game.gameLogic.handTotal(game.playerHand) == 21){

				resultText.setText("PLAYER: BLACKJACK!");
				dealerText.setText(game.gameLogic.printHand(game.bankerHand));
				hitButton.setDisable(true);
				stayButton.setDisable(true);
				restartButton.setDisable(false);
				game.totalWinnings += game.evaluateWinnings();
				totalMoneyText.setText("$" + String.format("%.2f",game.totalWinnings));

			}

		});

		// Restart Game button, brings us back to the round betting scene if our total money is not 0
		// BUT if we have 0 or negative money after the round, we start over all the way from the start screen
		restartButton.setOnAction(action -> {
			hitButton.setDisable(true);
			stayButton.setDisable(true);
			playerText.clear();
			dealerText.clear();

			resultText.setText("");
			if (game.totalWinnings <= 0){
				game.totalWinnings = 0;
				primaryStage.setScene(introScene);
			}else{
				primaryStage.setScene(bettingScene);
			}
		});


		//Hit button
		// contains logic, everytime that the player hits, we check if they got 21 or if they went over 21.
		hitButton.setOnAction(actionEvent -> {

			game.playerHand.add(game.theDealer.drawOne());
			String newHand = game.gameLogic.printHand(game.playerHand);
			playerText.setText(newHand);

			if (game.gameLogic.handTotal(game.playerHand) > 21){

				resultText.setText("PLAYER: BUST");
				dealerText.setText(game.gameLogic.printHand(game.bankerHand));
				hitButton.setDisable(true);
				stayButton.setDisable(true);
				restartButton.setDisable(false);
				game.totalWinnings += game.evaluateWinnings();
				totalMoneyText.setText("$" + String.format("%.2f",game.totalWinnings));

			}else if (game.gameLogic.handTotal(game.playerHand) == 21){

				resultText.setText("PLAYER: BLACKJACK!");
				dealerText.setText(game.gameLogic.printHand(game.bankerHand));
				hitButton.setDisable(true);
				stayButton.setDisable(true);
				restartButton.setDisable(false);
				game.totalWinnings += game.evaluateWinnings();
				totalMoneyText.setText("$" + String.format("%.2f",game.totalWinnings));

			}
		});

		// Stay button
		// Stay button runs logic for the banker to pull cards if the hand total is < 17
		// When the dealer stops pulling, if they don't bust, we compare the final values to see who wins
		stayButton.setOnAction(action -> {

			// Disable playing buttons, your turn is over
			hitButton.setDisable(true);
			stayButton.setDisable(true);

			boolean BankersTurn = true;
			boolean finalComparison = false;

			while(BankersTurn){

				// dealerTotal < 17 -> draw
				// dealerTotal >= 17 -> stop, and don't draw
				if (game.gameLogic.evaluateBankerDraw(game.bankerHand)){

					Card bankerDrawCard = game.theDealer.drawOne();
					game.bankerHand.add(bankerDrawCard);
					String newHand = game.gameLogic.printHand(game.bankerHand);
					dealerText.setText(newHand);

				}else{ // Stops loop, and goes to final comparison

					BankersTurn = false;
					finalComparison = true;

				}

				if (game.gameLogic.handTotal(game.bankerHand) > 21){

					BankersTurn = false;
					resultText.setText("DEALER: BUST");
					hitButton.setDisable(true);
					stayButton.setDisable(true);
					restartButton.setDisable(false);
					game.totalWinnings += game.evaluateWinnings();
					System.out.println("Total Winnings: " + game.totalWinnings);
					totalMoneyText.setText("$" + String.format("%.2f",game.totalWinnings));

				}else if (game.gameLogic.handTotal(game.bankerHand) == 21){

					BankersTurn = false;
					resultText.setText("DEALER: BLACKJACK!");
					hitButton.setDisable(true);
					stayButton.setDisable(true);
					restartButton.setDisable(false);
					game.totalWinnings += game.evaluateWinnings();
					totalMoneyText.setText("$" + String.format("%.2f",game.totalWinnings));

				}
			}

			dealerText.setText(game.gameLogic.printHand(game.bankerHand));

			// Only goes to final comparison if dealer doesn't need to draw anymore
			if (finalComparison){

				String theWinner = game.gameLogic.whoWon(game.playerHand,game.bankerHand);

				if (theWinner.equals("dealer")){ // Dealer wins

					game.gameLogic.printHand(game.playerHand);
					game.gameLogic.printHand(game.bankerHand);
					resultText.setText("DEALER WINS");
					hitButton.setDisable(true);
					stayButton.setDisable(true);
					restartButton.setDisable(false);
					game.totalWinnings -= game.currentBet;
					totalMoneyText.setText("$" + String.format("%.2f",game.totalWinnings));

				}else if (theWinner.equals("player")){ // Player wins

					resultText.setText("PLAYER WINS");
					hitButton.setDisable(true);
					stayButton.setDisable(true);
					restartButton.setDisable(false);
					game.totalWinnings += game.currentBet;
					totalMoneyText.setText("$" + String.format("%.2f",game.totalWinnings));

				}else{ //Draw

					game.gameLogic.printHand(game.playerHand);
					game.gameLogic.printHand(game.bankerHand);
					resultText.setText("DRAW");
					hitButton.setDisable(true);
					stayButton.setDisable(true);
					restartButton.setDisable(false);

				}
			}
		});

		////////////////////////////////////(-)EVENT HANDLERS//////////////////////////////////

		primaryStage.show();
	}



}
