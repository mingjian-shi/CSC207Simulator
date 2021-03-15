package com.example.csc207simulator.game2.gametwoui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.csc207simulator.Player;
import com.example.csc207simulator.R;
import com.example.csc207simulator.SetupManagement.SettableActivity;
import com.example.csc207simulator.game2.gametwobackend.GameTwoManager;

import java.util.ArrayList;

/**
 * This activity allows the player to trade with a TA.
 */
public class GameTwoTradeTaActivity extends SettableActivity{
    /**
     * The gametwomanager responsible for teachers'action in game 2
     */
    private GameTwoManager gameTwoManager;

    /**
     * The player responsible for action in game 2
     */
    private Player player;


    /**
     * Click button method
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_two_trade_ta);
        TextView knowledge = findViewById(R.id.knowledgeint6);
        player = super.appManager.getCurrentPlayer();
        int knowledgeInt = player.getKnowledge();
        knowledge.setText(Integer.toString(knowledgeInt));
        gameTwoManager = super.appManager.getGameTwoManager();

        Button button1 = findViewById(R.id.check3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNum();
            }
        });
    }

    /**
     * Check whether the ta has the enough knowledge
     */
    public void checkNum() {
        try {
            String num = ((EditText) findViewById(R.id.numtoguess3)).getText().toString();
            int NumtoGuess = Integer.parseInt(num);
            boolean ifEnough = gameTwoManager.checkNumPro(NumtoGuess, player);
            String show;
            if (ifEnough) {
                player.addPerformance(1);
                int result = gameTwoManager.showNumTa(NumtoGuess, player);
                player.addScore(result);
                player.addKnowledge(result);
                if (setup.getIsEnglish().equals("en")) {
                    show = "you earned " + result + " scores in this turn";
                } else {
                    show = "你本轮获得 " + result + " 分数在本轮游戏中";
                }
                openGameTwoActivity();
            } else {
                if (setup.getIsEnglish().equals("en")) {
                    show = "You do not have this much knowledge. Please enter a smaller amount.";
                } else {
                    show = "你并没有那么多的知识量。请输入一个小一点的数";
                }
            }
            Toast.makeText(this, show, Toast.LENGTH_LONG).show();
        } catch (NumberFormatException ex) {
            if (setup.getIsEnglish().equals("en")) {
                Toast.makeText(this, "Please enter a positive integer.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "请输入一个正数", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Open GameTwoActivity
     */
    public void openGameTwoActivity() {

        Intent intent = new Intent(this, GameTwoActivity.class);
        super.attachToIntent(intent);
        ArrayList<String> teacherVisibility = getIntent().getStringArrayListExtra("teacher");
        intent.putStringArrayListExtra("teacher", teacherVisibility);
        startActivity(intent);
    }


    /**
     * Resets the user interface to reflect any changes from the backend.
     */
    @Override
    public void resetContentView() {
        setContentView(R.layout.activity_game_two_trade_ta);
    }
}
