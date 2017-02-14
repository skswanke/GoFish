import java.util.*;
/**
 * This is a class created a computer player
 * @author yifanchang
 *
 */
public class AI extends Player{
	private Random rdm = new Random();
	private int count = 0;
	private int mode;
	private Set rankset = new HashSet();
	private Card newcard;  
	private int requestnew = 0;
	private Set history = new HashSet();
	public AI(Hand hand,Deck deck,int mode){
		super(hand,deck);
		this.mode = mode;
	}
	public void Turn(Deck deck){
		Card books = checkForBooks();
		ArrayList<Card> received = new ArrayList<Card>();
		received = null;
		if(books != null)
			System.out.println("Your opponent got a book of" + books +"s!");
		if(hand.getSize() == 0){
			System.out.println("Your opponent will draw a card from deck. Now it's your turn to go.");
			hand.addCard(deck.drawCard());
		}
		else{
			for(int i =0;i<hand.getSize();i++){
				rankset.add(hand.getCard(i).getRank());
			}
			switch(mode){
			case 1:
				int request1 = simple();
				System.out.println("Your opponent ask you a card named:"+request1);
				received = opponent.reply(request1); //call the reply from human player
				for(int k=0;k<received.size();k++){
					hand.addCard(received.get(k));
				}
				break;
			case 2:
				int request2 = strategy(requestnew);
				System.out.println("Your opponent ask you a card named:"+request2);
				received = opponent.reply(request2);
				for(int k=0;k<received.size();k++){
					hand.addCard(received.get(k));
				}
				break;
			case 3:
				int request3 = strategy(requestnew);
				System.out.println("Your opponent ask you a card named:"+request3);
				received = opponent.reply(request3);
				for(int k=0;k<received.size();k++){
					hand.addCard(received.get(k));
				}
				break;
			}
			if(received == null){
				System.out.println("Your opponent goes fishing now.");
				newcard = deck.drawCard();
				hand.addCard(newcard);
				for(int j = 0;j<rankset.size();j++){
					if(rankset.contains(newcard.getRank())==false)
						requestnew = newcard.getRank();
				}
			}

		}
	}
	/**
	 * This method is used to choose a card as a request in simple mode
	 * @return
	 */
	private int simple(){
		return hand.getCard(rdm.nextInt(hand.getSize())).getRank();
	}
	/**
	 * This method is used to choose a card as a request in strategy mode.
	 * If AI draws a card it does not have before, then it would request for 
	 * that card next. Otherwise, it will rotate among the ranks it already hold.
	 * @return
	 */
	private  int strategy(int requestnew) {
		int temp;
		int nextrequest;
		if(requestnew != 0){
			nextrequest = requestnew;
			history.add(requestnew);
			requestnew = 0;
			return nextrequest;
		}
		else{
			for(int i =0;i<hand.getSize();i++){
				temp = hand.getCard(i).getRank();
				if(history.contains(temp)==false){
					history.add(temp);
					return temp;
				}
			}
			history.clear();
			history.add(hand.getCard(0).getRank());	
			return hand.getCard(0).getRank();
		}

	}
	/**
	 * This method is used to check whether it should give human player the card or not
	 * @param rank is the rank requested by human player
	 * @return
	 */
	private boolean shouldGiven(int rank){
		for(int i=0;i<hand.getSize();i++){
			if(rank == hand.getCard(i).getRank()){
				return true;
				count++;
			}
		}
		return false;
	}
	/**
	 * This method is used to give all the card AI has requested by the human player
	 * @param rank is the rank requested by human player
	 * @return the card requested by human player

	public ArrayList<Card> giveCard(int rank){
		ArrayList<Card> given = new ArrayList<Card>();
		for(int i=0;i<hand.getSize();i++){
			if(hand.getCard(i).getRank()==rank){
				given.add(hand.getCard(i));
				hand.removeCard(hand.getCard(i));
			}
		}
		return given;
	}
	 */
	/**
	 * This method is used to reply the human player. In mode 1 and 2, it AI has that card requested
	 * by human player, it will return that card(s). In mode 3, AI will lie every third time that it
	 * could actually satisfy a request for card(s) of the requested rank and instead say "Go fish"
	 * @param rank is the rank requested by human player
	 * @return
	 */
	public ArrayList<Card> reply(int rank){
		ArrayList<Card> given = new ArrayList<Card>();
		if(mode !=3){
			if(shouldGiven(rank)){
				for(int i=0;i<hand.getSize();i++){
					if(hand.getCard(i).getRank()==rank){
						given.add(hand.getCard(i));
						hand.removeCard(hand.getCard(i));
					}
				}
				return given;
			}
			else{
				System.out.print("Go Fish!");
			}
		}
		if(mode == 3){
			if(shouldGiven(rank) && (count % 3)!=0){
				for(int i=0;i<hand.getSize();i++){
					if(hand.getCard(i).getRank()==rank){
						given.add(hand.getCard(i));
						hand.removeCard(hand.getCard(i));
					}
				}
				return given;
			}

			else
				System.out.print("Go fish!");
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
						hand.removeCard(c);
					}
					return c;
				}
			}
		}
	}

}

