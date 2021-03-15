package com.example.csc207simulator.Ranking;

import android.content.Context;

import com.example.csc207simulator.AccountManagement.Account;
import com.example.csc207simulator.AccountManagement.AccountManagementBackend.AccountManager;

import java.util.Arrays;
import java.util.Comparator;

/**
 * The class is responsible for sorting ranking by highest score.
 */
public class RankingByHighestScore implements RankingMethod {

    /**
     * get the ranking.
     * @param context context passed from activity.
     * @return the ranking by score.
     */
    public Account[] getRanking(Context context) {
        AccountManager accountManager = new AccountManager(context);
        Account[] accountArray = (Account[]) accountManager.getAccountList().toArray(new Account[accountManager.getAccountList().size()]);
        Arrays.sort(accountArray, new Sort());
        return accountArray;
    }

    /**
     * Sort by Highest Score.
     */
    public class Sort implements Comparator<Account> {
        @Override
        public int compare(Account a1, Account a2) {
            return a2.getPlayer().getHighestScore() - a1.getPlayer().getHighestScore();
        }

    }
}
