package com.pokergame;

import com.sun.org.apache.xpath.internal.SourceTree;

import javax.xml.bind.SchemaOutputResolver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by kim on 7/7/2015.
 */
public class Table {
    private final int HAND_SIZE = 5;
    private int again = 1;
    private double moneyOnTheTable;


    // instantiate Deck and Player
    Scanner scan = new Scanner(System.in);
    Deck deck = new Deck();
    ArrayList<Player> players = new ArrayList<>();
    //Hand newHand = new Hand();
    //Card[] hand;


    // plays the game
    public void play() {
        while(true) {
            System.out.println("You can have up to five players.  How many players are playing?");
            int numberOfPlayers = scan.nextInt();
            if (numberOfPlayers <= 5 && numberOfPlayers > 1) {
                scan.nextLine();
                for (int i = 0; i < numberOfPlayers; i++) {
                    System.out.println("Input a name: ");
                    players.add(new Player(scan.nextLine()));
                }
                break;
            } else {
                System.out.println("Enter a valid number of players!");
                continue;
            }
        }
        while (again == 1) {
            // fill deck
            deck.fillDeck();

            // shuffle
            deck.shuffle();

            for(Player player : players) {
                player.cards = player.getHand().draw(deck);
                Arrays.sort(player.cards);
            }

            // player draws
            //hand = newHand.draw(deck);

            // sort hand
            // Arrays.sort(hand);

            // player redraws
            // checkHand();
           //hand = redraw();

            // display hand again
            // this.makeHand();
            checkHand();

            // sort hand
            // Arrays.sort(hand);

            // evaluate the hand
            //evaluate();
            //winningHand();
            judge();
            // play again?
            for (int i = 0; i < players.size(); i++) {
                System.out.println(players.get(i).getName() + "'s Bank Balance: " + players.get(i).getWallet().getBalance());
            }
            again();
        }
        System.out.println("Thanks for playing! =]");
    }

    // makes a hand for testing purpose
//    public void makeHand()
//    {
//        hand[0].rank = 1;
//        hand[1].rank = 2;
//        hand[2].rank = 3;
//        hand[3].rank = 4;
//        hand[4].rank = 5;
//
//        hand[0].suit = 1;
//        hand[1].suit = 1;
//        hand[2].suit = 1;
//        hand[3].suit = 1;
//        hand[4].suit = 1;
//    }

    // tells player cards in hand
    public void checkHand() {
        for (Player player : players) {
            System.out.println("Player name:  " + player.getName() + "  Betting Bank Balance: $" + player.getWallet().getBalance());
            for (int j = 0; j < 5; j++) {
                this.display(player.cards[j]);
            }
            moneyOnTheTable = moneyOnTheTable + player.bet();
            System.out.println("All bets final!  Winners take the pot!  The total in the pot is $" + moneyOnTheTable);
            evaluate(player);
        }
    }

    // asks if player wants to redraw
//    public Card[] redraw()
//    {
//        for (int counter = 0; counter < 5; counter++)
//        {
//            System.out.print("Redraw card " + (counter + 1) + "?" +
//                    " (1 for yes, 0 for no)");
//            int answer = scan.nextInt();
//            if (answer == 1)
//            {
//                hand[counter] = newHand.redraw(counter, deck);
//            }
//        }
    //deck.refreshDeckPosition();
    //return hand;
    //   }


    // evaluates the hand
    public void evaluate(Player player) {
        //for (Player player : players) {
            if (this.royalFlush(player) == 1) {
                System.out.println("You have a royal flush!");
            } else if (this.straightFlush(player) == 1) {
                System.out.println("You have a straight flush!");
            } else if (this.fourOfaKind(player) == 1) {
                System.out.println("You have four of a kind!");
            } else if (this.fullHouse(player) == 1) {
                System.out.println("You have a full house!");
            } else if (this.flush(player) == 1) {
                System.out.println("You have a flush!");
            } else if (this.straight(player) == 1) {
                System.out.println("You have a straight!");
            } else if (this.triple(player) == 1) {
                System.out.println("You have a triple!");
            } else if (this.twoPairs(player) == 1) {
                System.out.println("You have two pairs!");
            } else if (this.pair(player) == 1) {
                System.out.println("You have a pair!");
            } else {
                int highCard = this.highCard(player);
                System.out.println("Your highest card is " + highCard);
            }
        //}

    }

