package com.example.csc207simulator.AccountManagement.AccountManagementBackend;


import android.content.Context;

import com.example.csc207simulator.AccountManagement.Account;
import com.example.csc207simulator.Player;
import com.example.csc207simulator.SetupManagement.Setup;

import java.util.ArrayList;

/**
 * This class has 2 main responsibilities (use Facade pattern):
 * 1) Cooperate with LoginActivity & NewaccountActivity -> Use AccountInformationChecker
 * 2) Cooperate with Files (both read and write)        -> Use FileModifier
 * The class is also responsible for miscellaneous management of accounts.
 *
 * NOTES: When an AccountManager has been created, it will automatically retrieve account list and current account(if there is one).
 */
public class AccountManager {

    /**
     * These 2 variables will be initialized oce login.
     */
    private String username;
    private String password;

    /**
     * account list containing user list, current account for the game, tow helper classes with functions mentioned above.
     */
    private ArrayList<Account> accountList;
    private Account currentAccount;
    private AccountInformationChecker aIC;
    private FileModifier fM;

    /**
     * Wait for login to finish.
     */
    public interface OnLoginCheckFinishedListener{
        void onLoginSuccess();
        void onLoginFail();
    }

    /**
     * Wait for creation of new account to finish.
     */
    public interface OnNewaccountCreationCheckFinishedListener{
        void onNewaccountCreationSuccess();
        void onNewaccountCreationFail();
    }

    /**
     * When an AccountManager has been created, it will automatically retrieve account list and current account(if there is any).
     * @param context context passed from other activity.
     */
    public AccountManager(Context context){
        this.aIC = new AccountInformationChecker();
        this.fM = new FileModifier();
        this.accountList =new ArrayList<>();
        this.load(context);
    }

    /**
     * Retrieve the account list and current account (if there is one).
     * @param context passed from previous activities.
     */
    public void load(Context context){
        accountList = fM.loadAccountList(context);
        for (Account a : accountList){
            String u = a.getUserName();
            String p = a.getPassWord();
            if (username != null && password !=null){
                if (username.equals(u) && password.equals(p)) {
                    currentAccount = a;
                }
            }

        }
    }

    /**
     * Change the file information whenever the account information is changed
     *
     * @param context passed from activity
     */
    public void save(Context context){
        for (int i =0; i<accountList.size();i++){
            if (accountList.get(i).getUserName().equals(currentAccount.getUserName()) && accountList.get(i).getPassWord().equals(currentAccount.getPassWord())){
                accountList.set(i,currentAccount);
            }
        }
        fM.save(context, accountList);
        load(context);
    }

    /**
     * Create or refresh the file content
     *
     * @param context passed from activity
     */
    public void newFile(Context context) {
        fM.newFile(context);
    }

    /**
     * Initialize account information to File after register
     */
    public void saveAccountToFile(Context context, Account account) {
        fM.saveAccountToFile(context,account);
        load(context);
    }

    /**
     * check whether login is successful.
     * Only when there is overlap and not contain null input then login.
     * @param userName username
     * @param passWord password
     * @param onLoginCheckFinishedListener whether check is finished.
     */
    public void checkLogin(String userName, String passWord, OnLoginCheckFinishedListener onLoginCheckFinishedListener){
        if (aIC.isOverlap(userName, passWord, accountList)
                && aIC.notContainNullInput(userName, passWord)){
            onLoginCheckFinishedListener.onLoginSuccess();
        }
        else{
            onLoginCheckFinishedListener.onLoginFail();
        }
    }

    /**
     * check whether new account creation is successful.
     * Only when there isn't overlap and not contain null input then create new account
     * @param userName username
     * @param passWord password
     * @param onNewaccountCreationCheckFinishedListener whether check is finished
     */
    public void checkNewaccountCreation(String userName, String passWord, OnNewaccountCreationCheckFinishedListener onNewaccountCreationCheckFinishedListener){
        if (!aIC.isOverlap(userName, passWord, accountList)
                && aIC.notContainNullInput(userName, passWord)){
            onNewaccountCreationCheckFinishedListener.onNewaccountCreationSuccess();
        }
        else{
            onNewaccountCreationCheckFinishedListener.onNewaccountCreationFail();
        }
    }

    /**
     * Create a new account with initialized settings.
     * @param userName username
     * @param passWord password
     * @return a new account with initialized settings.
     */
    public Account accountInitialization(String userName, String passWord){
        return new Account.AccountBuilder(userName,passWord).setPlayer(new Player()).setCurrentState(0).setSetUp(new Setup()).build();
    }

    /**
     * Get current account.
     * @return current account.
     */
    public Account getCurrentAccount(){
        return currentAccount;
    }

    /**
     * Get account list.
     * @return account list.
     */
    public ArrayList<Account> getAccountList(){
        return accountList;
    }

    /**
     * Setter for username, username will be set after login.
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Setter for password, password will be set after login.
     * @param password password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
