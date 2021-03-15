package com.example.csc207simulator.MasterActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.csc207simulator.AppManager;

/**
 * A user interface with the ability to convert an AppManager object into a JsonString.
 */
public abstract class MasterActivity extends AppCompatActivity {
    /**
     * The key to retrieve a Json String from an intent.
     */
    public static final String APP_MANAGER = "appManager";

    /**
     * The Presenter for this MasterActivity.
     */
    protected MasterPresenter masterPresenter;

    /**
     * The AppManager, Model, for this MasterActivity.
     */
    protected AppManager appManager;

    /**
     * Creates the user interface.
     *
     * @param savedInstanceState the Bundle for this MasterActivity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.masterPresenter = new MasterPresenter(this, new MasterManager());
    }


    /**
     * Return a Json String representation of an AppManager.
     *
     * @param manager the AppManager to change into a Json String.
     * @return a Json String representation of an AppManager.
     */
    public String convertAppManagerToJson(AppManager manager) {
        return this.masterPresenter.convertAppManagerToJson(manager);
    }

    /**
     * Add an AppManager object represented as a Json String to an intent.
     *
     * @param intent the Intent to add the Json String to.
     */
    public void attachToIntent(Intent intent) {
        String appManagerData = this.convertAppManagerToJson(this.appManager);
        intent.putExtra(APP_MANAGER, appManagerData);
    }
}
