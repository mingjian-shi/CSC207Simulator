package com.example.csc207simulator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.csc207simulator.AccountManagement.AccountManagementBackend.AccountManager;
import com.example.csc207simulator.AccountManagement.AccountManagementUI.LoginActivity;
import com.example.csc207simulator.MasterActivity.MasterActivity;

import java.io.File;

public class MainActivity extends MasterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the file inside internal storage
        AccountManager accountManager = new AccountManager(this);
        File file = new File(getApplicationContext().getFilesDir().getPath());
        if (file.length() == 0) {
            accountManager.newFile(this);
        }

    }

    /**
     * Open LoginActivity
     */
    public void openLoginActivity(View view) {

        Intent intent = new Intent(this, LoginActivity.class);
        super.appManager = new AppManager(this);
        super.attachToIntent(intent);
        startActivity(intent);
    }

}
