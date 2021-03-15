package com.example.csc207simulator.Ranking;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Toast;

import com.example.csc207simulator.AccountManagement.Account;
import com.example.csc207simulator.R;
import com.example.csc207simulator.SetupManagement.SettableActivity;
import com.example.csc207simulator.SetupManagement.SetupActivity;

/**
 * This Activity displays the scoreboard, after game3 finishes.
 * It has three different ranking systems: by knowledge, by highest score and by performance score.
 */

public class ScoreboardActivity extends SettableActivity implements View.OnClickListener{

    /**
     * ScoreboardLayout TextView in the xml file.
     */
    TextView scoreboardlayout;

    /**
     * The first - fifth places' usernames.
     */
    String place1, place2, place3, place4, place5;


    /**
     * Create the user interface.
     *
     * @param savedInstanceState the Bundle for this SettableActivity.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.appManager.updateGame(2);
        super.appManager.getAccountMananger().save(this);
        setContentView(R.layout.activity_scoreboard);
        scoreboardlayout = findViewById(R.id.scoreboardlayout);
        Button v1 = findViewById(R.id.knowledge);
        Button v2 = findViewById(R.id.highestscore);
        Button v3 = findViewById(R.id.performance);
        Button v4 = findViewById(R.id.exit);
        v1.setOnClickListener(this);
        v2.setOnClickListener(this);
        v3.setOnClickListener(this);
        v4.setOnClickListener(this);
        setScoreboard("highscore");
    }

    /**
     * Refreshes the scoreboard, displays a new ranking.
     * @param string input string
     */
    void setScoreboard(String string){
        SharedPreferences preferences = getSharedPreferences("PREF", 0);
        place1 = preferences.getString("0", "N/A");
        place2 = preferences.getString("1", "N/A");
        place3 = preferences.getString("2", "N/A");
        place4 = preferences.getString("3", "N/A");
        place5 = preferences.getString("4", "N/A");
        RankingMethod ranking;
        switch (string) {
            case "highscore": {
                ranking = new RankingByHighestScore();
                break;
            }
            case "performance": {
                ranking = new RankingByPerformance();
                break;
            }
            case "knowledge": {
                ranking = new RankingByKnowledge();
                break;
            }
            default:{
                ranking = new RankingByHighestScore();
            }
        }
        Account[] acclist = ranking.getRanking(this);
        System.out.println(acclist[0]);
        for (int i = 0; i < 5 && i < acclist.length; i++) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(String.valueOf(i), acclist[i].getUserName());
            editor.apply();
        }
        String insertString = returnString(string);
        String finalString1 = insertString + "\n"
                + "1st:  " + place1 + "\n"
                + "2nd:  " + place2 + "\n"
                + "3rd:  " + place3 + "\n"
                + "4th:  " + place4 + "\n"
                + "5th:  " + place5;
        scoreboardlayout.setText(finalString1);
    }

    private String returnString(String string){
        String out;
        if (setup.getIsEnglish().equals("zh")) {
            switch (string) {
                case "performance":
                    out = "表现分排行榜";
                    break;
                case "highscore":
                    out = "最高分排行榜";
                    break;
                case "knowledge":
                    out = "知识排行榜";
                    break;
                default:
                    out = "Scoreboard by " + string;
                    break;
            }
        } else {
            out = "Scoreboard by " + string;
        }
        return out;
    }

    /**
     * OnClickListener, calls setScoreboard method or open another activity when it's being clicked.
     * @param v the button
     */
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.knowledge:
                setScoreboard("knowledge");
                break;
            case R.id.highestscore:
                setScoreboard("highscore");
                break;
            case R.id.performance:
                setScoreboard("performance");
                break;
            case R.id.exit:
                openSetupActivity();
                break;
            }
    }

    /**
     *Resets the user interface to reflect any changes from the backend.
     */
    public void resetContentView() {
        setContentView(R.layout.activity_scoreboard);
    }

    /**
     * Move from this activity to SetupActivity.
     */
    public void openSetupActivity() {
        Toast.makeText(this, "Welcome back!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, SetupActivity.class);
        super.attachToIntent(intent);
        startActivity(intent);
    }
}