    // checks for a royal flush
    public int royalFlush(Player player) {
        if (player.cards[0].rank == 1 && player.cards[1].rank == 10 && player.cards[2].rank == 11 &&
                player.cards[3].rank == 12 && player.cards[4].rank == 13) {
            player.setValue(20);
            return 1;
        } else {
            return 0;
        }
    }

    // checks for a straight flush
    public int straightFlush(Player player) {
        for (int counter = 1; counter < 5; counter++) {
            if (player.cards[0].suit != player.cards[counter].suit) {
                return 0;
            }
        }
        for (int counter2 = 1; counter2 < 5; counter2++) {
            if (player.cards[counter2 - 1].rank != (player.cards[counter2].rank - 1)) {
                return 0;
            }

        }
        player.setValue(19);
        return 1;

    }

    // checks for four of a kind
    public int fourOfaKind(Player player) {
        if (player.cards[0].rank != player.cards[3].rank && player.cards[1].rank != player.cards[4].rank) {
            return 0;
        } else {
            player.setValue(18);
            return 1;
        }
    }

    // checks for full house
    public int fullHouse(Player player) {
        int comparison = 0;
        for (int counter = 1; counter < 5; counter++) {
            if (player.cards[counter - 1].rank == player.cards[counter].rank) {
                comparison++;
            }
        }
        if (comparison == 3) {
            player.setValue(17);
            return 1;
        } else {
            return 0;
        }
    }

    // checks for flush
    public int flush(Player player) {
        for (int counter = 1; counter < 5; counter++) {
            if (player.cards[0].suit != player.cards[counter].suit) {
                return 0;
            }
        }
        player.setValue(16);
        return 1;
    }

    // check for straight
    public int straight(Player player) {
        for (int counter2 = 1; counter2 < 5; counter2++) {
            if (player.cards[counter2 - 1].rank != (player.cards[counter2].rank - 1)) {
                return 0;
            }

        }
        player.setValue(15);
        return 1;
    }

    // checks for triple
    public int triple(Player player) {
        if (player.cards[0].rank == player.cards[2].rank || player.cards[2].rank == player.cards[4].rank) {
            player.setValue(14);
            return 1;
        }
        return 0;
    }

    // checks for two pairs
    public int twoPairs(Player player) {
        int check = 0;
        for (int counter = 1; counter < 5; counter++) {
            if (player.cards[counter - 1].rank == player.cards[counter].rank) {
                check++;
            }
        }
        if (check == 2) {
            player.setValue(13);
            return 1;
        } else {
            return 0;
        }
    }

    // check for pair
    public int pair(Player player) {
        int check = 0;
        for (int counter = 1; counter < 5; counter++) {
            if (player.cards[counter - 1].rank == player.cards[counter].rank) {
                check++;
            }
        }
        if (check == 1) {
            player.setValue(12);
            return 1;
        } else {
            return 0;
        }
    }

    // find highest card
    public int highCard(Player player) {
        int highCard = 0;
        for (int counter = 0; counter < 5; counter++) {
            if (player.cards[counter].rank > highCard) {
                highCard = player.cards[counter].rank;
            }
        }
        player.setValue(11);
        return highCard;
    }

    // asks user if they want to play again
    public void again() {
        System.out.print("Play again? (1 for yes, 0 for no)");
        again = scan.nextInt();
    }

