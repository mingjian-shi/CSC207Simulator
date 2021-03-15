package com.example.csc207simulator.SetupManagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.csc207simulator.R;

/**
 * This activity is responsible for changing the setup and linkage to AppManager.
 */
public class SetupActivity extends SettableActivity{

    /**
     * The SetupPresenter for this SetupActivity.
     */
    private SetupPresenter setupPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        this.setupPresenter = new SetupPresenter(this, new SetupManager(appManager));
    }

    /**
     * Start playing game.
     *
     * @param view an object connected to this method that was clicked in the UI.
     */
    public void playGame(View view) {
        this.setupPresenter.playGame();
    }

    /**
     * Creates the intent for the specific game that player has last left off from.
     *
     * @param activity the activity to start.
     */
    public void createIntent(Class activity) {
        super.appManager.updateSetUp(super.setup);
        Intent intent = new Intent(this, activity);
        super.attachToIntent(intent);
        startActivity(intent);
    }

    /**
     * Resets the user interface to reflect any changes from the backend.
     */
    public void resetContentView() {
        setContentView(R.layout.activity_setup);
    }

}
