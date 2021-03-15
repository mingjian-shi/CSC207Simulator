package com.example.csc207simulator.game1.GameOneBackend;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * A class responsible for managing Books.
 */
public class BookManager implements Observer {

    /**
     * The maximum number of Books allowed to be displayed on the screen.
     */
    private int maxBooks = 6;

    /**
     * A list of Books belonging to this BookManager.
     */
    private List<Book> books;

    /**
     * Responsible for setting the id of Books.
     */
    private BookSetter bookSetter;

    /**
     * The list of ids of the Books in this BookManager
     */
    private int[] bookIds;

    /**
     * The BookFactory for this BookManager
     */
    private BookFactory bookFactory;

    /**
     * bonusActivated is true iff the player has completed the hidden action required.
     */
    private Boolean bonusActivated;

    /**
     * The current knowledge the Player has obtained from the Books.
     */
    private int currentKnowledge;

    /**
     * Creates an instance of BookManager.
     *
     * @param books the list of books the BookManager will use. Should be empty.
     * @param bookIds the integer array representing the position of each book.
     * @param bookFactory the BookFactory that will create the Books
     * @param bookSetter the BookSetter that will set the id and position of a Book on the screen
     */
    public BookManager(List<Book> books, int[] bookIds, BookFactory bookFactory,
                       BookSetter bookSetter) {
        this.books = books;
        this.bookIds = bookIds;
        this.bookFactory = bookFactory;
        this.bookSetter = bookSetter;
        this.bonusActivated = false;
        this.currentKnowledge = 0;
    }

    /**
     * Updates the state of this BookManager.
     */
    void update() {
        //if the number of Books on the screen falls below maxBooks, attempt to create more Books.
        if (this.books.size() < maxBooks) {
            Random randNum = new Random();
            if (randNum.nextInt(5) + 1 < 3)
                this.createBooks(randNum.nextInt(
                        maxBooks - this.books.size()) + 1);
        }
    }

    /**
     * Create numBooks Books and add them to the list of Books for this BookManager.
     *
     * @param numBooks the number of Books to create.
     */
    void createBooks(int numBooks) {
        for (int i = 0; i < numBooks; i++) {
            Book b;
            if (this.bonusActivated) {
                b = bookFactory.createBook("BONUS");
            } else {
                b = createNormalBook();
            }
            b.addObserver(this);
            bookSetter.setId(b, books.size());
            this.books.add(b);
        }
    }

    /**
     * Return either a GoodBook or a BadBook.
     *
     * @return either a GoodBook or BadBook.
     */
    private Book createNormalBook() {
        Book b;
        Random randNum = new Random();
        if (randNum.nextInt(5) < 3) {
            b = bookFactory.createBook("GOOD");
        } else {
            b = bookFactory.createBook("BAD");
        }
        return b;
    }

    /**
     * Remove booksToRemove from this BookManager's list of Books.
     *
     * @param booksToRemove the list of Books to remove.
     */
    private void removeBooks(List<Book> booksToRemove) {
        for (Book book : booksToRemove) {
            this.books.remove(book);
            int bookId = book.getId();
            bookIds[bookId - 1] = 0;
        }
    }

    /**
     * Updates a Book with id id that was clicked by the Player.
     *
     * @param id the id of the Book to click.
     */
    void clickedBook(int id) {
        Book foundBook = this.findBook(id);
        if (foundBook != null) {
            foundBook.clickBook();
        }
    }

    /**
     * Return a Book with id id. if a Book with this id cannot be found return null.
     *
     * @param id the id of the Book to look for.
     * @return a Book with id id. Null if no Book with such id is found.
     */
    private Book findBook(int id) {
        for (Book b : books) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    /**
     * Return the maximum number of Books allowed on the screen.
     *
     * @return the maximum number of Books allowed on the screen.
     */
    int getMaxBooks() {
        return maxBooks;
    }

    /**
     * Removes all books from this BookManager's book list. Reset this BookManager's bookIds.
     */
    private void clearBooks() {
        this.books = new ArrayList<>();
        for (int i = 0; i < this.bookIds.length; i++) {
            this.bookIds[i] = 0;
        }
    }

    /**
     * Resets the objects within game one so that only BonusBooks are created.
     */
    void activateBonus() {
        this.bonusActivated = true;
        this.clearBooks();
        this.createBooks(maxBooks);
    }

    /**
     * Updates the properties of this BookManager based on a change in an Observable object, in this
     * case a Book, this BookManager observes. Removes the Book with 0 clicks left from the game.
     *
     * @param o   the Observable object that has changed, in this case a Book.
     * @param arg the parameter passed through from the Observable object, in this case the
     *            integer knowledge.
     */
    @Override
     public void update(Observable o, Object arg) {
        List<Book> toRemove = new ArrayList<>();
        toRemove.add((Book) o);
        this.removeBooks(toRemove);
        this.currentKnowledge += (int) arg;
    }

    /**
     * Return the knowledge the Player has gained so far from clicking Books.
     *
     * @return the knowledge the Player has gained so far from clicking Books.
     */
    int getCurrentKnowledge() {
        return this.currentKnowledge;
    }

    /**
     * Return whether the book at index index is a BadBook.
     *
     * @return whether the book at index index is a BadBook.
     */
    boolean isBad(int index){
        if(this.bookIds[index - 1] == 0){
            return false;
        }
        else{
            Book b = this.findBook(index);
            if (b != null){
                return b.getIsBad();
            }
            return false;
        }

    }

    /**
     * To delete the un-clicked but disappeared book from the list
     */
    void deleteBook(int id){
        Book b = this.findBook(id);
        if (b != null){
           books.remove(b);
           bookIds[id - 1] = 0;
        }
    }

    /**
     * Return true iff a Book with id id does not exist, ie. a Book with id id has been clicked open
     * and had been removed from the game.
     *
     * @param id the id of the Book to check.
     *
     * @return true iff a Book with id id does not exist.
     */
    boolean checkClicked(int id){
        return bookIds[id - 1] == 0;
    }
}
