package com.example.csc207simulator;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.csc207simulator.AccountManagement.Account;
import com.example.csc207simulator.AccountManagement.AccountManagementBackend.AccountManager;
import com.example.csc207simulator.SetupManagement.Setup;
import com.example.csc207simulator.game1.GameOneUI.GameOneSetUpActivity;
import com.example.csc207simulator.game2.gametwobackend.GameTwoManager;
import com.example.csc207simulator.game2.gametwoui.GameTwoSetUpActivity;
import com.example.csc207simulator.game3.GameThreeUI.GameThreeDifficultyActivity;

/**
 * This class is passed through different activities. It is the topper class for internal storage.
 * Any other manager that needs to be conveyed through activities can be saved as an instance in
 * this class. It is also responsible for deciding which game to go into if the user have previously
 * played the game.
 *
 */
public class AppManager {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private AccountManager accountManager;
    private GameTwoManager gameTwoManager;

    /**
     * Construct the appmanager
     * @param context context passed from the previous acticity
     */
    public AppManager(Context context) {
        AppManager.context = context;
        this.accountManager = new AccountManager(context);
    }

    /**
     * decide which state the user need to enter according to its past data.
     * @param state state that the user have entered
     */
    public void updateGame(int state) {
        int set = 0;
        if (state == 0) {
            set = 1;
        } else if (state == 1) {
            set = 2;
        } else if (state == 2) {
            accountManager.getCurrentAccount().getPlayer().highScore();
        }
        accountManager.getCurrentAccount().getPlayer().highScore();
        accountManager.getCurrentAccount().setCurrentState(set);
        accountManager.save(context);
    }

    /**
     * Updates the setup in accountManager.
     * @param setup new setup
     */
    public void updateSetUp(Setup setup){
        accountManager.getCurrentAccount().setSetUp(setup);
        accountManager.save(context);
    }

    /**
     * get the current Account
     * @return current account
     */
    public Account getCurrentAccount(){
        return accountManager.getCurrentAccount();
    }

    /**
     * get the current player
     * @return current player
     */
    public Player getCurrentPlayer(){
        return accountManager.getCurrentAccount().getPlayer();
    }

    /**
     * get the current accounntmanager
     * @return the currentaccountmanager
     */
    public AccountManager getAccountMananger(){
        return this.accountManager;
    }

    /**
     * set the account manager
     * @param accountManager accountmanager
     */
    public void setAccountManager(AccountManager accountManager){
        this.accountManager = accountManager;
    }

    /**
     * To update the accountmanager
     */
    public void updateAccountManager() {
        this.accountManager.load(context);
    }

    /**
     * To set the username for accountmanager
     * @param userName username
     */
    public void setAccountManagerUserName(String userName) {
        this.accountManager.setUsername(userName);
    }

    /**
     * To set the password for accountmanager
     * @param passWord password
     */
    public void setAccountManagerPassWord(String passWord){
        this.accountManager.setPassword(passWord);
    }

    /**
     * The actual activity that the user is entering based on current state.
     * @return the activity class
     */
    public Class playGame(){
        int state = this.getAccountMananger().getCurrentAccount().getCurrentState();
        if (state == 0) {
            return GameOneSetUpActivity.class;
        } else if (state == 1) {
            return GameTwoSetUpActivity.class;
        } else {
            return GameThreeDifficultyActivity.class;
        }
    }

    /**
     * Creates an instance of GameTwoManager for game two.
     */
    public void createGameTwoManager(){
        Player player = this.getCurrentPlayer();
        this.gameTwoManager = new GameTwoManager(player.getKnowledge(), player);
    }

    /**
     * Return the GameTwoManager.
     *
     * @return the GameTwoManager.
     */
    public GameTwoManager getGameTwoManager(){
        return this.gameTwoManager;
    }

    /**
     * Remove the GameTwoManager from the AppManager to save memory.
     */
    public void deleteGameTwoManager(){
        this.gameTwoManager = null;
    }
}
