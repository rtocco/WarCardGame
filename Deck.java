import java.util.ArrayList;

// This class represents not only the main deck but also any group of cards.
public class Deck {

    private ArrayList<Card> cards;

    // Initialize the deck.
    public Deck(boolean mainDeck) {

        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        cards = new ArrayList<Card>();

        // If this is the main deck, initialize it with the proper 52 cards.
        if(mainDeck == true) {
            // For each suit.
            for(int i = 0; i <= 3; i++) {
                // For each value of card with this suit.
                for(int j = 2; j <= 14; j++) {
                    Card card = new Card(suits[i], j);
                    cards.add(card);
                }
            }
        }
    }

    // Shuffle the deck by swapping cards at random locations.
    public void shuffle() {

        // The amount of swapping is proportional to the number of cards in the deck.
        for(int i = 0; i < (cards.size() * 2); i++) {

            // Produce two random numbers, representing the locations of cards to be swapped.
            int rand1 = (int)(Math.random() * cards.size());
            int rand2 = (int)(Math.random() * cards.size());

            // Swap the cards at the two specified positions.
            Card temp = cards.get(rand1);
            cards.set(rand1, cards.get(rand2));
            cards.set(rand2, temp);
        }
    }

    // Remove a card from the top of the deck and return that card.
    public Card removeFromTop() {
        Card card = cards.get(cards.size() - 1);
        cards.remove(cards.size() - 1);
        return card;
    }

    // Add a card to the top of the deck.
    public void addToTop(Card topCard) {
        cards.add(topCard);
    }

    // Add a group of cards to the bottom of a deck. Shuffle
    // the group of cards first to ensure randomness.
    public void addToBottom(Deck bottomCards) {
        bottomCards.shuffle();

        while(bottomCards.getSize() > 0) {
            cards.add(0, bottomCards.removeFromTop());
        }
    }

    // returns the current number of cards in a deck.
    public int getSize() {
        return cards.size();
    }

}
