package com.example.csc207simulator;

/**
 * The abstract class for Gameone, Gametwo and Gamethree
 */
public abstract class GameManager {
    /**
     * The knowledge of the player
     */
    protected int knowledge;

    /**
     * Construct a game manager
     * @param knowledge knowledge
     */
    public GameManager(int knowledge) {
        this.knowledge = knowledge;
    }


    /**
     * update the setup of the game
     */
    protected abstract void update();

}
