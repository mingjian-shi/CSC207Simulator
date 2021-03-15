package com.example.csc207simulator.game1.GameOneBackend;

import java.util.Random;

/**
 * A class representing a BadBook. A BadBook gives negative knowledge.
 */
class BadBook extends Book {

    /**
     * Creates an instance of BadBook.
     */
    BadBook(){
        Random randInt = new Random();
        this.isBad = true;
        this.clicks = randInt.nextInt(3) + 1;
        //knowledge is determined based on the number of clicks needed to open the book
        this.knowledge = -this.clicks * 3;
    }
}
