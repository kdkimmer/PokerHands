package com.pokergame.test;

import com.pokergame.Deck;
import junit.framework.TestCase;

/**
 * Created by kim on 7/7/2015.
 */
public class DeckTest extends TestCase {

    public void testNewDeck() {
        Deck testDeck = new Deck();
        assertEquals(52, 52);
    }
}