/*
* Sam Swanke
* CS 110
* Deck Class
*/

import java.util.ArrayList;
import java.util.Collections;

public class Deck implements CardPileInterface {
	private ArrayList<Card> deck = new ArrayList<Card>(52);

    public Deck() {
        for (int i=1;i<4+1;i++) {
            for (int j=1;j<13+1;j++) {
                deck.add(new Card(i,j));
            }
        }
        shuffle();
    }

    @Override
    public void shuffle() {
        Collections.shuffle(deck);
    }

    @Override
    public void addCard(Card c) {
        deck.add(c);
    }

    @Override
    public Card drawCard() {
        Card c = deck.get(0);
        deck.remove(0);
        return c;
    }

    public Hand dealHand() {
        Hand hand = new Hand();
        for (int i=0;i<26;i++) {
            hand.addCard(deck.get(0));
            deck.remove(0);
        }
        return hand;
    }

    public int getSize () {
        return deck.size();
    }
}
