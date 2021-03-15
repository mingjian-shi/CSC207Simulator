package com.example.csc207simulator.game1.GameOneBackend;

import java.util.Random;

/**
 * A class representing a BonusBook. A BonusBook gives more knowledge and requires less clicks to
 * open.
 */
class BonusBook extends Book {

    /**
     * Creates and instance of BonusBook.
     */
    BonusBook() {
        Random randInt = new Random();
        this.clicks = randInt.nextInt(8) + 2;
        this.isBad = false;
        //knowledge is determined based on the number of clicks needed to open the book
        this.knowledge = this.clicks * 5;
    }
}
