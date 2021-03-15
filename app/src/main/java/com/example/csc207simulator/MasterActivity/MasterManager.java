package com.example.csc207simulator.MasterActivity;

import android.content.Intent;

import com.example.csc207simulator.AppManager;

/**
 * A class representing the Model for a MasterView. This class manages use cases for MasterActivity.
 */
class MasterManager {

    /**
     * The converter used to change an AppManager object into a Json String.
     */
    private JsonConverter jsonConverter;

    /**
     * Creates an instance of MasterManager.
     */
    MasterManager() {
        this.jsonConverter = new JsonConverter();
    }

    /**
     * Return an AppManager converted from a Json String.
     *
     * @param intent the Intent the Json String is added to.
     * @return an AppManager converted from a Json String.
     */
    AppManager retrieveAppManager(Intent intent) {
        return jsonConverter.retrieveAppManager(intent);
    }

    /**
     * Return a Json String converted from an AppManager.
     *
     * @param manager the AppManager to convert into a Json String.
     * @return a Json String converted from an AppManager.
     */
    String convertAppManagerToJson(AppManager manager) {
        return jsonConverter.convertAppManagerToJson(manager);
    }
}
