package com.example.csc207simulator.SetupManagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.csc207simulator.MasterActivity.MasterActivity;

/**
 * A class representing an Activity that can change settings. Current settings include language
 * and background color.
 */
public abstract class SettableActivity extends MasterActivity{

    /**
     * The setup of the game.
     */
    protected Setup setup;

    /**
     * The SettableActivityPresenter for this SettableActivity.
     */
    protected SettablePresenter settablePresenter;

    /**
     * Create the user interface.
     *
     * @param savedInstanceState the Bundle for this SettableActivity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        super.appManager = super.masterPresenter.retrieveAppManager(intent);
        this.setup = appManager.getCurrentAccount().getSetUp();
        this.settablePresenter = new SettablePresenter(this,
                new SettableManager(setup));
        initializeSettings();
    }

    /**
     * Changes the user interface to agree with the current Player's setup.
     */
    public void initializeSettings() {
        settablePresenter.initializeSettings(getBaseContext().getResources());
    }

    /**
     * Sets the language to Chinese or English.
     *
     * @param view the object that was clicked.
     */
    public void setLanguage(View view) {
        this.settablePresenter.setLanguage(view, getBaseContext().getResources());
        super.appManager.getCurrentAccount().setSetUp(setup);
    }

    /**
     * Sets the color of the background.
     *
     * @param view the object that was clicked.
     */
    public void setColor(View view) {

        this.settablePresenter.setColor(view);
        super.appManager.getCurrentAccount().setSetUp(setup);
    }

    /**
     *Resets the user interface to reflect any changes from the backend.
     */
    protected abstract void resetContentView();
}
