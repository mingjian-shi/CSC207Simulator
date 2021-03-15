package com.example.csc207simulator.game3.GameThreeUI;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.csc207simulator.AccountManagement.AccountManagementUI.LoginActivity;
import com.example.csc207simulator.R;
import com.example.csc207simulator.Ranking.ScoreboardActivity;
import com.example.csc207simulator.SetupManagement.SettableActivity;

public class GameThreeEndActivity extends SettableActivity {
    @Override
    /**
     * The Activity end of game three
     */
    /**
     * Hidden action of exchanging items user obtained from games three with scores
     */
    protected void onCreate(Bundle savedInstanceState) {
//        AppManager.updateGame(2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_three_end);

        ImageView secreten = findViewById(R.id.Secretentrance);
        secreten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("clicked");
                openSecretActivity();
            }
        });

        Button reset = findViewById(R.id.resetbutton);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScoreboarnActivity();
            }
        });

        Button logout = findViewById(R.id.logoututton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlogoutActivity();
            }
        });


        TextView highestscore = findViewById(R.id.Scorepad);
        int temp = super.appManager.getCurrentAccount().getPlayer().getHighestScore();
        String last = Integer.valueOf(temp).toString();
        highestscore.setText(last);
        //score.setText("999999");

        TextView score = findViewById(R.id.currentscore);
        int currenttemp = super.appManager.getCurrentAccount().getPlayer().getScore();
        String curr = Integer.valueOf(currenttemp).toString();
        score.setText(curr);

        TextView performancepanel = findViewById(R.id.finalperformance);
        int currentperformance = super.appManager.getCurrentAccount().getPlayer().getPerformance();
        String rank = Integer.valueOf(currentperformance).toString();
        performancepanel.append(rank);
    }

    /**
     * Open Scoreboard
     */
    private void openScoreboarnActivity() {
        Intent intent = new Intent(this, ScoreboardActivity.class);
        super.attachToIntent(intent);
        startActivity(intent);
    }

    /**
     * Logout of account and open login page
     */
    private void openlogoutActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        super.attachToIntent(intent);
        startActivity(intent);
    }

    /**
     * Enter the secret room
     */
    private void openSecretActivity() {
        Intent intent = new Intent(this, GameThreeSecretActivity.class);
        super.attachToIntent(intent);
        startActivity(intent);
    }
    /**
     * Resets the user interface to reflect any changes from the backend.
     */
    @Override
    public void resetContentView() {
        setContentView(R.layout.activity_game_three_end);
    }
}
