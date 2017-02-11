/*
Go Fish main class
Go fish rules:
Five cards are dealt from a standard 52-card deck to each player, or seven cards if there are four or fewer players.
The remaining cards are shared between the players, usually spread out in a disorderly pile referred to as the "ocean"
or "pool".
The player whose turn it is to play asks another player for his or her cards of a particular face value. For example
Alice may ask, "Bob, do you have any threes?" Alice must have at least one card of the rank she requested. Bob must
hand over all cards of that rank if possible. If he has none, Bob tells Alice to "go fish" (or simply "fish"), and Alice
draws a card from the pool and places it in her own hand. Then it is the next player's turn – unless the card Alice drew
is the card she asked for, in which case she shows it to the other players, and she gets another turn. When any player
at any time has all four cards of one face value, it forms a book, and the cards must be placed face up in front of that
player.
Play proceeds to the left. When all sets of cards have been laid down in books, the game ends. The player with the
most books wins.
Possible classes:
Deck
Hand
Player
AI
Pool
*/
class GoFish {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Hand humanHand = deck.dealHand();
        Hand AIHand = deck.dealHand();
        Human player = new Human(humanHand, deck);
        int mode = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("What difficulty would you like to play at? (1,2,3): ");
        mode = scanner.nextInt();
        AI ai = new AI(AIHand, deck, mode);
        player.setOpponent(ai);
        ai.setOpponent(player);
        // true = player, false = ai
        boolean whichTurn = true;
        boolean isOver = false;
        deal(player, ai, deck);
        while(!isOver){
            if(whichTurn){
                player.turn();
            }
            else {
                ai.turn();
            }
            if(checkWin()){
                isOver = true;
            }
            whichTurn = !whichTurn;
        }

        findWinner(player.score(), ai.score());
        System.exit(0);
    }

    private static boolean checkWin(){
        // If all cards are in books there will be 13 books
        if(player.score() + ai.score() == 13) {
            return true;
        } else {
            return false;
        }
    }

    private static String findWinner(int pScore, int aScore){
        if(pScore>aScore){
            System.out.println("You win!");
        } else {
            System.out.println("You lose!");
        }
    }
}