package com.example.csc207simulator.SetupManagement;

/**
 * A class representing a Setup for the current Account logged into the game. The Setup saves
 * customizations such as the language and theme.
 */
public class Setup {

    /**
     * Background's color.
     */
    private String backgroundColor;

    /**
     * "en" if it's english, "zh" if it's mandarin.
     */
    private String language;

    /**
     * The default constructor
     */
    public Setup() {
        language = "en";
        backgroundColor = "light";
    }

    /**
     * The constructor, with different initialization.
     */
    public Setup(String language, String color) {
        this.language = language;
        backgroundColor = color;
    }

    /**
     * getter for backgroundColor.
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * setter for backgroundColor.
     */
    void setBackgroundColor(String color) {
        backgroundColor = color;
    }

    /**
     * Getter for isEnglish.
     */
    public String getIsEnglish() {
        return language;
    }

    /**
     * Reverses the current language.
     */
    void reverseLanguage() {
        //Reverses the current language.
        if (language.equals("en"))
            language = "zh";
        else
            language = "en";
    }
}
