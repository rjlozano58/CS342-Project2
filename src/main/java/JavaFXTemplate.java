import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
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

	//feel free to remove the starter code from this method
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

		TextField currBetText = new TextField();
		currBetText.setDisable(true);
		TextField totalMoneyText = new TextField();
		totalMoneyText.setDisable(true);
		TextField dealerText = new TextField();
		TextField playerText = new TextField();
		Text resultText = new Text("");
		Label playerTitle = new Label("PLAYER"); // top left
		Label dealerTitle = new Label("DEALER"); // top Right

		Button restartButton = new Button("New Round");
		restartButton.setStyle("-fx-background-color: #6e0305;-fx-text-fill: white; -fx-font-size: 16px;-fx-border-color: #000000; -fx-border-width: 2px; -fx-border-radius: 2px;");
		Button hitButton = new Button("Hit");
		hitButton.setMinWidth(100);
		hitButton.setMaxWidth(100);
		hitButton.setStyle("-fx-background-color: #6e0305;-fx-text-fill: white; -fx-font-size: 16px;-fx-border-color: #000000; -fx-border-width: 2px; -fx-border-radius: 2px;");
		Button stayButton = new Button("Stay");
		stayButton.setMinWidth(100);
		stayButton.setMaxWidth(100);
		stayButton.setStyle("-fx-background-color: #6e0305;-fx-text-fill: white; -fx-font-size: 16px;-fx-border-color: #000000; -fx-border-width: 2px; -fx-border-radius: 2px;");
		Button dealButton = new Button("Deal");
		dealButton.setPrefHeight(25);
		dealButton.setMinWidth(100);
		dealButton.setMaxWidth(100);
		dealButton.setStyle("-fx-background-color: #6e0305;-fx-text-fill: white; -fx-font-size: 16px;-fx-border-color: #000000; -fx-border-width: 2px; -fx-border-radius: 2px;");
		VBox leftButtons = new VBox(10,dealButton, hitButton,stayButton);



		playerTitle.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		dealerTitle.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		leftButtons.setAlignment(Pos.CENTER);
		HBox playerAndDealer = new HBox(20,leftButtons,playerText,dealerText);


		Label totalMoneyLabel = new Label("Total Money:");
		totalMoneyLabel.setTextAlignment(TextAlignment.LEFT);
		HBox totalMoneyBox = new HBox(10, totalMoneyLabel, totalMoneyText);
		totalMoneyBox.setAlignment(Pos.CENTER);

		Label currBetLabel = new Label("Current Bet:");
		currBetLabel.setTextAlignment(TextAlignment.LEFT);
		HBox currBetBox = new HBox(10,currBetLabel, currBetText);
		currBetBox.setAlignment(Pos.CENTER);

		VBox moneyBox = new VBox(10,totalMoneyBox, currBetBox);
		moneyBox.setAlignment(Pos.TOP_CENTER);


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

		resultText.setFont(Font.font(null, FontWeight.BOLD, 36));

		//set player and dealer text
		playerTitle.setTextFill(Paint.valueOf("#0E86D4")); // changed from dark blue to lighter blue
		HBox playerTitleBox = new HBox(playerTitle);
		playerTitleBox.setAlignment(Pos.TOP_LEFT);
		dealerTitle.setTextFill(Paint.valueOf("#ff0011")); // changed from dark red to lighter red
		HBox dealerTitleBox = new HBox(dealerTitle);
		dealerTitleBox.setPrefWidth(300);
		dealerTitleBox.setAlignment(Pos.TOP_RIGHT);


		StackPane root = new StackPane();
		moneyBox.setPrefWidth(300);
		moneyBox.setMaxWidth(400);
		root.getChildren().add(moneyBox);
		root.getChildren().add(dealerTitle);
		root.getChildren().add(playerTitle);
		root.getChildren().add(playerAndDealer);
		root.getChildren().add(resultText);
		root.getChildren().add(restartButton);
		playerAndDealer.setAlignment(Pos.CENTER);

		StackPane.setAlignment(playerTitle,Pos.TOP_LEFT);
		StackPane.setAlignment(dealerTitle,Pos.TOP_RIGHT);
		StackPane.setAlignment(playerAndDealer,Pos.CENTER);
		StackPane.setAlignment(moneyBox,Pos.TOP_CENTER);
		StackPane.setAlignment(dealButton,Pos.CENTER_RIGHT);
		StackPane.setAlignment(resultText,Pos.BOTTOM_CENTER);
		StackPane.setAlignment(restartButton,Pos.BOTTOM_RIGHT);

		root.setStyle("-fx-background: #1a4a09; -fx-padding: 50px;");


		Scene mainScene = new Scene(root,1500,900);
		primaryStage.setScene(introScene);

		//////////////////////////////////////////(-)END OF MAIN SCENE/////////////////////////////////////////////////


		/////////////////////////////////////////////(+)SET ROUND BET////////////////////////////////////////////////////

		Label placeBetText = new Label("Set your bet for this round!");
		TextField newBetTextField = new TextField();
		Button submitBet = new Button("Submit");
		Label errorMessage = new Label("");
		VBox bettingBox = new VBox(40,placeBetText,newBetTextField,submitBet,errorMessage);

		placeBetText.setStyle(" -fx-font-size: 25px; ");
		newBetTextField.setPrefHeight(100);
		newBetTextField.setPrefWidth(250);
		newBetTextField.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 25px; -fx-pref-height: 50px;");
		submitBet.setStyle("-fx-background-color: #6e0305;-fx-text-fill: white; -fx-font-size: 16px;-fx-border-color: #000000; -fx-border-width: 2px; -fx-border-radius: 2px;");
		errorMessage.setStyle(" -fx-font-size: 25px; ");

		StackPane betRoot = new StackPane();
		betRoot.setStyle("-fx-background: #1a4a09; -fx-padding: 50px;");
		betRoot.getChildren().add(bettingBox);
		StackPane.setAlignment(bettingBox,Pos.CENTER);
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
					currBetText.setText("$" + String.format("%.2f",game.currentBet)); ///////

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
		Label totalBetLabel = new Label("Set your total Money!");
		TextField totalBetTextField = new TextField();
		Button submitTotalBet = new Button("Submit");
		Label errorTotalMessage = new Label("");
		VBox bettingTotalBox = new VBox(40,totalBetLabel,totalBetTextField,submitTotalBet,errorTotalMessage);

		totalBetLabel.setStyle(" -fx-font-size: 25px; ");
		totalBetTextField.setPrefHeight(100);
		totalBetTextField.setPrefWidth(250);
		totalBetTextField.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 25px; -fx-pref-height: 50px;");
		submitTotalBet.setStyle("-fx-background-color: #6e0305;-fx-text-fill: white; -fx-font-size: 16px;-fx-border-color: #000000; -fx-border-width: 2px; -fx-border-radius: 2px;");
		errorTotalMessage.setStyle(" -fx-font-size: 25px; ");

		StackPane betTotalRoot = new StackPane();
		betTotalRoot.setStyle("-fx-background: #1a4a09; -fx-padding: 50px;");
		betTotalRoot.getChildren().add(bettingTotalBox);
		StackPane.setAlignment(bettingTotalBox,Pos.CENTER);
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

		playButton.setOnAction(action -> {
			primaryStage.setScene(bettingTotalScene);
		});

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

		//////Restart Game button///////////////
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


		//////Hit button///////////////
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

		//////Stay button///////////////
		stayButton.setOnAction(action -> {
			hitButton.setDisable(true);
			stayButton.setDisable(true);

			boolean BankersTurn = true;
			boolean finalComparison = false;

			while(BankersTurn){
				if (game.gameLogic.evaluateBankerDraw(game.bankerHand)){

					Card bankerDrawCard = game.theDealer.drawOne();
					game.bankerHand.add(bankerDrawCard);
					String newHand = game.gameLogic.printHand(game.bankerHand);
					dealerText.setText(newHand);

				}else{

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

			if (finalComparison){

				String theWinner = game.gameLogic.whoWon(game.playerHand,game.bankerHand);

				if (theWinner.equals("dealer")){

					game.gameLogic.printHand(game.playerHand);
					game.gameLogic.printHand(game.bankerHand);
					resultText.setText("DEALER WINS");
					hitButton.setDisable(true);
					stayButton.setDisable(true);
					restartButton.setDisable(false);
					game.totalWinnings -= game.currentBet;
					totalMoneyText.setText("$" + String.format("%.2f",game.totalWinnings));

				}else if (theWinner.equals("player")){

					resultText.setText("PLAYER WINS");
					hitButton.setDisable(true);
					stayButton.setDisable(true);
					restartButton.setDisable(false);
					game.totalWinnings += game.currentBet;
					totalMoneyText.setText("$" + String.format("%.2f",game.totalWinnings));

				}else{

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
