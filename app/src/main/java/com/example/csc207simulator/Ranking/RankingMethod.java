package com.example.csc207simulator.Ranking;

import android.content.Context;
import com.example.csc207simulator.AccountManagement.Account;


/**
 *  The interface is responsible for getting rankings.
 */
public interface RankingMethod {

    Account[] getRanking(Context context);

}
