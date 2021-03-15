package com.example.csc207simulator.MasterActivity;

import android.content.Intent;

import com.example.csc207simulator.AppManager;
import com.google.gson.Gson;

/**
 * A class representing the function of converting Objects to Json format.
 */
class JsonConverter {

    /**
     * Return an AppManager object converted from a Json String.
     *
     * @param intent the Intent the Json String was added to.
     * @return a AppManager converted from a Json String.
     */
    AppManager retrieveAppManager(Intent intent) {
        String appManagerData = intent.getStringExtra(MasterActivity.APP_MANAGER);
        Gson gson = new Gson();
        return gson.fromJson(appManagerData, AppManager.class);
    }

    /**
     * Return a Json String converted from an AppManager object.
     *
     * @param manager the AppManager to convert to a Json String.
     * @return a Json String converted from an AppManager object.
     */
    String convertAppManagerToJson(AppManager manager) {
        Gson gson = new Gson();
        return gson.toJson(manager);
    }
}
