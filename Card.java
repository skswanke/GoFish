as/*
* Sam Swanke
* CS 110
* Card Class
*/

public class Card {
    public static final int SPADES = 1;
    public static final int CLUBS = 2;
    public static final int HEARTS = 3;
    public static final int DIAMONDS = 4;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    public static final int ACE = 1;
    private int rank;
    private int suit;

    /**
     * Constructor takes rank and suit info
     * @param s
     * @param r
     */
    public Card (int s, int r) {
        if (0>s || 3<s) {
            rank = 2;
            suit = 1;
        }
        else{
            rank = r;
            suit = s;
        }
    }

    /**
     * returns the rank
     * @return
     */
    public int getRank () {
        return rank;
    }

    /**
     * returns the suit
     * @return
     */
    public int getSuit () {
        return suit;
    }

    /**
     * Returns a string version of the card
     * @return
     */
    public String toString () {
        String rString;
        String sString;
        switch (suit){
            case 1:
                sString = "s";
                break;
            case 2:
                sString = "c";
                break;
            case 3:
                sString = "h";
                break;
            case 4:
                sString = "d";
                break;
            default:
                sString = "Something is amiss";
        }
        switch (rank) {
            case 1:
                rString = "ace";
                break;
            case 11:
                rString = "jack";
                break;
            case 12:
                rString = "queen";
                break;
            case 13:
                rString = "king";
                break;
            default:
                rString = String.valueOf(rank);
        }
        return rString + sString;
    }

    /**
     * this method takes a card and compares the values to this one
     * @param c
     * @return
     */
    public boolean equals (Card c) {
        return (rank == (c.getRank())) && (suit == c.getSuit());
    }
}
