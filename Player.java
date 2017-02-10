
import java.util.ArrayList;

/**
 * player class create a player
 *
 * @author neal
 */
public abstract class Player {

    private Hand hand = new Hand();
    private ArrayList<Integer> book = new ArrayList<>();

    public Player() {
    }

    /**
     * this method will draw a card from the pool 
     * and add to player's hand
     */
    public void goFish() {
        getHand().addCard(getHand().drawCard());
    }
    
    /**
     * this method will return the score base on 
     * how many books the player has
     * @return 
     */
    public int score(){
        return book.size() * 10;
    }
    
    /**
     * this method will ask their opponent to give them card
     */
    public abstract void getReply(Card c);
    
    /**
     * this method will respond to their opponent's request
     */
    public abstract void reply();

    /**
     * @return the hand
     */
    public Hand getHand() {
        return hand;
    }
}
