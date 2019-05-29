
package blackjack;
import java.util.Scanner;

public class Money {
    private static int bank; //the money that the player gambles with, changes as wins hand or loses hands
    //we will assume that the table dealer does not run out of money
    
    //Note: there is an inital bet
    //After seeing 2 cards, player can 'double' which doubles the bet
    private int bet;
    public Money(){
        Scanner in = new Scanner(System.in);
        System.out.println("How much money are you bringing to the table?");
        bank = in.nextInt();
    }
    public void setBet(int b){
//The following methods are probably not the most efficient way to handle the gambling aspect of the game
        bet = b;
    }
    public void DoubleBet(){
        bet*=2;
    }
    public void updateBank(Boolean didplayerwin){
        if(didplayerwin)
            bank+=bet;
        else
            bank-=bet;
    }
    public int getBank(){
        return bank;
    }
    public Boolean DidPlayerWin(int deal_result, int my_result){
        if((deal_result>my_result && deal_result<22) || my_result>21)
           return false;
        else if(my_result==21)
            return true;
        else if(deal_result==my_result){
            setBet(0); //Wierd way of solving the problem when there is a push (tie game)
            return false;
        }
        return true;
    }
}
