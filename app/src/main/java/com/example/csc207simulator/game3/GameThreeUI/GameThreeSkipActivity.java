package com.example.csc207simulator.game3.GameThreeUI;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;


import com.example.csc207simulator.R;
import com.example.csc207simulator.SetupManagement.SettableActivity;
public class GameThreeSkipActivity extends SettableActivity{
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * The skip room which is entered by hidden action
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_three_skip);



        Button gobackbutton = findViewById(R.id.gobackbutton);
        gobackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameThreeActivity();
            }
        });

        Button leavebutton = findViewById(R.id.Leaveskipbutton);
        leavebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameThreeEndActivity();
            }
        });


    }
    /**
     * Skip to the end of Game three
     */
    private void openGameThreeEndActivity() {
        Intent intent = new Intent(this, GameThreeEndActivity.class);
        super.attachToIntent(intent);
        startActivity(intent);
    }
    /**
     * go back to game three
     */
    private void openGameThreeActivity() {
        Intent intent = new Intent(this, GameThreeActivity.class);
        super.attachToIntent(intent);
        startActivity(intent);
    }


    /**
     * Resets the user interface to reflect any changes from the backend.
     */
    @Override
    public void resetContentView() {
        setContentView(R.layout.activity_game_three_rule);
    }
}
