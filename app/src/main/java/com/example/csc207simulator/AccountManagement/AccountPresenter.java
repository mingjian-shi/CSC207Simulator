package com.example.csc207simulator.AccountManagement;

import android.content.Context;

import com.example.csc207simulator.AccountManagement.AccountManagementBackend.AccountManager;
import com.example.csc207simulator.AccountManagement.AccountManagementUI.LoginActivity;
import com.example.csc207simulator.AccountManagement.AccountManagementUI.NewaccountActivity;

/**
 * The accountpresenter will be responsible for interacting with UI through loginnewaccountview and backend through accountmanager.
 * The class will contain the two instances.
 */
public class AccountPresenter implements AccountManager.OnLoginCheckFinishedListener, AccountManager.OnNewaccountCreationCheckFinishedListener {


    private AccountManager accountManager;
    private NewaccountActivity newaccountActivity;
    private LoginActivity loginActivity;

    /**
     * Construct the accountPresenter with the newaccountview and accountmanager
     * @param newaccountActivity newaccountActivity
     * @param accountManager accountmanager
     */
    public AccountPresenter(NewaccountActivity newaccountActivity, AccountManager accountManager){
        this.newaccountActivity = newaccountActivity;
        this.accountManager = accountManager;
    }

    /**
     * Construct the accountpresenter with the login view adn accountmanager
     * @param loginActivity loginactivity
     * @param accountManager accountmanager
     */
    public AccountPresenter(LoginActivity loginActivity, AccountManager accountManager) {
        this.loginActivity = loginActivity;
        this.accountManager = accountManager;
    }

    /**
     * get the account manager
     * @return accountmanager
     */
    public AccountManager getAccountManager() {
        return accountManager;
    }

    /**
     * check whether login or new account creation is valid.
     * @param userName username
     * @param passWord password
     * @param indicator indicates whether this is login or new account creation.
     */
    public void check(String userName, String passWord, String indicator){
        if (indicator.equals("Login")){
            if(loginActivity != null){
                loginActivity.showProgressBar();
            }
            accountManager.checkLogin(userName, passWord, this);
        } else {
            if(newaccountActivity != null){
                newaccountActivity.showProgressBar();
            }
            accountManager.checkNewaccountCreation(userName, passWord, this);
        }
    }

    /**
     * Calls accountmanager saveAccountToFile method
     * @param context context passed from activities.
     * @param account the account to be saved.
     */
    public void saveAccountToFile(Context context, Account account){
        accountManager.saveAccountToFile(context, account);
    }

    /**
     * Calls accountmanager accountInitialization method.
     * @param userName username
     * @param passWord password
     * @return a new account.
     */
    public Account accountInitialization(String userName, String passWord){
        return accountManager.accountInitialization(userName, passWord);
    }

    /**
     * Calls accountmanager load method.
     * @param context context passed from previous activities.
     */
    public void load(Context context){
        accountManager.load(context);
    }

    /**
     * After successfully login.
     */
    @Override
    public void onLoginSuccess() {
        loginActivity.openSetupActivity();
    }

    /**
     *  Login failed
     */
    @Override
    public void onLoginFail() {
        loginActivity.hideProgressBar();
        loginActivity.openLoginActivity();
    }

    /**
     * New account created successfully.
     */
    @Override
    public void onNewaccountCreationSuccess() {
        newaccountActivity.openLoginActivity();
    }

    /**
     * New account created unsuccessfully.
     */
    @Override
    public void onNewaccountCreationFail() {
        newaccountActivity.hideProgressBar();
        newaccountActivity.openNewaccountActivity();
    }
}
