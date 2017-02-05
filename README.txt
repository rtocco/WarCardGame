
In order to compile and run the program, have all of the files (War.java,
Player.java, Deck.java, and Card.java) in the same directory. In the shell,
navigate to that directory. Compile the program with

    javac War.java

and run it with

    java War

It should print out which player won (Player 1 or Player 2) and the number of
rounds played, not including instances of war.

If you run the program with the -v option,

    java War -v

then before every round it will print out the number of cards each player has
and the cards played by every player during that round. This will generate a
lot of output, as games can have many rounds occur.


In order to compile and run the tests program, put Tests.java in the
same directory as the rest of the files. Compile it with

    javac Tests.java

and run it with

    java Test

It should run tests, printing out what it's doing along the way. You should see
the word the word "Passed" multiple times printed in green.
