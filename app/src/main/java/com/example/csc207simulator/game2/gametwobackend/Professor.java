package com.example.csc207simulator.game2.gametwobackend;

import java.util.Random;

/**
 * A kind of teacher, the knowledge giver with more knowledge in the game 2.
 */
public class Professor extends Teacher {

    /**
     * The default constructor
     */
    Professor() {
        super();
        Random randomInt = new Random();
        this.knowledge = randomInt.nextInt(200);
    }

    /**
     * the number of knowledge other Trader receives.
     *
     * @param playerKnowledge the other Trader.
     */
    public int giveKnowledge(int playerKnowledge) {
        // if the player guess the type of the teacher right
        if (this.isGuess) {
            addKnowledge(playerKnowledge); //While trading the player, Professor may do research
            if (this.knowledge >= 3 * playerKnowledge) {//If the professor has enough knowledge
                return this.knowledge;
            } else {
                return playerKnowledge;//If the professor has some knowledge
            }
        }
        return 0;
    }
}

