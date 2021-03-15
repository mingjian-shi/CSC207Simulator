package com.example.csc207simulator.game2.gametwobackend;

import java.util.Random;


/**
 * A kind of teacher, the knowledge giver with less knowledge in the game 2.
 */

public class TA extends Teacher {

    /**
     * The default constructor
     */
    TA() {
        super();
        Random randomInt = new Random();
        this.knowledge = randomInt.nextInt(50);
    }


    /**
     * the number of knowledge other Trader receives.
     *
     * @param playerKnowledge the other Trader.
     */
    public int giveKnowledge(int playerKnowledge) {
        // if the player guess the type of the teacher right
        if (this.isGuess) {
            Random randomInt = new Random();
            addKnowledge(randomInt.nextInt(Math.max(playerKnowledge,10))); //While trading the player, TA may do research
            if (this.knowledge >= 2 * playerKnowledge) {
                setChanged();
                notifyObservers(2 * this.knowledge);
                return randomInt.nextInt(2 * this.knowledge);
            }
        }
        return 0;
    }



}