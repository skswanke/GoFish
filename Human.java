class Human extends Player {
    public Human(Hand newHand, Deck deck) {
        super(newHand, deck);
    }

    public void turn(){
        System.out.print("Your cards are: " + hand.toString());
        System.out.println("\nChoose a rank to ask for: ");
        Scanner scanner = new Scanner(System.in);
        String askRank = scanner.nextLine();
        Card aiCard = null;
        Card aiCard = getReply(askRank);
        if(aiCard.getRank() != -1){
            hand.addCard(aiHasCard);
        } else {
            System.out.println("Sorry AI doesn't have that card! Go Fish!");
            cardRecieved = goFish();
            System.out.println("You got " + cardRecieved + "!");
        }
    }
    @override
    private Card getReply(Card c){
        return AI.reply();
    }
    @override
    public reply(Card c) {
        System.out.println("Do you have a " + c.toString() + "?");

    }
}