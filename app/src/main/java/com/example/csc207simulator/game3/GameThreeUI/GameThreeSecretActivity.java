package com.example.csc207simulator.game3.GameThreeUI;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;


import com.example.csc207simulator.Player;
import com.example.csc207simulator.R;
import com.example.csc207simulator.SetupManagement.SettableActivity;
import static com.example.csc207simulator.game3.GameThreeBackend.GameThreeEngine.gm;


public class GameThreeSecretActivity extends SettableActivity {
    /**
     * The secret Activity that is approached by hidden action
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_three_secret);

        final Player player = super.appManager.getCurrentPlayer();
        final TextView secretnum = findViewById(R.id.Numberofsecret);
        String numofsecret = Integer.valueOf(gm.getSecret()).toString();
        secretnum.setText(numofsecret);

        Button exbutton = findViewById(R.id.Exchangebutton);
        exbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gm.getSecret()==0){
                    if (setup.getIsEnglish().equals("en")) {
                        Toast.makeText(context, "You don't have books of secret", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "你没有秘密之书", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    gm.addscore(100 * gm.getSecret());
                    gm.subSecret(gm.getSecret());
                    secretnum.setText('0');
                }
            }
        });

        Button leavebutton = findViewById(R.id.LeaveButton);
        leavebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.addScore(gm.getScore());
                openGameThreeEndActivity();
            }
        });


    }
    /**
     * Go back to the end of Game three
     */
    private void openGameThreeEndActivity() {
        Intent intent = new Intent(this, GameThreeEndActivity.class);
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
