package com.example.csc207simulator.SetupManagement;

/**
 * The presenter for the SetupView
 */
class SetupPresenter {
    /**
     * The SetupActivity this SetupPresenter is linked to.
     */
    private SetupActivity setupActivity;

    /**
     * The SetupManager that manages the setup.
     */
    private SetupManager setupManager;

    /**
     * Create an instance of SetupPresenter.
     *
     * @param setupActivity the SetupActivity to link to.
     */
    SetupPresenter(SetupActivity setupActivity, SetupManager setupManager) {
        this.setupActivity = setupActivity;
        this.setupManager = setupManager;
    }


    /**
     * Retrieves the state of the currentPlayer and tells the setupActivity to start the correct
     * game.
     */
    void playGame() {
        Class activity = this.setupManager.playGame();
        this.setupActivity.createIntent(activity);
    }
}
