package com.example.csc207simulator.game3.GameThreeUI;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.csc207simulator.Player;
import com.example.csc207simulator.R;
import com.example.csc207simulator.SetupManagement.SettableActivity;
import com.example.csc207simulator.game3.GameThreeBackend.GameThreeEngine;


public class GameThreeDifficultyActivity extends SettableActivity {
    /**
     * Difficulty panel for game three to let user choose difficulty from three options
     */
    final Context temp = this;
    public static int equipmentremain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.appManager.deleteGameTwoManager();
        setContentView(R.layout.activity_game_three_difficulty);
        final Player curplayer = super.appManager.getCurrentPlayer();

        Button beginnerbutton = findViewById(R.id.beginnerbutton);
        beginnerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curplayer.addPerformance(2);
                equipmentremain = 2;
                openGamethreeActivity(4, 4, 4, 2);
            }
        });


        Button intermediatebutton = findViewById(R.id.intermediatebutton);
        intermediatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curplayer.addPerformance(4);
                equipmentremain = 4;
                openGamethreeActivity(6, 6, 9, 4);
            }
        });

        Button Hardcorebutton = findViewById(R.id.Hardcorebutton);
        Hardcorebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curplayer.addPerformance(6);
                equipmentremain = 8;
                openGamethreeActivity(8, 8, 16, 8);
            }
        });


    }


    /**
     * Open rule panel of GameThree with the difficulty chosen
     */
    private void openGamethreeActivity(int width, int height, int number, int equipment) {
        GameThreeEngine m = new GameThreeEngine(width, height, number, equipment);
        m.createGrid(temp);
        Intent intent = new Intent(this, GameThreeRuleActivity.class);
        super.attachToIntent(intent);
        startActivity(intent);
    }
    /**
     * Resets the user interface to reflect any changes from the backend.
     */
    @Override
    public void resetContentView() {
        setContentView(R.layout.activity_game_three_difficulty);
    }
}