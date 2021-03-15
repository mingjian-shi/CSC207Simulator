package com.example.csc207simulator;

import com.example.csc207simulator.game2.gametwobackend.Teacher;

import java.util.Observable;
import java.util.Observer;

public class Player implements Trader, Observer {


    /**
     * private attributes: score and knowledge.
     */
    private int score;
    private int knowledge;
    private int highestScore;
    private int performance;

    /**
     * The constructor when loading data from a file.
     *
     * @param score     from the previous game.
     * @param knowledge from the previous game.
     */
    public Player(int score, int knowledge, int highestScore, int performance) {
        this.score = score;
        this.knowledge = knowledge;
        this.highestScore = highestScore;
        this.performance = performance;
    }

    /**
     * The default constructor.
     */
    public Player() {
        this.score = 0;
        this.knowledge = 0;
        this.highestScore = 0;
        this.performance = 0;
    }

    /**
     * Getter for score.
     *
     * @return score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter for score.
     *
     * @param sco the input score.
     */
    public void setScore(int sco) {
        score = sco;
    }

    /**
     * Clear current Score.
     */
    public void clearall() {
        score = 0;
        knowledge = 0;
        performance = 0;
    }

    /**
     * Check if it's the high score. If so, replace it.
     */

    void highScore() {
        if (score > highestScore)
            highestScore = score;
    }

    /**
     * Add some score.
     *
     * @param sco the input score.
     */
    public void addScore(int sco) {
        score = sco + score;
    }

    /**
     * Getter for Knowledge.
     *
     * @return knowledge.
     */
    public int getKnowledge() {
        return knowledge;
    }

    /**
     * Setter for Knowledge.
     *
     * @param know the input knowledge.
     */
    public void setKnowledge(int know) {
        knowledge = know;
    }

    /**
     * Add some knowledge.
     *
     * @param know the input knowledge.
     */
    public void addKnowledge(int know) {
        knowledge = know + knowledge;
    }

    /**
     * the other Trader receives some knowledge.
     *
     * @param trader the other Trader.
     */
    public void giveKnowledge(Trader trader) {
        trader.addKnowledge(this.getKnowledge());
    }

    /**
     * getter for highest score
     * @return highest score
     */
    public int getHighestScore() {
        return highestScore;
    }

    /**
     * add performance score
     * @param performance performance score
     */
    public void addPerformance(int performance) {
        this.performance += performance;
    }

    /**
     * getter for performance
     * @return the performance
     */
    public int getPerformance() {
        return this.performance;
    }

    /**
     * Returns true iff knowledge is less than or equal to this Player's knowledge.
     *
     * @param knowledge the knowledge to compare to this Player's knowledge.
     * @return true iff the knowledge is less than or equal to this Player's knowledge.
     */
    public boolean hasEnough(int knowledge) {
        return knowledge <= this.knowledge;
    }

    /**
     * Updates the properties of this Player based on a change in an Observable object this Player
     * observes.
     *
     * @param o   the Observable object that has changed.
     * @param arg the parameter passed through from the Observable object.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Teacher) {
            this.addKnowledge((int) arg);
        }
    }
}
