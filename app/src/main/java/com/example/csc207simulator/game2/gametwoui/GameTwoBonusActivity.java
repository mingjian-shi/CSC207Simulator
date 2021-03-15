package com.example.csc207simulator.game2.gametwoui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.csc207simulator.Player;
import com.example.csc207simulator.R;
import com.example.csc207simulator.game3.GameThreeUI.GameThreeDifficultyActivity;
import com.example.csc207simulator.game2.gametwobackend.BonusGame;
import com.example.csc207simulator.SetupManagement.SettableActivity;

/**
 * The game two bonus activity will be responsible for interacting with UI through backend BonusGame.
 */
public class GameTwoBonusActivity extends SettableActivity {
    /**
     * The bonus game responsible for bonus game related activities
     */
    private BonusGame bonusgame = new BonusGame();

    /**
     * The player responsible for action in game 2 bonus game
     */
    private Player player;


    /**
     * Click button method
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_two_bonus);
        player = super.appManager.getCurrentPlayer();
        Button button1 = findViewById(R.id.check2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNum();
            }
        });
    }

    /**
     * Check the num the player enters
     */
    public void checkNum() {
        String num = ((EditText) findViewById(R.id.score)).getText().toString();
        int NumtoGuess = Integer.parseInt(num);
        if (bonusgame.checkScore(NumtoGuess, player)) {
            bonusgame.addScore(player);
            int result = bonusgame.getScore(player);
            player.addPerformance(3); // If the player enters the right score
            if (setup.getIsEnglish().equals("en")) {
                Toast.makeText(this, " Congratulations! Your guess is correct and you earned " + result + " !", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, " 祝贺你! 你的猜测是对的然后你的了 " + result + " !", Toast.LENGTH_LONG).show();
            }
        } else {
            if (setup.getIsEnglish().equals("en")) {
                Toast.makeText(this, " Sorry, your memory is not correct", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, " 不好意思，你的猜测不正确", Toast.LENGTH_LONG).show();
            }
        }
        super.appManager.updateGame(1);
        openGameThreeActivity();

    }

    /**
     * Open GameThreeActivity
     */
    public void openGameThreeActivity() {
        Intent intent = new Intent(this, GameThreeDifficultyActivity.class);
        super.attachToIntent(intent);
        startActivity(intent);
    }

    /**
     * Resets the user interface to reflect any changes from the backend.
     */
    @Override
    public void resetContentView() {
        setContentView(R.layout.activity_game_two_bonus);
    }
}
