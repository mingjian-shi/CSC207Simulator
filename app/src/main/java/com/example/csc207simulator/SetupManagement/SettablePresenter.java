package com.example.csc207simulator.SetupManagement;

import android.content.res.Resources;
import android.view.View;

import com.example.csc207simulator.R;

/**
 * A class representing the Presenter for a SettableActivityView.
 */
class SettablePresenter {

    /**
     * The SettableActivity this SettableActivityPresenter is linked to.
     */
    private SettableActivity activity;

    /**
     * The SettableManager this SettableActivityPresenter is linked to.
     */
    private SettableManager settableManager;

    /**
     * Create an instance of a SettableActivity Presenter.
     *
     * @param settableActivity    the SettableActivity this SettablePresenter will
     *                        link to.
     * @param settableManager the SettableActivityManager this SettableActivityPresenter
     *                        will link to.
     */
    SettablePresenter(SettableActivity settableActivity,
                      SettableManager settableManager) {
        this.settableManager = settableManager;
        this.activity = settableActivity;
    }

    /**
     * Sets both the language and background.
     *
     * @param resources the resource containing the configuration to change the language of.
     */
    void initializeSettings(Resources resources) {
        this.settableManager.changeColor(activity);
        this.settableManager.changeLanguage(resources);
    }

    /**
     * Sets the background color for the user interface.
     *
     * @param view the View object that was clicked in order to call this method.
     */
    void setColor(View view) {
        this.settableManager.setColor(view, activity);
    }

    /**
     * Sets the language for the user interface.
     *
     * @param view      the View object that was clicked in order to call this method.
     * @param resources the resource containing the configuration to change the language of.
     */
    void setLanguage(View view, Resources resources) {
        String language;
        int id = view.getId();
        if (id == R.id.chinese) {
            language = "zh";
        } else {
            language = "en";
        }

        this.settableManager.setLanguageSetup(language);
        this.settableManager.changeLanguage(resources);
        this.activity.resetContentView();
    }

}
