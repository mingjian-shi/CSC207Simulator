package com.example.csc207simulator.game1.GameOneBackend;

import java.util.Observable;

/**
 * A class representing a Book.
 */
public abstract class Book extends Observable {
    /**
     * The number of clicks a Player needs to open this Book.
     */
    int clicks;

    /**
     * The amount of knowledge a Book will give to a Player.
     */
    protected int knowledge;

    /**
     * An integer which informs the position of this Book on the screen.
     */
    private int id;

    /**
     * A boolean to determine whether it is a bad book.
     */
    boolean isBad;

    /**
     * Set the id of this Book.
     *
     * @param id the id to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Return the id of this Book.
     *
     * @return the id of this Book.
     */
    public int getId() {
        return id;
    }

    /**
     * Decreases the number of clicks left to open this Book by one.
     */
    private void decreaseClicks() {
        this.clicks--;
    }

    /**
     * Return the knowledge this Book gives.
     *
     * @return the knowledge this Book gives.
     */
    private int getKnowledge() {
        return this.knowledge;
    }

    /**
     * Decreases the click of a Book and if a Book is open, notify its Observer, a Player to give
     * knowledge.
     */
    void clickBook() {
        decreaseClicks();
        if (this.clicks == 0) {
            this.setChanged();
            this.notifyObservers(this.getKnowledge());
        }
    }

    /**
     * Return whether this Book is bad or not.
     *
     * @return whether this Book is bad or not.
     */
    boolean getIsBad(){
        return this.isBad;
    }
}
