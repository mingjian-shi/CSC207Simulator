package com.example.csc207simulator.SetupManagement;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;

import com.example.csc207simulator.R;

import java.util.Locale;

/**
 * A class representing the responsibilities of changing the settings. Current settings include
 * background color and language.
 */
class SettableManager {

    /**
     * The current Setup applied to the app.
     */
    private Setup setup;

    /**
     * Creates an instance of SettableActivityManager.
     *
     * @param setup the current Setup applied to the app.
     */
    SettableManager(Setup setup) {
        this.setup = setup;
    }

    /**
     * Changes the background colour of the user interface based on the Setup.
     *
     */
    void changeColor(SettableActivity activity) {
        String currColor = setup.getBackgroundColor();
        if (currColor.equals("dark")) {
            activity.setTheme(R.style.DarkTheme);
        } else if (currColor.equals("light")) {
            activity.setTheme(R.style.LightTheme);
        }
        activity.resetContentView();
    }

    /**
     * Changes the language of the app.
     *
     * @param resources the resource containing the configuration to change the language of.
     */
    /*code for changing language referenced from:
    https://stackoverflow.com/questions/2900023/change-app-language-programmatically-in-android
     */
    @SuppressWarnings("deprecation")
    void changeLanguage(Resources resources) {
        Locale locale = new Locale(setup.getIsEnglish());
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    /**
     * Changes the background color for the Setup.
     *
     * @param view the View object that was clicked in order to call this method.
     */
    void setColor(View view, SettableActivity activity) {
        switch (view.getId()) {
            case (R.id.black):
                setup.setBackgroundColor("dark");
                break;
            case (R.id.white):
                setup.setBackgroundColor("light");
                break;
        }
        this.changeColor(activity);
    }

    /**
     * Changes the language of the Setup.
     *
     * @param language the language to change the Setup to.
     */
    void setLanguageSetup(String language) {
        String setupLanguage = setup.getIsEnglish();
        if ((setupLanguage.equals("zh") && language.equals("en")) ||
                (setupLanguage.equals("en") && language.equals("zh"))) {
            setup.reverseLanguage();
        }
    }
}
