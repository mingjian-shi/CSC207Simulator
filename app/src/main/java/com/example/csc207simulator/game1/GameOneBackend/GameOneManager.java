package com.example.csc207simulator.game1.GameOneBackend;

import com.example.csc207simulator.GameManager;

/**
 * A class responsible for managing game one.
 */
public class GameOneManager extends GameManager {
    /**
     * Manages all the Books' statuses for this GameOneManager.
     */
    private BookManager bookManager;

    /**
     * Create a new GameOneManager.
     *
     * @param knowledge   the knowledge going into the game.
     * @param bookManager the manager for the Books for game one.
     */
    public GameOneManager(int knowledge, BookManager bookManager) {
        super(knowledge);
        this.bookManager = bookManager;
    }


    /**
     * Updates the current state of game one.
     */
    @Override
    public void update() {
        this.bookManager.update();
    }

    /**
     * Updates an object on the screen that was clicked.
     *
     * @param id the id of the object that was clicked.
     */
    public void itemClicked(int id) {
        this.bookManager.clickedBook(id);
    }

    /**
     * Creates the items required for game1.
     */
    public void createItems() {
        this.bookManager.createBooks(bookManager.getMaxBooks());
    }

    /**
     * Sets whether the bonus level has been activated for game one and creates necessary objects
     * for the bonus level.
     */
    public void activateBonus() {
        this.bookManager.activateBonus();
    }

    /**
     * Return the current knowledge the Player has gained so far from the game.
     *
     * @return the current knowledge the Player has gained so far from the game.
     */
    public int getKnowledge() {
        this.knowledge = this.bookManager.getCurrentKnowledge();
        return this.knowledge;
    }

    /**
     * Return the current knowledge the Player has gained so far from the game.
     *
     * @return the current knowledge the Player has gained so far from the game.
     */
    public boolean isBad(int index){
        return bookManager.isBad(index);
    }

    /**
     * To delete the un-click but disappeared book from the list
     */

    public void deleteBook (int id){bookManager.deleteBook(id);
    }

    /**
     * Return true iff a Book with id id does not exist, ie. a Book with id id has been clicked open
     * and had been removed from the game.
     *
     * @param id the id of the Book to check.
     *
     * @return true iff a Book with id id does not exist.
     */
    public boolean checkClicked(int id){return bookManager.checkClicked(id);}
}
