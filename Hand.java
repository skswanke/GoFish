import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by SKSWANKE on 12/2/2014.
 * This class controls the hand for players
 */
public class Hand implements CardPileInterface {

    private ArrayList<Card> hand;

    public Hand () {
       hand = new ArrayList<Card>(26);
    }

    @Override
    public void shuffle() {
        Collections.shuffle(hand);
    }

    @Override
    public void addCard(Card c) {
        hand.add(c);
    }

    @Override
    public Card drawCard() {
        Card c = hand.get(0);
        hand.remove(0);
        return c;
    }

    public int getSize() {
        return hand.size();
    }

    public Card getTopCard() {
        return hand.get(0);
    }
}
