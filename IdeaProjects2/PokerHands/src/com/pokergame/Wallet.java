package com.pokergame;

import java.util.Scanner;

/**
 * Created by kim on 7/8/2015.
 */
public class Wallet {


    private double balance = 5000;

    //public Wallet() {
       // this.balance = 3500 + Math.round(100 * (Math.random() * 6500)) / 100.00;
   // }

    /*
    ToDo printWallet(), subtract(), add()
    */
    public double getBalance(){
        return this.balance;
    }

    public void add(double moneyToAdd){
        balance = moneyToAdd + balance;
    }

    public double subtract(double moneyToBet){
        if ((balance - moneyToBet) >= 0){
            balance = balance - moneyToBet;
            return moneyToBet;
        } else {
            System.out.println("Insufficient funds");
            return -1;
        }
    }

}


