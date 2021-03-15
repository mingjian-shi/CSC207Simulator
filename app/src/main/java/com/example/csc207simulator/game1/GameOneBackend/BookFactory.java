package com.example.csc207simulator.game1.GameOneBackend;

/**
 * A class representing the function of creating a Book. Current Book types include GoodBook and
 * BadBook.
 */
public class BookFactory {

    /**
     * Return a new Book object based on bookType.
     *
     * @param bookType the type of Book object to create.
     * @return a new Book object based on bookType.
     */
    Book createBook(String bookType) {
        if (bookType == null) {
            return null;
        }
        if (bookType.equalsIgnoreCase("GOOD")) {
            return new GoodBook();
        } else if (bookType.equalsIgnoreCase("BAD")) {
            return new BadBook();
        } else if (bookType.equalsIgnoreCase("BONUS")) {
            return new BonusBook();
        }
        return null;
    }
}
