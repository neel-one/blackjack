package blackjack;

public class Card{

	private int value;
	private String suit;
	private int truevalue;

	//values from 1 to 13; four suits
	public Card(int value, String suit){
		this.value=value;
		this.suit=suit;
		if(value == 1 )
			truevalue = 11;
		if(value > 10)
			truevalue = 10;
	}

	public void StringRepresentation(){
		if(value < 11 && value > 1)
			System.out.println(value + " of " +suit);
		else if(value == 1)
			System.out.println("Ace of " + suit);
		else if(value == 11)
			System.out.println("Jack of " + suit);
		else if(value == 12)
			System.out.println("Queen of " + suit);
		else if(value == 13)
			System.out.println("King of " + suit);

	}

	public int getTrueValue(){
		return truevalue;
	}

	public void Card_no_suit(){
		if(value < 11 && value > 1)
			System.out.println(value);
		else if(value == 1)
			System.out.println("Ace");
		else if(value == 11)
			System.out.println("Jack");
		else if(value == 12)
			System.out.println("Queen");
		else if(value == 13)
			System.out.println("King");
	}
}

	