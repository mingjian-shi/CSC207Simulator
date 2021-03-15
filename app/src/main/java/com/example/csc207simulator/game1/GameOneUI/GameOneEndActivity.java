package com.example.csc207simulator.game1.GameOneUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.csc207simulator.Player;
import com.example.csc207simulator.R;
import com.example.csc207simulator.SetupManagement.SettableActivity;
import com.example.csc207simulator.game2.gametwoui.GameTwoSetUpActivity;

/**
 * This activity shows up after game one finishes, and it will direct to GameTwoSetupActivity.
 */
public class GameOneEndActivity extends SettableActivity {
    /**
     * The CurrentPlayer who is playing the game;
     */
    private Player player;

    /**
     * Create the user's interface.
     *
     * @param savedInstanceState the Bundle for this SettableActivity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.appManager.updateGame(0);
        setContentView(R.layout.activity_game_one_end);
        player = super.appManager.getCurrentPlayer();
        Button button1 = findViewById(R.id.EndGameOneButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showScore();
            }
        });
    }

    /**
     * Display the score, and then open the next activity.
     */
    public void showScore() {
        int result = player.getScore();
        if (setup.getIsEnglish().equals("en")) {
            Toast.makeText(this, " You score after game 1 is " + result + " !", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, " 在游戏1之后你的成绩是 " + result + " !", Toast.LENGTH_LONG).show();
        }
        openGameTwoSetUpActivity();
    }

    /**
     * Opens the next activity.
     */
    public void openGameTwoSetUpActivity() {
        Intent intent = new Intent(this, GameTwoSetUpActivity.class);
        super.attachToIntent(intent);
        startActivity(intent);

    }

    /**
     * Resets the user interface to reflect any changes from the backend.
     */
    @Override
    protected void resetContentView() {
        setContentView(R.layout.activity_game_one_end);
    }
}
