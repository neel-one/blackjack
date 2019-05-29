
package blackjack;
import java.util.Scanner;


public class Game { //this class creates a shuffled deck, calculates a count, and has the method to play the game among others
   private Card[] deck;
   private Money player;
   private int count;
   private int dealerhand; //made an array to account the two possibilities of scores if an ace is recieved
   private Card reveal; //what card dealer reveals
   private int myhand;
   private Boolean soft = false;
   private Boolean deal_soft = false; //probably not the best way I can do this... 
   private int index = 0; //index will update during the game so cards are not reused 
   //the game will stop when the player indicates OR when the index is too high (meaning the deck is running out of cards)
   public Game(int num_decks){ //create the full deck
       Deck d = new Deck(num_decks);
       deck = d.Shuffle(d.makeDeck());
       count=0;
       player= new Money();
   }
   public Card[] getDeck(){
       return deck;
   }

   public void setCount(Card card){
       if(card.getTrueValue() < 7)
           count++;
       if(card.getTrueValue() > 9)
           count--;
   }
   public int getCount(){
       return count;
   }
   public int setMyHand(){
       int hand = deck[index].getTrueValue() + deck[index + 1].getTrueValue();
       if(deck[index].getTrueValue() == 11 || deck[index+1].getTrueValue() == 11)
           soft = true;
       setCount(deck[index]); //sets card counts for player;
       setCount(deck[index+1]);
       System.out.println("Player shows " + deck[index]+" and "+deck[index+1]);
       index+=2;
       return hand;
   }
   public int setDealerHand(){//similar to setMyHand() but the count is only affected by the face up card
       int hand = deck[index].getTrueValue() + deck[index + 1].getTrueValue();
       
       if(deck[index].getTrueValue() == 11)
           deal_soft = true;
       if(deck[index+1].getTrueValue() == 11)
           deal_soft = true;
         //TODO add insurance option here
       setCount(deck[index+1]);
       System.out.print("Dealer shows a(n) ");
       deck[index+1].Card_no_suit();
       reveal = deck[index];
       index+=2;
       return hand;
}
   public void Reveal(){
       System.out.println("Dealer reveals ");
       reveal.Card_no_suit();
       setCount(reveal);
   }
   public int Hit(int hand){
       hand += deck[index].getTrueValue();
       if(deck[index].getTrueValue() == 11)
           soft = true;
       System.out.println("Hits ");
       deck[index].Card_no_suit();
       if(hand > 21 && !soft)
           System.out.println("Bust!");
       if(soft && hand > 31)
           System.out.println("Bust");
       if(soft && hand > 21){
           hand -= 10;
           soft = false;
       }
       setCount(deck[index]);
       index++;
       return hand;
   }
   public Boolean YesOrNo(){
       Scanner in = new Scanner(System.in);
       System.out.println("yes/no"); 
       String ans = in.nextLine();
       if(ans.charAt(0)=='y' || ans.charAt(0)=='Y')
           return true;
       return false;
   }
   public void Deal(){ //Only 1 dealer and 1 player for now; parameter may indicate the number of players
       Scanner in = new Scanner(System.in);
       System.out.println("Set initial bet");
       player.setBet(in.nextInt());
       myhand = setMyHand();
       Boolean blackjack = false;
       if(myhand == 21){
           blackjack = true;
           System.out.println("Blackjack!");
       }
       dealerhand = setDealerHand();
       if(!blackjack){
        System.out.println("Double?");
        if(YesOrNo())
            player.DoubleBet();
        System.out.println("Hit?");
        while(YesOrNo()){
            myhand = Hit(myhand);
        }
       }
       if(deal_soft)
           soft = true;
       while(dealerhand < 17 && !blackjack){
           System.out.println("Dealer hits");
           dealerhand= Hit(dealerhand);
   }
       Reveal();
       
       System.out.println("Dealer has "+ dealerhand);
       System.out.println("Player has "+ myhand);
       Boolean DidPlayerWin = player.DidPlayerWin(dealerhand, myhand);
       if((dealerhand>myhand && dealerhand<22) || myhand>21)
            System.out.println("Dealer wins!");
       else if(dealerhand == myhand)
           System.out.println("Tie!");
       else
           System.out.println("Player wins!");
       player.updateBank(DidPlayerWin);
       System.out.println("Bank = " + player.getBank());
}
   public void Play(){
       Deal();
       System.out.println("Play another round?");
       while(YesOrNo() && index < Deck.FINAL_INDEX){
           Deal();
           System.out.println("Play another round?");
   }
   }
}


