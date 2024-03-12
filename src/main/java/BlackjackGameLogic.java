import java.util.ArrayList;

public class BlackjackGameLogic {


    public String whoWon(ArrayList<Card> playerHand1,ArrayList<Card> dealerHand){
        int playerTotal = handTotal(playerHand1);
        int bankerTotal = handTotal(dealerHand);

        if (( bankerTotal <= 21 && playerTotal <= 21) && (playerTotal == bankerTotal)){
            return "draw";
        }else if (( bankerTotal <= 21 && playerTotal <= 21) && (playerTotal > bankerTotal)){
            return "player";
        }else if (( bankerTotal <= 21 && playerTotal <= 21) && (playerTotal < bankerTotal)){
            return "banker";
        }

        return "";
    }

    public int handTotal(ArrayList<Card> hand){
        int totalAceAs1 = 0;
        int totalAceAs11 = 0;

        for (int i = 0; i < hand.size();i++){
            totalAceAs1 += hand.get(i).value;
        }
        for (int i = 0; i < hand.size();i++){
            if (hand.get(i).value == 1){
                totalAceAs11 += 11;
            }else{
                totalAceAs11 += hand.get(i).value;
            }
        }

        if (totalAceAs11 <= 21){
            return totalAceAs11;
        }else{
            return totalAceAs1;
        }
    }

    public boolean evaluateBankerDraw(ArrayList<Card> hand){
        int bankerTotal = handTotal(hand);
        if (bankerTotal <= 16) {
            return true;
        }else{
            return false;
        }
    }

    public String printHand(ArrayList<Card> hand){
        String returnString = "";
        for (int i = 0; i < hand.size();i++){
            returnString += hand.get(i).suit + hand.get(i).value + " ";
        }
        return returnString;
    }
    public String printFirstHidden(ArrayList<Card> hand){
        String returnString = "";
        for (int i = 0; i < hand.size();i++){
            if (i == 0){
                returnString += "X ";
            }else{
                returnString += hand.get(i).suit + hand.get(i).value + " ";
            }
        }
        return returnString;
    }
}
