package com.example.csc207simulator.AccountManagement.AccountManagementBackend;


import com.example.csc207simulator.AccountManagement.Account;

import java.util.ArrayList;

/**
 * This class is an instance of AccountManager and will be responsible for checking the username & password combination that the player
 * inputs, specific methods such as checkOverlap, checkNullInput etc.
 */
class AccountInformationChecker {

    /**
     * Check if the username & password combination has been used by existing user, if it is then new player has to create
     * a new combination, while old player can login.
     *
     * @param userName username passed from activity
     * @param passWord password passed from activity
     * @return If there is an overlap of input and database
     */
    boolean isOverlap(String userName, String passWord, ArrayList<Account> list) {

        boolean overLap = false;
        for (Account account : list)
            if (account.getUserName().equals(userName) && account.getPassWord().equals(passWord)) {
                overLap = true;
                break;
            }

        return overLap;

    }


    /**
     * Check if there is not null input
     * @param userName username passed from activity
     * @param passWord password passed from activity
     * @return true if both username and password are not empty string.
     */
    boolean notContainNullInput(String userName, String passWord){
        return (!userName.equals("")) && (!passWord.equals(""));
    }


}
