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

import java.util.Stack;
import java.util.*;
public class JavaFXTemplate extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Welcome to JavaFX");

		Button restartButton = new Button("Restart");

		//bringing blackjack logic
		BlackjackGame game = new BlackjackGame();
		game.theDealer.generateDeck();
		game.theDealer.shuffleDeck();
		game.totalWinnings = 0;
		game.currentBet = 0;




////////////////////////////////////////(+)PLAYER WIN SCENE////////////////////////////////////////////////

		TextField winText = new TextField();
		String winScore = "Player hand: " + game.gameLogic.handTotal(game.playerHand) +
				"\nDealer hand: " + game.gameLogic.handTotal(game.bankerHand)
				+ "\n";
		winText.setText(winScore);
		VBox vBoxPlayerWin = new VBox(50,winText,restartButton);
		Scene playerWinScreen = new Scene(vBoxPlayerWin,1000,1000);

//////////////////////////////////////(-)PLAYER WIN SCENE MADE///////////////////////////////////////////

		//////////////////////////////////// Game Intro Scene ////////////////////////////////////////////////
		Label titleLabel = new Label("Welcome to Blackjack!"); // welcome msg
		titleLabel.setFont(Font.font("Times New Roman", 34)); // setting the font of the label
		titleLabel.setTextFill(Color.web("White")); // color of the label is white
		titleLabel.setStyle("-fx-font-weight: bold;"); // makes the title bold

		Button playButton = new Button("Press to Play"); // button to press to move on

		VBox vBoxIntro = new VBox(20, titleLabel, playButton);
		vBoxIntro.setStyle("-fx-background: #1a4a09;"); // makes the background dark green
		vBoxIntro.setAlignment(Pos.CENTER); // Centers the vbox

		Scene introScene = new Scene(vBoxIntro, 1000, 1000);



		/////////////////////////////////////////////////////////////////////////////////////////////////////////



		//////////////////////////////////////////////(+)MAIN SCENE////////////////////////////////////////////////

		TextField currBetText = new TextField();
		TextField totalMoneyText = new TextField();
		TextField dealerText = new TextField();
		TextField playerText = new TextField();
		Text resultText = new Text("");
		Label playerTitle = new Label("PLAYER"); // top left
		Label dealerTitle = new Label("DEALER"); // top Right

		Button hitButton = new Button("Hit");
		hitButton.setStyle("-fx-background-color: #6e0305;-fx-text-fill: white; -fx-font-size: 16px;-fx-border-color: #000000; -fx-border-width: 2px; -fx-border-radius: 2px;");
		hitButton.getStyleClass().add("styled-button");
		Button stayButton = new Button("Stay");
		stayButton.setStyle("-fx-background-color: #6e0305;-fx-text-fill: white; -fx-font-size: 16px;-fx-border-color: #000000; -fx-border-width: 2px; -fx-border-radius: 2px;");
		Button seeResultsButton = new Button("See Results");
		VBox leftButtons = new VBox(10,hitButton,stayButton);


		playerTitle.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		dealerTitle.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		leftButtons.setAlignment(Pos.CENTER);
		HBox playerAndDealer = new HBox(20,leftButtons,playerText,dealerText);
		VBox moneyBox = new VBox(10,totalMoneyText,currBetText);

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
		dealerText.setPrefWidth(400);
		dealerText.setPrefHeight(100);
		dealerText.setPromptText("Player Hand");
		dealerText.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 50px; -fx-pref-height: 50px;");
		dealerText.setEditable(false);

		// players text showing their cards
		playerText.setPromptText("Dealer Hand");
		playerText.setPrefWidth(400);
		playerText.setPrefHeight(100);
		playerText.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 50px; -fx-pref-height: 50px;");
		playerText.setEditable(false);

		resultText.setFont(Font.font(null, FontWeight.BOLD, 36));

		//set player and dealer text
		game.playerHand.add(game.theDealer.drawOne());
		game.playerHand.add(game.theDealer.drawOne());
		game.bankerHand.add(game.theDealer.drawOne());
		game.bankerHand.add(game.theDealer.drawOne());
		playerText.setText(game.gameLogic.printHand(game.playerHand));
		playerTitle.setTextFill(Paint.valueOf("#0005a1"));
		HBox playerTitleBox = new HBox(playerTitle);
		playerTitleBox.setAlignment(Pos.TOP_LEFT);
		dealerText.setText(game.gameLogic.printFirstHidden(game.bankerHand));
		dealerTitle.setTextFill(Paint.valueOf("#a1000b"));
		HBox dealerTitleBox = new HBox(dealerTitle);
		dealerTitleBox.setPrefWidth(300);
		dealerTitleBox.setAlignment(Pos.TOP_RIGHT);



		StackPane root = new StackPane();
