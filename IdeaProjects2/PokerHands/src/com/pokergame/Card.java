package com.pokergame;

/**
 * Created by kim on 7/7/2015.
 */
public class Card implements Comparable<Card> {
    public int suit;
    public int rank;

    @Override
    public int compareTo(Card o)
    {
        if (this.rank == (o.rank))
            return 0;
        else if ((this.rank) > (o.rank))
            return 1;
        else
            return -1;
    }

//    private String suit;
//    private int cardValue;
//
//    public Card(String suit, int cardValue) {
//        this.suit = suit;
//        this.cardValue = cardValue;
//    }
//
//    public String getSuit() {
//        return suit;
//    }
//
//    public void setSuit(String suit) {
//        this.suit = suit;
//    }
//
//    public int getCardValue() {
//        return cardValue;
//    }
//
//    public void setCardValue(int cardValue) {
//        this.cardValue = cardValue;
//    }
}

