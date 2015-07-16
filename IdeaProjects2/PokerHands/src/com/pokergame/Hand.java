package com.pokergame;

/**
 * Created by kim on 7/7/2015.
 */
public class Hand {


    // gets 5 cards from deck
    public Card[] draw(Deck deck) {

        Card[] hand = deck.deal();
        return hand;
    }

//    // switches card for a new card
//    public Card redraw(int counter, Deck deck) {
//
//        Card card = deck.redeal();
//        return card;
//    }
}
