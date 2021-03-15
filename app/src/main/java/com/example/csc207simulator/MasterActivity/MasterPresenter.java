package com.example.csc207simulator.MasterActivity;

import android.content.Intent;

import com.example.csc207simulator.AppManager;

/**
 * A class representing the Presenter for a MasterView.
 */
public class MasterPresenter {

    /**
     * The MasterView for this MasterPresenter.
     */
    //currently is not used but may be useful in the future for extensibility.
    private MasterActivity masterActivity;

    /**
     * The MasterManager for this MasterPresenter.
     */
    private MasterManager masterManager;

    /**
     * Creates and instance of MasterPresenter.
     *
     * @param masterActivity    the MasterActivity to link to this MasterPresenter.
     * @param masterManager the MasterManager to link to this MasterPresenter.
     */
    MasterPresenter(MasterActivity masterActivity, MasterManager masterManager) {
        this.masterManager = masterManager;
        this.masterActivity = masterActivity;
    }


    /**
     * Returns the AppManager converted from a Json String.
     *
     * @param intent the Intent the AppManager is added to.
     * @return the AppManager converted from a Json String.
     */
    public AppManager retrieveAppManager(Intent intent) {
        return this.masterManager.retrieveAppManager(intent);
    }

    /**
     * Return a converted AppManager manager into a Json String.
     *
     * @param manager the AppManager to convert.
     * @return a converted AppManager as a Json String.
     */
    String convertAppManagerToJson(AppManager manager) {
        return this.masterManager.convertAppManagerToJson(manager);
    }
}
