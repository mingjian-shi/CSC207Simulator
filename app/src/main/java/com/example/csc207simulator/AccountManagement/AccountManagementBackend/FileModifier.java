package com.example.csc207simulator.AccountManagement.AccountManagementBackend;

import android.content.Context;

import com.example.csc207simulator.AccountManagement.Account;
import com.example.csc207simulator.Player;
import com.example.csc207simulator.SetupManagement.Setup;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class is responsible for interaction with files, including save, load, create new file etc.
 */
class FileModifier{

    /**
     * Address of android studio internal storage.
     */
    private static final String FILENAME = "accounts.txt";

    /**
     * Save account information to File.
     */
    void saveAccountToFile(Context context, Account a) {
        String toBeSave = a.getUserName() + " " + a.getPassWord() + " " + a.getPlayer().getScore() + " " + a.getPlayer().getKnowledge() + " " + a.getPlayer().getHighestScore() + " " + a.getPlayer().getPerformance() + " " + a.getCurrentState() + " " + a.getSetUp().getIsEnglish() + " " + a.getSetUp().getBackgroundColor();
        //username + password + score + knowledge + highestScore + currentState + english + color

        try {
            FileOutputStream fileOutputStream = context.openFileOutput(FILENAME, Context.MODE_APPEND);
            fileOutputStream.write(toBeSave.getBytes());
            fileOutputStream.write(Objects.requireNonNull(System.getProperty("line.separator")).getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create or refresh the file content
     *
     * @param context passed from activity
     */
    void newFile(Context context) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * refresh the file and put every account in the accountlist inside the internal storage
     * @param context context passed from activity
     * @param list accountlist passed from previous class
     */
    void save(Context context, ArrayList<Account> list) {
        newFile(context);

        for (Account a : list) {
            saveAccountToFile(context, a);
        }

    }

    /**
     * load the account list
     * @param context passed from activity
     * @return accountlist
     */
    ArrayList<Account> loadAccountList(Context context) {

        ArrayList<Account> list = new ArrayList<>();
        try {
            FileInputStream fileInputStream = context.openFileInput(FILENAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String text;

            while ((text = bufferedReader.readLine()) != null) {
                if (!(text.equals(""))) {
                    String[] words = text.split("\\s");
                    String u = words[0];
                    String p = words[1];
                    int s = Integer.valueOf(words[2]);
                    int k = Integer.valueOf(words[3]);
                    int hs = Integer.valueOf(words[4]);
                    int per = Integer.valueOf(words[5]);
                    int cs = Integer.valueOf(words[6]);
                    String e = words[7];
                    String c = words[8];
                    Player player = new Player(s, k, hs, per);
                    Setup setup = new Setup(e, c);
                    Account account = new Account.AccountBuilder(u,p).setPlayer(player).setCurrentState(cs).setSetUp(setup).build();
                    list.add(account);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;

    }
}
