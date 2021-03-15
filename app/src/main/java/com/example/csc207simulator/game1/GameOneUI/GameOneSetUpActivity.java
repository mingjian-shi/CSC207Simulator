package com.example.csc207simulator.game1.GameOneUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.csc207simulator.R;
import com.example.csc207simulator.SetupManagement.SettableActivity;

/**
 * The first gaming activity in this app, introduces the rules.
 */
public class GameOneSetUpActivity extends SettableActivity {

    /**
     * Create user's interface.
     * @param savedInstanceState the Bundle for this SettableActivity.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.appManager.getCurrentPlayer().clearall();
        setContentView(R.layout.activity_gameone_set_up_activity);
        Button startGameOne = findViewById(R.id.StartGameOne);
        startGameOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
    }

    /**
     * Start the next activity(To play game1).
     */
    public void start() {
        Intent intent = new Intent(this, GameOneActivity.class);
        super.attachToIntent(intent);
        startActivity(intent);
    }

    /**
     * Resets the user interface to reflect any changes from the backend.
     */
    @Override
    protected void resetContentView() {
        setContentView(R.layout.activity_gameone_set_up_activity);
    }
}