//		moneyBox.setAlignment(Pos.TOP_CENTER);
		moneyBox.setPrefWidth(300);
		moneyBox.setMaxWidth(400);
		root.getChildren().add(moneyBox);
		root.getChildren().add(dealerTitle);
		root.getChildren().add(playerTitle);
		root.getChildren().add(playerAndDealer);
		playerAndDealer.setAlignment(Pos.CENTER);

		StackPane.setAlignment(playerTitle,Pos.TOP_LEFT);
		StackPane.setAlignment(dealerTitle,Pos.TOP_RIGHT);
		StackPane.setAlignment(playerAndDealer,Pos.CENTER);
		StackPane.setAlignment(moneyBox,Pos.TOP_CENTER);

		root.setStyle("-fx-background: #1a4a09; -fx-padding: 50px;");


		Scene mainScene = new Scene(root,1000,1000);
		primaryStage.setScene(introScene);

		//////////////////////////////////////////(-)END OF MAIN SCENE//////////////////////////////////////////////////

		/////////////////////////////////////////////(+)SET BET////////////////////////////////////////////////////

		Label placeBetText = new Label("Set your bet!");
		placeBetText.setStyle(" -fx-font-size: 25px; ");
		TextField newBetTextField = new TextField();
		newBetTextField.setPrefHeight(100);
		newBetTextField.setPrefWidth(250);
		newBetTextField.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 25px; -fx-pref-height: 50px;");
		Button submitBet = new Button("Submit");
		submitBet.setStyle("-fx-background-color: #6e0305;-fx-text-fill: white; -fx-font-size: 16px;-fx-border-color: #000000; -fx-border-width: 2px; -fx-border-radius: 2px;");
		Label errorMessage = new Label("");
		VBox bettingBox = new VBox(40,placeBetText,newBetTextField,submitBet,errorMessage);

		StackPane betRoot = new StackPane();
		betRoot.setStyle("-fx-background: #1a4a09; -fx-padding: 50px;");
		betRoot.getChildren().add(bettingBox);
		StackPane.setAlignment(bettingBox,Pos.CENTER);
		Scene bettingScene = new Scene(betRoot,800,800);

		submitBet.setOnAction(action -> {
			game.currentBet = Double.parseDouble(newBetTextField.getText());

			System.out.println("Number stored: " + game.currentBet);
			primaryStage.setScene(mainScene);
			currBetText.setText("$"+game.currentBet);
		});


		/////////////////////////////////////////////(-)SET BET////////////////////////////////////////////////////

		////////////////////////////////////////////GAMEOVER SCENE////////////////////////////////////////////////
		Text gameOverWinner = new Text();
		gameOverWinner.setText("Winner: " + game.gameLogic.whoWon(game.playerHand,game.bankerHand));
		Text gameOverScoresText = new Text();
		String gameOverScore= "Player hand: " + game.gameLogic.handTotal(game.playerHand) +
				"\nDealer hand: " + game.gameLogic.handTotal(game.bankerHand)
				+ "\n";
		gameOverScoresText.setText(gameOverScore);
		VBox v1 = new VBox(50,gameOverWinner,gameOverScoresText,restartButton);
		Scene gameOverScene = new Scene(v1,1000,1000);
		//////////////////////////////////////END OF GAMEOVER SCENE MADE///////////////////////////////////////////

		///////////////////////////////////////////(+)EVENT HANDLERS//////////////////////////////////////////////////////

		playButton.setOnAction(event -> {
			primaryStage.setScene(bettingScene);
//			primaryStage.setScene(betScene); // want it to move to the scene where you enter the betting info
		});

		//////Restart Game button///////////////
		restartButton.setOnAction(action -> {
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
			resultText.setText("");
			primaryStage.setScene(mainScene);
		});

		// See results button
		seeResultsButton.setOnAction(action -> {
			primaryStage.setScene(gameOverScene);
			gameOverWinner.setText("Winner: " + game.gameLogic.whoWon(game.playerHand,game.bankerHand));
			gameOverScoresText.setText("Player hand: " + game.gameLogic.handTotal(game.playerHand) +
					"\nDealer hand: " + game.gameLogic.handTotal(game.bankerHand)
					+ "\n");

		});



		//////Hit button///////////////
		hitButton.setOnAction(actionEvent -> {

			game.playerHand.add(game.theDealer.drawOne());
			String newHand = game.gameLogic.printHand(game.playerHand);
			playerText.setText(newHand);

			if (game.gameLogic.handTotal(game.playerHand) > 21){

				resultText.setText("PLAYER: BUST");
				hitButton.setDisable(true);
				stayButton.setDisable(true);

			}else if (game.gameLogic.handTotal(game.playerHand) == 21){

				resultText.setText("PLAYER: BLACKJACK!");
				hitButton.setDisable(true);
				stayButton.setDisable(true);

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

				}else if (game.gameLogic.handTotal(game.bankerHand) == 21){

					BankersTurn = false;
					resultText.setText("DEALER: BLACKJACK!");
					hitButton.setDisable(true);
					stayButton.setDisable(true);

				}
			}

			dealerText.setText(game.gameLogic.printHand(game.bankerHand));

			if (finalComparison){
				if (game.gameLogic.handTotal(game.bankerHand) > game.gameLogic.handTotal(game.playerHand)){

					game.gameLogic.printHand(game.playerHand);
					game.gameLogic.printHand(game.bankerHand);
					resultText.setText("DEALER WINS");
					hitButton.setDisable(true);
					stayButton.setDisable(true);

				}else if (game.gameLogic.handTotal(game.bankerHand) < game.gameLogic.handTotal(game.playerHand)){

					resultText.setText("PLAYER WINS");
					hitButton.setDisable(true);
					stayButton.setDisable(true);

				}else{

					game.gameLogic.printHand(game.playerHand);
					game.gameLogic.printHand(game.bankerHand);
					resultText.setText("DRAW");
					hitButton.setDisable(true);
					stayButton.setDisable(true);

				}
			}
		});



		////////////////////////////////////(-)EVENT HANDLERS//////////////////////////////////




		primaryStage.show();
	}


}
