package com.example.csc207simulator.game2.gametwoui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.csc207simulator.Player;
import com.example.csc207simulator.R;
import com.example.csc207simulator.SetupManagement.SettableActivity;
import java.util.ArrayList;

/**
 * This activity shows the rules for this game.
 */
public class GameTwoSetUpActivity extends SettableActivity {

    /**
     * Click button method
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_two_set_up_acitivity);
        TextView knowledge = findViewById(R.id.knowledgeint4);
        super.appManager.createGameTwoManager();
        Player player = super.appManager.getCurrentPlayer();
        int knowledgeInt = player.getKnowledge();
        knowledge.setText(Integer.toString(knowledgeInt));

        Button button = findViewById(R.id.begin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameTwoActivity();
            }
        });
    }

    /**
     * Open GameTwoActivity
     */
    public void openGameTwoActivity() {

        Intent intent = new Intent(this, GameTwoActivity.class);
        super.attachToIntent(intent);
        ArrayList<String> hasClicked = createList();
        intent.putStringArrayListExtra("teacher", hasClicked);
        startActivity(intent);
    }

    /**
     * Returns a list representing which teacher has been clicked.
     * @return a list representing which teacher has been clicked.
     */
    private ArrayList<String> createList(){
        ArrayList<String> toReturn = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            toReturn.add("false");
        }
        return toReturn;
    }

    /**
     * Resets the user interface to reflect any changes from the backend.
     */
    @Override
    public void resetContentView() {
        setContentView(R.layout.activity_game_two_set_up_acitivity);
    }
}
