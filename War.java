
// Running this program simulates one game of war.
public class War {

    static boolean verbose;

    public static void main(String[] args) {

        // See if the verbose option was specified.
        parseOptions(args);

        // Create and shuffle the main deck.
        Deck mainDeck = new Deck(true);
        mainDeck.shuffle();

        // Create the players that will be playing the game.
        Player player1 = new Player();
        Player player2 = new Player();

        // Deal the cards to the two players.
        dealCards(mainDeck, player1, player2);

        // Continue playing rounds until one player is out of cards.
        int numberOfRounds = 0;
        while(player1.getNumberOfCards() > 0 && player2.getNumberOfCards() > 0) {
            if(verbose == true) {
                System.out.print("\nPlayer 1 has " + player1.getNumberOfCards());
                System.out.println(" cards, Player 2 has " + player2.getNumberOfCards() + " cards");
            }
            playRound(player1, player2);
            numberOfRounds++;
        }

        // The winner is the player who still has cards.
        String winner = (player1.getNumberOfCards() == 0) ? "Player 2" : "Player 1";
        System.out.println("\n" + winner + " wins!!!");
        System.out.println("Number of rounds: " + numberOfRounds);
    }

    // Parse command line options to see if the verbose option was specified.
    static void parseOptions(String[] args) {
        verbose = false;

        for(int i = 0; i < args.length; i++) {
            if(args[i].equals("-v")) {
                verbose = true;
            }
        }
    }

    // Deal the deck out to the players.
    static void dealCards(Deck mainDeck, Player player1, Player player2) {
        while(mainDeck.getSize() > 0) {
            Card card1 = mainDeck.removeFromTop();
            Card card2 = mainDeck.removeFromTop();

            player1.receiveCard(card1);
            player2.receiveCard(card2);
        }
    }

    // Both players play a card. Move to war if cards are equal in value.
    static void playRound(Player player1, Player player2) {
        Card card1 = player1.playTopCard();
        Card card2 = player2.playTopCard();

        if(verbose == true) {
            printCards(card1, card2);
        }

        if(card1.getValue() > card2.getValue()) { // Player 1 wins round.
            Deck deck = new Deck(false);
            deck.addToTop(card1);
            deck.addToTop(card2);
            player1.receiveDeck(deck);

        } else if(card2.getValue() > card1.getValue()) { // Player 2 wins round.
            Deck deck = new Deck(false);
            deck.addToTop(card1);
            deck.addToTop(card2);
            player2.receiveDeck(deck);

        } else { // Players tie, go to war.

            // These decks will contain the cards that players 1
            // and 2, respectively, have laid down this round.
            Deck deck1 = new Deck(false);
            Deck deck2 = new Deck(false);
            deck1.addToTop(card1);
            deck2.addToTop(card2);

            war(player1, player2, deck1, deck2);
        }
    }

    // Carry out rounds of war until one player either wins or runs out of cards.
    static void war(Player player1, Player player2, Deck deck1, Deck deck2) {

        // If a player doesn't have enough cards to continue, we return all of
        // the cards each player has laid down to the bottom of their decks.
        if(player1.getNumberOfCards() < 2 || player2.getNumberOfCards() < 2) {
            player1.receiveDeck(deck1);
            player2.receiveDeck(deck2);

        } else {

            // Each player plays one card face down.
            Card faceDown1 = player1.playTopCard();
            Card faceDown2 = player2.playTopCard();
            deck1.addToTop(faceDown1);
            deck2.addToTop(faceDown2);

            // Then each player plays one card face up.
            Card faceUp1 = player1.playTopCard();
            Card faceUp2 = player2.playTopCard();
            deck1.addToTop(faceUp1);
            deck2.addToTop(faceUp2);

            if(verbose == true) {
                System.out.println("War:");
                printCards(faceUp1, faceUp2);
            }

            if(faceUp1.getValue() > faceUp2.getValue()) { // Player 1 wins round.
                player1.receiveDeck(deck1);
                player1.receiveDeck(deck2);

            } else if(faceUp2.getValue() > faceUp1.getValue()) { // Player 2 wins round.
                player2.receiveDeck(deck1);
                player2.receiveDeck(deck2);

            } else { // Players tie, go to war again.
                war(player1, player2, deck1, deck2);
            }
        }
    }

    // Print the cards played by each player.
    static void printCards(Card card1, Card card2) {
        System.out.print("Player 1 plays ");
        card1.print();
        System.out.print("Player 2 plays ");
        card2.print();
    }
}
