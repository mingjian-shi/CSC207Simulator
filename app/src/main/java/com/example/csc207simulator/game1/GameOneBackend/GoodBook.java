package com.example.csc207simulator.game1.GameOneBackend;

import java.util.Random;

/**
 * A class representing a GoodBook. A GoodBook gives positive knowledge.
 */
class GoodBook extends Book {

    /**
     * Creates an instance of GoodBook.
     */
    GoodBook(){
        Random randInt = new Random();
        this.isBad = false;
        this.clicks = randInt.nextInt(9) + 3;
        //knowledge is determined based on the number of clicks needed to open the book
        this.knowledge = this.clicks * 2;
    }
}
