package com.example.csc207simulator.game1.GameOneBackend;

/**
 * A class representing the function of linking the id of the Books to the buttons on the user
 * interface.
 */
public class BookSetter {

    /**
     * The list of ids of the Books currently on the screen.
     */
    private int[] ids;

    /**
     * Creates a new BookSetter.
     *
     * @param ids the integer array representing the ids of the Books currently on the screen.
     *            Should be the same array used in BookManager.
     */
    public BookSetter(int[] ids) {
        this.ids = ids;
    }

    /**
     * Sets the id of Book b.
     *
     * @param b     the Book to set the id for.
     * @param numBooks  the number of Books currently on the screen.
     */
    void setId(Book b, int numBooks) {
        boolean isIdSet = false;
        if (numBooks == 0) {
            b.setId(1);
            this.ids[0] = 1;
        } else {
            for (int i = 1; (i < ids.length + 1 && !isIdSet); i++) {
                if (ids[i - 1] == 0) {
                    isIdSet = true;
                    b.setId(i);
                    this.ids[i - 1] = i;
                }
            }
        }
    }
}
