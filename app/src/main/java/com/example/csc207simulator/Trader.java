package com.example.csc207simulator;

//The interface for class Teacher and Player

/**
 * This is the interface for class Teacher and Player
 */
public interface Trader {


    void giveKnowledge(Trader trader);

    /**
     * add knowledge
     * @param knowledge knowledge statistics
     */
    void addKnowledge(int knowledge);
}
