
// This class represents one card, with a suit and value.
public class Card {

    static final String[] faceCards = { "Jack", "Queen", "King", "Ace" };
    private String suit;
    private int value;

    public Card(String suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    // Returns the suit of the card.
    public String getSuit() {
        return suit;
    }

    // Returns the name of the card (i.e. 2, 3, Jack, Ace)
    public String getName() {
        if(value <= 10) {
            return "" + value;
        } else {
            return faceCards[value - 11];
        }
    }

    // Return the value of the card as a number (11 for Jack, 12 for Queen, etc.)
    public int getValue() {
        return value;
    }

    // Print the name and suit of a card.
    public void print() {
        System.out.println(getName() + " of " + suit);
    }
}
