package com.example.csc207simulator.game3.GameThreeBackend;

import android.content.Context;
import android.view.View;

/**
 * A class responsible for the setup of the game three.
 */
public abstract class Checkstatus extends View {
    /**
     * The value set for each field.
     */
    private int value;

    /**
     * The boolean show whether the field is a chest.
     */
    private boolean isChest;

    /**
     * The boolean show whether the field is revealed.
     */
    private boolean isRevealed;

    /**
     * The boolean show whether the field is clicked.
     */
    private boolean isClicked;

    /**
     * The boolean show whether the chest is searched.
     */
    private boolean isSearched;

    /**
     * The boolean show whether the field is secret.
     */
    private boolean isSecret;

    /**
     * The width and the height.
     */
    private int x, y;
    private int position;

    public Checkstatus(Context context) {
        super(context);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        isChest = false;
        isRevealed = false;
        isClicked = false;
        isSearched = false;
        isSecret = false;
        if (value == 10) {
            isChest = true;
        }
        if (value == 11) {
            isSecret = true;
        }

        this.value = value;
    }

    public boolean isChest() {
        return isChest;
    }

    public void setchest(boolean chest) {
        isChest = chest;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed() {
        isRevealed = true;
        invalidate();
    }

    public boolean isClicked() {
        return isClicked;
    }
    public boolean isSecret() {
        return isSecret;
    }
    public void setClicked() {
        this.isClicked = true;
        this.isRevealed = true;

        invalidate();
    }

    public boolean isSearched() {
        return isSearched;
    }

    public void setSearched(boolean searched) {
        isSearched = searched;
    }

    public int getXPos() {
        return x;
    }

    public int getYPos() {
        return y;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;

        this.position = y * GameThreeEngine.getInstance().getWIDTH() + x;

        invalidate();
    }

}