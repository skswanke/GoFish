class Player {
    private Hand hand;
    private ArrayList<Card> books = new ArrayList<Card>();
    public Player(Hand newHand) {
        hand = newHand;
    }
    public boolean getReply(Card requestedCard){}
    public String goFish(){
        Card c = Deck.drawCard()
        hand.addCard(c);
        return c.toString();
    }
    public reply(Card requestedCard){}
    public score(){
        return books.size();
    }
}
