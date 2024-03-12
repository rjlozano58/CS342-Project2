import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXTemplate extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Welcome to JavaFX");
		int totalMoney = 0;
		int currMoney = 0;
		Button restartButton = new Button("Restart");

		//bringing blackjack logic
		BlackjackGame game = new BlackjackGame();
		game.theDealer.generateDeck();
		game.theDealer.shuffleDeck();




////////////////////////////////////////(+)PLAYER WIN SCENE////////////////////////////////////////////////

		TextField winText = new TextField();
		String winScore = "Player hand: " + game.gameLogic.handTotal(game.playerHand) +
				"\nDealer hand: " + game.gameLogic.handTotal(game.bankerHand)
				+ "\n";
		winText.setText(winScore);
		VBox vBoxPlayerWin = new VBox(50,winText,restartButton);
		Scene playerWinScreen = new Scene(vBoxPlayerWin,1000,1000);

//////////////////////////////////////(-)PLAYER WIN SCENE MADE///////////////////////////////////////////

////////////////////////////////////////////(+)START GAME SCENE////////////////////////////////////////////////

		TextField initaltotalMoney = new TextField();
		initaltotalMoney.setPromptText("How much will you like to bet?");
		Button setMoney = new Button("Set Money");
		VBox vBoxStart = new VBox(20,initaltotalMoney,setMoney);
		Scene startScreen = new Scene(vBoxStart,1000,1000);

///////////////////////////////////////////(-)START GAME SCENE////////////////////////////////////////////////

//////////////////////////////////////////////(+)MAIN SCENE////////////////////////////////////////////////

		TextField currMoneyText = new TextField();
		TextField dealerText = new TextField();
		TextField playerText = new TextField();
		Button hitButton = new Button("Hit");
		Button stayButton = new Button("Stay");
		Button seeResultsButton = new Button("See Results");
		VBox leftButtons = new VBox(10,hitButton,stayButton);
		Text resultText = new Text("");
		currMoneyText.setText("$"+currMoney);
		dealerText.setPrefWidth(250);
		dealerText.setPromptText("Player Hand");
		dealerText.setEditable(false);
		playerText.setPromptText("Dealer Hand");
		playerText.setPrefWidth(250);
		playerText.setEditable(false);
		resultText.setFont(Font.font(null, FontWeight.BOLD, 36));

		//set player and dealer text
		game.playerHand.add(game.theDealer.drawOne());
		game.playerHand.add(game.theDealer.drawOne());
		game.bankerHand.add(game.theDealer.drawOne());
		game.bankerHand.add(game.theDealer.drawOne());
		playerText.setText(game.gameLogic.printHand(game.playerHand));
		dealerText.setText(game.gameLogic.printFirstHidden(game.bankerHand));

		GridPane gridPane = new GridPane();

		gridPane.setMinSize(500, 500);

		gridPane.setPadding(new Insets(40, 40, 40, 40));

		gridPane.setHgap(10);

		gridPane.setAlignment(Pos.CENTER);
		gridPane.add(restartButton,1,3);
		gridPane.add(seeResultsButton,2,3);
		gridPane.add(leftButtons,0,1);
		gridPane.add(playerText,1,1);
		gridPane.add(dealerText,2,1);
		gridPane.add(resultText,1,2);
		gridPane.setStyle("-fx-background-color: #A50;");


		Scene mainScene = new Scene(gridPane,1000,1000);
		primaryStage.setScene(startScreen);

		//////////////////////////////////////////(-)END OF MAIN SCENE//////////////////////////////////////////////////


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

		//////Set Money button///////////////
		setMoney.setOnAction(action -> {
			primaryStage.setScene(mainScene);
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
