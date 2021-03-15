package com.example.csc207simulator.game3.GameThreeUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.csc207simulator.Player;
import com.example.csc207simulator.R;
import com.example.csc207simulator.SetupManagement.SettableActivity;


import static com.example.csc207simulator.game3.GameThreeBackend.GameThreeEngine.gm;


public class GameThreeActivity extends SettableActivity {
    /**
     * The main game Acitivity responsible for game three
     * */

    /**
     * Hidden action by clicking the door 10 times to skip game three
     */
    Context context = this;
    int clickedforskip = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_three);
        final Player player = super.appManager.getCurrentPlayer();

        ImageButton reset = findViewById(R.id.Gonextbutton);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gm.isFinished()) {
                    player.addScore(gm.getScore());
                    openFinalActivity();
                } else {
                    Toast.makeText(context, "The game is not finished yet", Toast.LENGTH_SHORT).show();
                }
                clickedforskip++;
                if(clickedforskip == 10){
                    openSkipActivity();
                }
            }
        });


    }

    /**
     * Open End of the game 3
     */
    private void openFinalActivity() {
        gm.setFinished(false);
        Intent intent = new Intent(this, GameThreeEndActivity.class);
        super.attachToIntent(intent);
        startActivity(intent);
    }

    /**
     * Open Skip room in game three
     */
    private void openSkipActivity() {
        gm.setFinished(false);
        Intent intent = new Intent(this, GameThreeSkipActivity.class);
        super.attachToIntent(intent);
        startActivity(intent);
    }
    /**
     * Resets the user interface to reflect any changes from the backend.
     */
    @Override
    public void resetContentView() {
        setContentView(R.layout.activity_game_three);
    }
}
