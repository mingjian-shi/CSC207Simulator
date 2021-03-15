package com.example.csc207simulator.SetupManagement;

import com.example.csc207simulator.AppManager;
/**
 * A class responsible for changing the setup for the game.
 */
class SetupManager {

    /**
     * The AppManager that manages the game.
     */
    private AppManager appManager;

    /**
     * Create an instance of SetupManager.
     *
     * @param appManager the AppManager that manages the game.
     */
    SetupManager(AppManager appManager) {
        this.appManager = appManager;
    }

    /**
     * Returns the Class that the game will continue from, and creates and instance of the
     * AppManager.
     *
     * @return a Class that the game will continue from.
     */
    Class playGame() {
        return appManager.playGame();
    }
}
