import java.util.ArrayList;
import java.util.Scanner;

public class Human extends Player {
    public Human(Hand newHand, Deck deck) {
        super(newHand, deck);
    }

    public void turn(Deck deck){
    
        //If you have no cards in hand, draw a card and pass turn.
        if(hand.getSize() == 0) {
            System.out.println("You have no cards. You draw, and then the opponent will go.");
            Card cardReceived = deck.drawCard();
            System.out.println("You got " + cardReceived + "!");
            hand.addCard(cardReceived);
         }    
         else {
            //Displays what cards are in your hand.
            System.out.print("Your cards are: " + hand.toString());
            
            //Gets the rank from the user
            System.out.println("\nChoose a rank to ask for: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
             
            while(!isInteger(input)) {
                System.out.println("Please enter a valid number.");
                input = scanner.nextLine();       
            }
             
            int askRank = Integer.parseInt(input);
             
             while(!hand.findRank(askRank) || askRank < 0 || askRank > 13 ) {
                 System.out.println("Please enter a valid card that you have in your hand.");
                 input = scanner.nextLine();
             }
             
             askRank = Integer.parseInt(input);
        
            //Requests the cards from the opponent. Not sure if this works with the methods in AI.
            ArrayList<Card> cardsReceived = opponent.reply(askRank);
            
            //If there were cards received, adds them to your hand.
            if(cardsReceived.size() != -1){
                for(Card c : cardsReceived){
                   hand.addCard(c);
                }
                
            //Otherwise, go fish (draw from the deck).
            } else {
                System.out.println("Sorry AI doesn't have that card! Go Fish!");
                Card cardReceived = deck.drawCard();
                System.out.println("You got " + cardReceived + "!");
                hand.addCard(cardReceived);
            }
            
            //Check for books and increment the books count.
            books += hand.placeBooks();
        }
        
        
    }
                  
    public boolean isInteger(String input) {
        
        boolean isInteger = true;
        for(char c : input.toCharArray()){
            if(!Character.isDigit(c)) {
                isInteger = false;
            }
        }
        
        try{
            Integer.parseInt(input);
        } catch(NumberFormatException e) {
            isInteger = false;
        }
       
        return isInteger;
       
    }
   
    //Replies to the opponent, returning cards that the opponent requested.
    public ArrayList reply(int rank) {
    
        ArrayList<Card> cardsTaken = new ArrayList<Card>();
        
        boolean found = false;
        
        //Checks through your hand to see if you have any cards of the rank the opponent requested.
        for(int i = 0; i < hand.getSize(); i++){
            if(hand.getCard(i).getRank() == rank){
               
               found = true;
             
               //Removes the wanted card from your hand and adds it to the ArrayList holding the wanted cards.
               Card temp = hand.getCard(i);
               hand.removeCard(hand.getCard(i));
               cardsTaken.add(temp);
            }
        }
        
        if(found == true) {
            System.out.println("Yes, I have some " + rank + "'s.");
        }
        else{
            System.out.println("Go fish.");
        }
      
        return cardsTaken;
    }
}
