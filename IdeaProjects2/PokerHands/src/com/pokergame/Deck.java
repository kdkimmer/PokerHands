package com.pokergame;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by kim on 7/7/2015.
 */
public class Deck {

        private final int DECK_SIZE = 52;
        private final int SHUFFLE = 2000;
        private final int HAND_SIZE = 5;
        public int nextCardInLine = 6;

        ArrayList<Card> deck = new ArrayList<>();
        Random random = new Random();

        // fill deck with cards
        public void fillDeck()
        {
            int counter = 0;
            for (int suit = 1; suit <= 4; suit++)
            {
                for (int rank = 1; rank <= 13; rank++)
                {
                    deck.add(new Card());
                    deck.get(counter).suit = suit;
                    deck.get(counter).rank = rank;
                    counter++;
                }
            }
        }

        // shuffle deck
        public void shuffle()
        {
            for (int x = 0; x <= SHUFFLE; x++)
            {
                int number1 = random.nextInt(DECK_SIZE);
                int number2 = random.nextInt(DECK_SIZE);
                Card temp = deck.get(number1);
               // deck.get(number1) = deck.get(number2);
                //deck.get(number2) = temp;
                deck.set(number1, deck.get(number2));
                deck.set(number2, temp);
            }
        }

        // deal 5 cards
        public Card[] deal()
        {
            Card[] hand = new Card[HAND_SIZE];
            for (int deckPosition = 0; deckPosition < 5; deckPosition++)
            {
                //hand[deckPosition] = deck[deckPosition];
                Card positionZero = deck.get(0);
                deck.remove(positionZero);
                hand[deckPosition] = positionZero;
            }
            return hand;
        }

    public ArrayList<Card> getDeck() {
        return deck;
    }
     //deals cards for redraw
        public Card redeal()

        {
            Card nextCard = deck.get(0);
            nextCardInLine++;
            return nextCard;
        }

        // refreshes deck position to 6 for next hand
        public void refreshDeckPosition()
        {
            nextCardInLine = 6;
        }

        //    ArrayList<Card> deck = new ArrayList<Card>();
//
//    public enum CARDVALUES {
//        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11),
//
// QUEEN(12), KING(13), ACE(14);
//        //this will never change so by keeping it private and final no one has access to it.
//        private final int cardValue;
//        //creating an instance of the value
//        CARDVALUES(int value) {
//            this.cardValue = value;
//        }
//        public int getCardValue() {
//            return cardValue;
//        }
//    }
//    public enum SUIT{
//        HEART,DIAMOND,CLUB,SPADE;
//    }
//    public Deck(){
//        for(CARDVALUES cardValues : CARDVALUES.values()){
//            for(SUIT suit : SUIT.values()){
//                deck.add(new Card(suit.name(),cardValues.getCardValue()));
//            }
//        }
//    }
//    public void printDeck(){
//        for(Card card : deck){
//            System.out.println(card.getSuit()+ " "+ card.getCardValue());
//        }
//
//    }
//
//    public ArrayList<Card> getDeck() {
//        return deck;
//    }


}

