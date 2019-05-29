package blackjack;

public class Deck{
	private int num_decks;
	public static final int FINAL_INDEX;


	public Deck(int decks){
		num_decks = decks;
		FINAL_INDEX = num_decks*52-1; 
	}

	public Card[] makeDeck(){
		Card[] deck = new Card[num_decks*52];
		int card = 1;
		String[] suit = {"Hearts", "Diamonds","Spades","Clubs"};
		for(int i = 0; i < deck.length; i++){
          deck[i]= new Card(card, suit[i % 4]);
          if(i%4==3 && card<13)
            card++;
          if(card == 13)
          	card = 1;
       }
       	return deck;
	}

	public Card[] Shuffle(Card[] deck){
 		for(int i=0; i<deck.length; i++){
   			int position = (int) Math.random * deck.length;
   			int temp = deck[i];
   			deck[i]= deck[position];
   			deck[position]=temp;
	}
	return deck;
}
}