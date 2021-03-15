package com.example.csc207simulator.game2.gametwobackend;

import com.example.csc207simulator.Trader;

import java.util.Observable;
import java.util.Random;

/**
 * A teacher, the knowledge giver in the game 2.
 */
public class Teacher extends Observable {

    /**
     * The number of knowledge the teacher has.
     */
    protected int knowledge;

    /**
     * The indicator whether the player can trade with the teacher.
     */
    protected boolean isGuess = true;

    /**
     * The default constructor
     */
    Teacher() {
        Random randomInt = new Random();
        this.knowledge = randomInt.nextInt(100);
    }

    /**
     * If the player's guess is true, the player can trade knowledge with the teacher
     */
    public void giveKnowledge(Trader trader) {
        if (this.isGuess) {
            trader.addKnowledge(this.knowledge);
        }
    }

    /**
     * The knowledge the teacher get from the Player.
     */
    public void addKnowledge(int know) {
        if (this.isGuess) {
            this.knowledge += know;
        }
    }


    /**
     * Return the knowledge this Teacher has
     *
     * @return the knowledge this Teacher has
     */
    public int getKnowledge() {
        return this.knowledge;
    }


}
