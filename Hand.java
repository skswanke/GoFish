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
    
    //This constructor makes a Hand using an ArrayList of existing Cards that are passed in.
    public Hand (ArrayList<Card> cards) {
      
      hand = new ArrayList<Card>();
      
      for(int i = 0; i < cards.size(); i++) {
         hand.add(cards.get(i));
      }
    
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
    
    public void removeCard(Card c) {
      hand.remove(c);
    }

    public int getSize() {
        return hand.size();
    }

    public Card getTopCard() {
        return hand.get(0);
    }
    
    
    //This method checks the player's hand for any books, then returns an int that indicates how many books the player has.
    //It also removes those books from the player's hand.
    public int placeBooks() {
    
      //Creates an int to indicate how many books the player has.
      int books = 0;
      
      //Creates an array to keep track of what ranks the player has 4 of.
      int[] bookCount = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      
      
      //For each rank of card, from 1 to 13,
      for(int i = 1; i <= 13; i++) {  
      
         //Iterates through the player's hand and finds which cards have that rank.
         for(int j = 0; j < hand.size(); j++) {
         
            if(hand.get(j).getRank() == i) {
              //Adds 1 to the count for that rank.
              bookCount[i]++;
            
            }
         } 
      }
      
      //For each rank in the array keeping track of how many cards of that rank there are, 
      for(int bookRank = 0; bookRank < bookCount.length; bookRank++) {
      
         //If there are 4 cards of that rank, adds that book to the ArrayList holding which books are being placed.
         if(bookCount[bookRank] == 4) {
         
            books++;
            
            //Then, iterates through the players hand, and removes all the cards in that book.
            for(int k = 0; k < hand.size(); k++) {
            
               if(hand.get(k).getRank() == bookRank + 1) {
               
                  hand.remove(hand.get(k));
               }
            }
         }
      
      }
      
      //Returns what ranks the player now has books of. 
      return books;
    
    }
    
    public String toString () {
    
        String cardString = new String();
        for(Card c : hand) {
            
            cardString += c.toString() + ",";
            
        } 
        
        return cardString; 
        
    }
    
    
}
