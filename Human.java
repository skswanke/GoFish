class Human extends Player {
    public Human(Hand newHand) {
        super(newHand);
    }

    public Deck turn(Deck deck){
        System.out.print("Your cards are: ");
        for(int i = 0; i<hand.size(); i++){
            System.out.print(hand[i].getRank().toString() + hand[i].getRank().toString() + ", ");
        }
        System.out.println("\nChoose a rank to ask for: ");
        Scanner scanner = new Scanner(System.in);
        String askRank = scanner.nextLine();
        aiCard = getReply(askRank);
        if(aiCard != null){
            hand.addCard(aiHasCard);
        } else {
            System.out.println("Sorry AI doesn't have that card! Go Fish!");
            cardRecieved = goFish();
            System.out.println("You got " + cardRecieved + "!");
        }
    }
}
