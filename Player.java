
import java.util.ArrayList;

/**
 * player class create a player
 *
 * @author neal
 */
public abstract class Player {

    public Hand hand;
    public Deck deck;
    int books; 
    public Player opponent;
    
    public Player(Hand hand, Deck deck) {
        this.hand = hand;
        this.deck = deck;
    }
    
    public abstract void turn(Deck deck);
    
    /**
     * this method will return the score base on 
     * how many books the player has
     * @return 
     */
    public int score(){
        return books * 10;
    }

    /**
     * this method will respond to their opponent's request
     */
    public abstract ArrayList reply(int rank);
    
    public void setOpponent(Player opponent){
        this.opponent = opponent;
    }
}
