
package blackjack;


public class Blackjack {

    
    public static void main(String[] args) {
        //Main method to play the game
        Game game = new Game(1);
        game.Play();
        System.out.println("the public card count: "+game.getCount());
    }
    
}
