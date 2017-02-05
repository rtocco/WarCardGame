import java.util.ArrayList;

// This class currently acts mainly as an interface for the player's deck.
// If more features were added to the game, it could be used
// for things like keeping track of the number of wins a player has.
public class Player {

    private Deck deck;

    public Player() {
        // Create an empty deck.
        deck = new Deck(false);
    }

    // The card is added to the top of the player's deck.
    public void receiveCard(Card card) {
        deck.addToTop(card);
    }

    // Add a group of cards to the bottom of the player's deck.
    public void receiveDeck(Deck bottomCards) {
        deck.addToBottom(bottomCards);
    }

    // Remove the top card and return it.
    public Card playTopCard() {
        Card topCard = deck.removeFromTop();
        return topCard;
    }

    // Returns the number of cards a player's deck has.
    public int getNumberOfCards() {
        return deck.getSize();
    }

}
