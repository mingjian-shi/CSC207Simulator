package com.example.csc207simulator.game2.gametwoui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import com.example.csc207simulator.Player;
import com.example.csc207simulator.R;
import com.example.csc207simulator.SetupManagement.SettableActivity;
import com.example.csc207simulator.game2.gametwobackend.GameTwoManager;

/**
 * This activity displays a guessing screen, the player has to choose a number between 1 and 2.
 */
public class GameTwoGuessActivity extends SettableActivity {
    /**
     * The gametwomanager responsible for teachers'action in game 2
     */
    private GameTwoManager gameTwoManager;

    /**
     * The player responsible for action in game 2
     */
    private Player player;


    /**
     * The int showing the player's current knowledge
     */
    private int knowledgeInt;

    /**
     * The knowledgeboard showing the player's current knowledge
     */
    private TextView knowledge;


    /**
     * Click button method
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_two_guess);
        knowledge = findViewById(R.id.knowledgeint3);
        player = super.appManager.getCurrentPlayer();
        knowledgeInt = player.getKnowledge();
        knowledge.setText(Integer.toString(knowledgeInt));
        gameTwoManager = super.appManager.getGameTwoManager();

        Button button1 = findViewById(R.id.check);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNum();
            }
        });

    }

    /**
     * Check whether the type of the teacher the player guesses is correct
     */
    public void checkNum() {
        try {
            Random randomInt = new Random();
            int random = randomInt.nextInt(100);
            String num = ((EditText) findViewById(R.id.numtoguess)).getText().toString();
            int NumtoGuess = Integer.parseInt(num);
            if (gameTwoManager.checkCorrect(NumtoGuess)) {
                player.addPerformance(1);
                gameTwoManager.add(1);
                if (setup.getIsEnglish().equals("en")) {
                    Toast.makeText(this, " Congratulations! Your guess is correct!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "祝贺你！你猜对了", Toast.LENGTH_LONG).show();
                }
                if (random % 2 == 1) {
                    openGameTwoTradeProActivity();
                    knowledgeInt = player.getKnowledge();
                    knowledge.setText(Integer.toString(knowledgeInt));
                    finish();
                } else {
                    openGameTwoTradeTaActivity();
                    knowledgeInt = player.getKnowledge();
                    knowledge.setText(Integer.toString(knowledgeInt));
                    finish();
                }
            } else {
                gameTwoManager.add(0);
                if (setup.getIsEnglish().equals("en")) {
                    Toast.makeText(this, " Sorry, your guess is incorrect...", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, " 抱歉，你的猜测是错的…", Toast.LENGTH_LONG).show();
                }
                openGameTwoActivity();
            }
        } catch (NumberFormatException ex) {
            if (setup.getIsEnglish().equals("en")) {
                Toast.makeText(this, " Please enter either 1 or 0", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, " 请输入0 或 1", Toast.LENGTH_LONG).show();
            }
        }

    }



    /**
     * Open GameThreeActivity
     */
    private void openGameTwoTradeProActivity() {
        Intent intent = new Intent(this, GameTwoTradeProActivity.class);
        super.attachToIntent(intent);
        ArrayList<String> teacherVisibility = getIntent().getStringArrayListExtra("teacher");
        intent.putStringArrayListExtra("teacher", teacherVisibility);
        startActivity(intent);
    }

    /**
     * Open GameThreeActivity
     */
    private void openGameTwoTradeTaActivity() {
        Intent intent = new Intent(this, GameTwoTradeTaActivity.class);
        super.attachToIntent(intent);
        ArrayList<String> teacherVisibility = getIntent().getStringArrayListExtra("teacher");
        intent.putStringArrayListExtra("teacher", teacherVisibility);
        startActivity(intent);
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
        setContentView(R.layout.activity_game_two_guess);
    }

}
