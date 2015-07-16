package com.pokergame;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Scanner;

/**
 * Created by kim on 7/7/2015.
 */
public class Player {
        private Wallet wallet = new Wallet();
        private Hand hand = new Hand();
        Card[] cards;
        private String name;
        private int value;

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Player(String name, Deck deck) {
            this.name = name;
            cards = hand.draw(deck);
        }
    public Player(String name) {
        this.name = name;
    }

        public double bet(){
            wallet.getBalance();
            System.out.println("How much would you like to bet?");
            Scanner scan = new Scanner(System.in);
//            wallet.subtract(scan.nextDouble());
            double betMoney = wallet.subtract(scan.nextDouble());
            while(betMoney <= 0){
                betMoney = wallet.subtract(scan.nextDouble());
            }
            return betMoney;
        }


    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    //    public Deck getDeck() {
//        return deck;
//    }
//
//    public void setDeck(Deck deck) {
//        this.deck = deck;
//    }
    //    private Wallet wallet;
//
//    public Player(Wallet wallet) {
//        this.wallet = wallet;
//    }
//
    public Wallet getWallet() {
        return wallet;
    }
//
//    public void setWallet(Wallet wallet) {
//        this.wallet = wallet;
//    }
}
