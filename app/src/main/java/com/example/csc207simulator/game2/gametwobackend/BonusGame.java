package com.example.csc207simulator.game2.gametwobackend;

import com.example.csc207simulator.Player;


/**
 * A class representing a BonusGame for GameTwo. A bonus scores will be given if the Player failed
 * to trade with every teacher and guessed the current score correctly.
 */
public class BonusGame {

    /**
     * Returns if the score the player enters is the exact score.
     *
     * @param score the score the player enters.
     */
    public boolean checkScore(int score, Player player){
        return score == player.getScore();
    }

    /**
     * Add the bonus score to the current player.
     */
    public void addScore(Player player){
        player.addScore(player.getScore());
    }

    /**
     * Returns the score of the current player.
     */
    public int getScore(Player player){
        return player.getScore();
    }
}
