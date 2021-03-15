package com.example.csc207simulator.game2.gametwoui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.csc207simulator.Ranking.ScoreboardActivity;
import com.example.csc207simulator.SetupManagement.SettableActivity;
import com.example.csc207simulator.Player;
import com.example.csc207simulator.R;
import com.example.csc207simulator.game2.gametwobackend.GameTwoManager;
import com.example.csc207simulator.game3.GameThreeUI.GameThreeDifficultyActivity;

import java.util.ArrayList;
import java.util.List;

public class GameTwoActivity extends SettableActivity {
    /**
     * The gametwomanager responsible for teachers'action in game 2
     */
    private GameTwoManager gameTwoManager;


    /**
     * The list controlling the appearance of the teachers' image button
     */
    private ArrayList<String> teacherVisibility;

    /**
     * The list responsible for controlling the button on the page
     */
    private List<ImageButton> buttons;


    /**
     * Click button method
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_two);
        Button button = findViewById(R.id.Nextgame);
        TextView knowledge = findViewById(R.id.knowledgeint2);
        Player player = super.appManager.getCurrentPlayer();
        int knowledgeInt = player.getKnowledge();
        knowledge.setText(Integer.toString(knowledgeInt));
        gameTwoManager = super.appManager.getGameTwoManager();
        teacherVisibility = getIntent().getStringArrayListExtra("teacher");
        buttons = new ArrayList<>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameTwoManager.isEmpty()){
                    openGameTwoBonusActivity();
                } else {
                    openGameThreeActivity();
                }
            }
        });

        ImageButton button1 = findViewById(R.id.person2);
        buttons.add(button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teacherVisibility.set(0, "true");// if the button is not clicked
                openGameTwoGuessActivity();
            }
        });


        ImageButton button2 = findViewById(R.id.person3);
        buttons.add(button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teacherVisibility.set(1, "true");// if the button is not clicked
                openGameTwoGuessActivity();

            }
        });

        ImageButton button3 = findViewById(R.id.person4);
        buttons.add(button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teacherVisibility.set(2, "true");// if the button is not clicked
                openGameTwoGuessActivity();
            }
        });


        ImageButton button4 = findViewById(R.id.person5);
        buttons.add(button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teacherVisibility.set(3, "true");// if the button is not clicked
                openGameTwoGuessActivity();

            }
        });

        ImageButton button5 = findViewById(R.id.person6);// if the button is not clicked
        buttons.add(button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teacherVisibility.set(4, "true");
                openGameTwoGuessActivity();
            }
        });

        setVisibility();

    }

    /**
     * Sets the visibility of the teacher ImageButtons.
     */
    void setVisibility(){
        for(int i = 0; i < buttons.size(); i++){
            ImageButton button = this.buttons.get(i);
            if(this.teacherVisibility.get(i).equalsIgnoreCase("false")){
                button.setVisibility(View.VISIBLE);
            }
            else{
                button.setVisibility(View.GONE);
            }
        }
    }

    /**
     * Open GameThreeActivity
     */
    public void openGameThreeActivity() {
        super.appManager.updateGame(1);
        Intent intent = new Intent(this, GameThreeDifficultyActivity.class);
        super.attachToIntent(intent);
        startActivity(intent);
    }

    /**
     * Open GameTwoGuessActivity
     */
    public void openGameTwoGuessActivity() {

        Intent intent = new Intent(this, GameTwoGuessActivity.class);
        super.attachToIntent(intent);
        intent.putStringArrayListExtra("teacher", this.teacherVisibility);
        startActivity(intent);
    }

    /**
     * Open GameTwoBonusActivity
     */
    public void openGameTwoBonusActivity() {

        Intent intent = new Intent(this, GameTwoBonusActivity.class);
        super.attachToIntent(intent);
        startActivity(intent);
    }

    /**
     * Resets the user interface to reflect any changes from the backend.
     */
    @Override
    public void resetContentView() {
        setContentView(R.layout.activity_game_two);
    }


}
