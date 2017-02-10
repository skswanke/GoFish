import java.util.*;
/**
 * This is a class created a computer player
 * @author yifanchang
 *
 */
 class AI extends Player{
	public ArrayList<Card> history = new ArrayList<Card>();
	Random rdm = new Random();
    int count = 0;
    boolean playing;
	public void Turn(int mode){
		
		do{
			Card books = checkForBooks();
			if(books != null)
				System.out.println("Your opponent got a book of" + books +"s!");
			if(hand.size == 0){
				System.out.println("Your opponent's hand is empty.");
				break;
			}
			switch(mode){
			case 0:
				Card request0 = simple();
				System.out.println("Your opponent ask you a card named:"+request);
				playing = Human.getReply(request); //call the reply from human player
				break;
			case 1:
				Card request1 = strategy();
				System.out.println("Your opponent ask you a card named:"+request);
				playing = Human.getRreply(request);
				break;
			case 2:
				Card request2 = strategy();
				System.out.println("Your opponent ask you a card named:"+request);
				playing = Human.getReply(request);
				break;
			}	
			
		}while(playing);
		System.out.println("Your opponent goes fishing now.");
		goFish();
		
	}
	/**
	 * This method is used to choose a card as a request in simple mode
	 * @return
	 */
	private Card simple(){
		return hand.get(rdm.nextInt(hand.size()));
	}
	/**
	 * This method is used to choose a card as a request in strategy mode.
	 * It keeps the history of the human player's and first check the history, if AI has that 
	 * card, then request that card, if it does not have that card, it request the card it gets from the 
	 * deck first.
	 * @return
	 */
	private  Card strategy() {
	    for(int i=history.size;i>-1;i--){
	    	if (hand.contains(history.get(i))){
	    		return history.remove(i);
	    	}
	    }
		return hand.get(hand.size()-1);

	}
    /**
     * This method is used to check whether it should give human player the card or not
     * @param c is the card requested by human player
     * @return
     */
	public boolean shouldGiven(Card c){
		return hand.contains(c);
		count ++;
	}
	/**
	 * This method is used to give all the card AI has requested by the human player
	 * @param c is the card requested by human player
	 * @return
	 */
	public ArrayList<Card> giveCard(Card c){
		ArrayList<Card> given = new ArrayList<Card>();
		for(int i=0;i<hand.size();i++){
			if(hand.get(i)==c)
				given.add(hand.get(i));
		}
		for(int j=0;j<given.size();j++){
			hand.remove(c);
		}
		return given;
	}
	/**
	 * This method is used to reply the human player. When it returns true, it means that AI has that card 
	 * then human could request a new rank again, if it returns false, then the human palyer need to go gish.
	 * @param c is the card requested by human player
	 * @return
	 */
	public boolean reply(Card c){
		if(shouldGiven){
			giveCard(c);
			return true;
			history.add(c);
		}
		else{
			return false;
			System.out.print("Go Fish!");
		}
	}
	/**
	 * This is a method used to let AI in devious mode, AI will lie every third time 
	 * that it could actually satisfy a request for card(s) of the requested rank and 
	 * instead say "Go Fish".
	 * @param c
	 * @return
	 */
	public boolean deviousReply(Card c){
		if(shouldGiven && (count % 3)!=0){
			giveCard(c);
			return true;
			history.add(c);
		}
		else{
			return false;
			System.out.print("Go Fish!");
		}
		
	}
	public Card checkForBooks(){
		for(Card c: hand){
			int numCard = 0;
			for(Card d:hand){
				if(c==d)
					numCard++;
				if(numCard == 4){
					for(int i=0;i<4;i++){
						hand.remove(c);
					}
					numBooks ++;
					return c;
				}
			}
		}
	}

}
