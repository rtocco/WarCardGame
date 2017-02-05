
// Running this program will test each of the individual classes
// as well as perform some test rounds of game play.
public class Tests {

    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        testCardClass();
        System.out.println("\n");
        testDeckClass();
        System.out.println("\n");
        testPlayerClass();
        System.out.println("\n");
        testGamePlay();
    }

    // Test the Card class.
    private static void testCardClass() {
        System.out.println("Card Class Tests:");

        System.out.println("\n\tCreating card 2 of Spades");
        Card numberCard = new Card("Spades", 2);

        System.out.print("\tNumber Card should have value 2: ");
        shouldBeEqual(numberCard.getValue(), 2);

        System.out.print("\tNumber Card should have suit Spades: ");
        shouldBeEqual(numberCard.getSuit(), "Spades");

        System.out.print("\tNumber Card should have name 2: ");
        shouldBeEqual(numberCard.getName(), "2");

        System.out.println("\n\tCreating card Queen of Hearts");
        Card faceCard = new Card("Hearts", 12);

        System.out.print("\tFace Card should have value 12: ");
        shouldBeEqual(faceCard.getValue(), 12);

        System.out.print("\tFace Card should have suit Hearts: ");
        shouldBeEqual(faceCard.getSuit(), "Hearts");

        System.out.print("\tFace Card should have name Queen: ");
        shouldBeEqual(faceCard.getName(), "Queen");
    }

    // Test the Deck class.
    private static void testDeckClass() {
        System.out.println("Deck Class Tests");

        System.out.println("\n\tCreating a main deck");
        Deck deck = new Deck(true);

        System.out.print("\tSize should be equal to 52: ");
        shouldBeEqual(deck.getSize(), 52);

        System.out.println("\n\tRemoving top card from deck");
        Card topCard = deck.removeFromTop();

        System.out.print("\tDeck should now have 51 cards: ");
        shouldBeEqual(deck.getSize(), 51);

        System.out.print("\tRemoved card should be and Ace: ");
        shouldBeEqual(topCard.getName(), "Ace");

        System.out.print("\tRemoved card should have suit Spades: ");
        shouldBeEqual(topCard.getSuit(), "Spades");

        System.out.println("\n\tAdding card back to deck");
        deck.addToTop(topCard);

        System.out.print("\tDeck should now have 52 cards again: ");
        shouldBeEqual(deck.getSize(), 52);

        System.out.println("\tShuffling deck");
        deck.shuffle();

        // Note that this test could fail even if the program works properly.
        // Randomness is somewhat difficult to quantify.
        System.out.print("\tDeck should exibit randomness: ");
        boolean random = true;
        String previousSuit = "";
        int previousValue = 0;
        int run = 0;

        // Search through deck and look for runs of cards with
        // the same suit and values close to each other.
        while(deck.getSize() > 0) {
            Card card = deck.removeFromTop();
            if(card.getSuit().equals(previousSuit)) {
                if((card.getValue() == previousValue + 1) || (card.getValue() == previousValue - 1))
                run++;
            }

            if(run > 6) {
                random = false;
                break;
            }

            previousSuit = card.getSuit();
            previousValue = card.getValue();
        }

        shouldBeTrue(random);
    }

    // Test the Player class. This class is reliant upon the Deck class, so
    // errors in this set of tests could have their root there.
    private static void testPlayerClass() {
        System.out.println("Player Class Tests");

        System.out.println("\n\tCreating a new player");
        Player player = new Player();

        System.out.print("\tPlayer should have no cards: ");
        shouldBeEqual(player.getNumberOfCards(), 0);

        System.out.println("\n\tGiving player two cards");
        Card card1 = new Card("Clubs", 4);
        Card card2 = new Card("Hearts", 12);
        player.receiveCard(card1);
        player.receiveCard(card2);

        System.out.print("\tTop card should have value 12: ");
        shouldBeEqual(player.playTopCard().getValue(), 12);

        System.out.println("\n\tAdding deck of 2 cards to bottom of player's deck");
        Deck deck = new Deck(false);
        Card deckCard1 = new Card("Diamonds", 2);
        Card deckCard2 = new Card("Spades", 3);
        deck.addToTop(deckCard1);
        deck.addToTop(deckCard2);
        player.receiveDeck(deck);

        System.out.print("\tPlayer should now have 3 cards: ");
        shouldBeEqual(player.getNumberOfCards(), 3);

        // Checking to make sure the cards were added to the bottom, not the top.
        System.out.print("\tThe top card in the player's deck should have value 4: ");
        shouldBeEqual(player.playTopCard().getValue(), 4);
    }

    // Test the game play functions found in War.java
    private static void testGamePlay() {
        System.out.println("Game Play Tests");

        // Test whether rounds of play work correctly

        System.out.println("\n\tCreating 2 players and giving them 4 cards each.");
        Player player1 = new Player();
        Player player2 = new Player();
        player1.receiveCard(new Card("Clubs", 9));
        player1.receiveCard(new Card("Clubs", 12));
        player1.receiveCard(new Card("Spades", 8));
        player1.receiveCard(new Card("Spades", 4));

        player2.receiveCard(new Card("Spades", 14));
        player2.receiveCard(new Card("Clubs", 10));
        player2.receiveCard(new Card("Hearts", 8));
        player2.receiveCard(new Card("Diamonds", 5));

        System.out.println("\tExecuting one round of game play. If all goes well player 2 will win.");
        War.playRound(player1, player2);

        System.out.print("\tPlayer 1 should now have 3 cards: ");
        shouldBeEqual(player1.getNumberOfCards(), 3);

        System.out.print("\tPlayer 2 should now have 5 cards: ");
        shouldBeEqual(player2.getNumberOfCards(), 5);

        System.out.println("\n\tExecuting another round. If all goes well it will go to war and player 2 will win.");
        War.playRound(player1, player2);

        System.out.print("\tPlayer 1 should now have 0 cards: ");
        shouldBeEqual(player1.getNumberOfCards(), 0);

        System.out.print("\tPlayer 2 should now have 8 cards: ");
        shouldBeEqual(player2.getNumberOfCards(), 8);

        System.out.println("\n\tCreating 2 new players and a full deck of cards");
        Player player3 = new Player();
        Player player4 = new Player();
        Deck deck = new Deck(true);

        // Test whether the dealCards method works properly.

        System.out.println("\n\tDealing the cards to the players");
        War.dealCards(deck, player3, player4);

        System.out.print("\tDeck should be empty: ");
        shouldBeEqual(deck.getSize(), 0);

        System.out.print("\tPlayer 1 should have 26 cards: ");
        shouldBeEqual(player3.getNumberOfCards(), 26);

        System.out.print("\tPlayer 2 should have 26 cards: ");
        shouldBeEqual(player4.getNumberOfCards(), 26);
    }

    // Check if two ints are equal.
    private static void shouldBeEqual(int a, int b) {
        if(a == b) {
            pass();
        } else {
            fail();
        }
    }

    // Check if two Strings are equal.
    private static void shouldBeEqual(String a, String b) {
        if(a.equals(b)) {
            pass();
        } else {
            fail();
        }
    }

    // Check if a value is true.
    private static void shouldBeTrue(boolean isTrue) {
        if(isTrue == true) {
            pass();
        } else {
            fail();
        }
    }

    // Print out the word Passed in green.
    private static void pass() {
        System.out.println(GREEN + "Passed" + RESET);
    }

    // Print out the word Failed in red.
    private static void fail() {
        System.out.println(RED + "Failed" + RESET);
    }
}
