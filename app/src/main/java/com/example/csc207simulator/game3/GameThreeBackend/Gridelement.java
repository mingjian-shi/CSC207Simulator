package com.example.csc207simulator.game3.GameThreeBackend;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.csc207simulator.R;



public class Gridelement extends Checkstatus implements View.OnClickListener, View.OnLongClickListener {
    /**
     * element drawer based on the properties of the element
     */
    public Gridelement(Context context, int x, int y) {
        super(context);

        setPosition(x, y);
        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    public void resetclickable() {
        this.setClickable(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    public void onClick(View v) {
        if (isSearched()) {
            return;
        }
        GameThreeEngine.getInstance().click(getXPos(), getYPos());
    }

    @Override
    public boolean onLongClick(View v) {
        GameThreeEngine.getInstance().search(getXPos(), getYPos());
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawButton(canvas);

        if (isSearched()) {
            if (!isRevealed()) {
                drawSearch(canvas);
            } else {
                if (isChest()) {
                    drawfindknowledge(canvas);
                } else if(isSecret()){
                    drawSecret(canvas);
                } else{
                    drawfindnothing(canvas);
                }
            }
        } else if (isRevealed() && !isSecret() && !isChest() && !isClicked()) {
            drawNumber(canvas);

        } else if (isRevealed() && isChest() && !isClicked()) {

            drawLockedChest(canvas);
        } else if (isRevealed() && isSecret() && !isClicked()) {

            drawLockedChest(canvas);
        }else {
            if (isClicked()) {

                if (getValue() == 10) {
                    drawChest(canvas);
                } else if(getValue() == 11){
                    drawChest(canvas);
                } else{
                    drawNumber(canvas);
                }
            } else {
                drawButton(canvas);
            }
        }
    }

    /**
     * draw an element that is clicked and it's a chest
     */
    private void drawChest(Canvas canvas) {

        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.openchest);
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }

    /**
     * draw an element that is searched but has secret book
     */
    private void drawSecret(Canvas canvas) {

        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.secret);
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }

    /**
     * draw an element that is searched but has chests
     */
    private void drawfindknowledge(Canvas canvas) {

        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.openedchest);
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }

    /**
     * draw an element that is searched but has nothing
     */
    private void drawfindnothing(Canvas canvas) {

        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.cancel);
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }

    /**
     * draw an element that is searched
     */
    private void drawSearch(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.search);
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }
    /**
     * draw a untouched element
     */
    private void drawButton(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.unclick);
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }

    /**
     * draw a locked chest that is not searched
     */
    private void drawLockedChest(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.lockedchest);
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }

    /**
     * draw a number
     */
    private void drawNumber(Canvas canvas) {
        Drawable drawable = null;

        switch (getValue()) {
            case 0:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.zero);
                break;
            case 1:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.one);
                break;
            case 2:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.two);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.three);
                break;
            case 4:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.four);
                break;
            case 5:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.five);
                break;
            case 6:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.six);
                break;
            case 7:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.seven);
                break;
            case 8:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.eight);
                break;

        }

        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }


}