    // generates string for each card in hand
    public void display(Card card) {
        if (card.rank == 1) {
            System.out.print("Ace of ");
        }
        if (card.rank == 2) {
            System.out.print("Two of ");
        }
        if (card.rank == 3) {
            System.out.print("Three of ");
        }
        if (card.rank == 4) {
            System.out.print("Four of ");
        }
        if (card.rank == 5) {
            System.out.print("Five of ");
        }
        if (card.rank == 6) {
            System.out.print("Six of ");
        }
        if (card.rank == 7) {
            System.out.print("Seven of ");
        }
        if (card.rank == 8) {
            System.out.print("Eight of ");
        }
        if (card.rank == 9) {
            System.out.print("Nine of ");
        }
        if (card.rank == 10) {
            System.out.print("Ten of ");
        }
        if (card.rank == 11) {
            System.out.print("Jack of ");
        }
        if (card.rank == 12) {
            System.out.print("Queen of ");
        }
        if (card.rank == 13) {
            System.out.print("King of ");
        }
        if (card.suit == 1) {
            System.out.print("Spades");
            System.out.println();
        }
        if (card.suit == 2) {
            System.out.print("Hearts");
            System.out.println();
        }
        if (card.suit == 3) {
            System.out.print("Diamonds");
            System.out.println();
        }
        if (card.suit == 4) {
            System.out.print("Clubs");
            System.out.println();
        }

    }
    public void judge() {
                    /*
            First, find out the list of players who have the same value;
            Second, compare the bigger card of each player in the list above
            Then, you have the winner, right?
             */
        // Below is the list of players who have the same and biggest value;
        // For example, player 1 20, player 2 20, player 3 10
        // Then the list is player1 and player2
        ArrayList<Player> biggestValue = new ArrayList<Player>();
        // Add the first player into the list
        // Because we assume the first player have a biggest value
        // And as a matter of fact, we just have seen one player, so he gotta have the biggest value
        biggestValue.add(players.get(0));
        // Iterate through the other players
        for (int i = 1; i < players.size(); i++) {
            // Challenge the biggest walue we thought we have
            if (biggestValue.get(0).getValue() < players.get(i).getValue()){
                // In this case, we are wrong, the players.get(i) has a bigger value
                // So we, first, empty the list, because they don't have the biggest value
                biggestValue.clear();
                // Then we put players.get(i) into the list, because it has the biggest value we have seen so far
                biggestValue.add(players.get(i));
            } else if (biggestValue.get(0).getValue() == players.get(i).getValue()) {
                // In this case, player i has the same value as the biggest value
                // So we want to add this player into the biggestValue player list
                biggestValue.add(players.get(i));
            } else {
                // This case means the player i's value is not bigger than our biggest value
                // Therefore, nothing should change
                // Because our assumption (players in the biggestValue list have the biggest value) is still correct
                // So I just do nothing!
            }
        }
        // After the for loop, we now have a list of players who definitely have the biggest value
        // So now we want to compare each player's biggest card to find out the winner
        // There comes another for loop for the biggestValue player list (not the players list)

        // Let's do the assumption again
        // We assume the first player has the biggest biggest card in his/her hand
        // Once again, as a matter of fact, that is the biggest card we have seen so far
        Player winner = biggestValue.get(0);
        for (int i = 1; i < biggestValue.size(); i++) {
            // Challenge our assume and change our assumption if it is wrong
            // We have be able to access the cards of a player
            // Because we want to get the player's biggest hand card
            // And I think you have sort the cards in each hand
            // OK, so according to the console, I thinkk the biggest card would be the fifth one
            // I forget again, that your cards are in the player rather than in the hand
            if (winner.cards[4].compareTo(biggestValue.get(i).cards[4]) < 0) {
                // Compare to function return a negative number when the latter card has a higher ranking
                // In this case our assumption is wrong
                // To make it right, make the winner as the latter person
                winner = biggestValue.get(i);
            }
        }
        // After the above for loop, the winner should be the player has the both biggest value
        // And his/her biggest card has the highest ranking among those who have the same biggest value
        System.out.println("The winner is: " + winner.getName());
        winner.getWallet().add(moneyOnTheTable);
    }
}
