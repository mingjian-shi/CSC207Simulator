package com.example.csc207simulator.AccountManagement.AccountManagementUI;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.csc207simulator.AccountManagement.AccountPresenter;
import com.example.csc207simulator.MasterActivity.MasterActivity;
import com.example.csc207simulator.R;
import com.example.csc207simulator.SetupManagement.SetupActivity;

/**
 * This activity is responsible for Login
 */
public class LoginActivity extends MasterActivity{

    private AccountPresenter accountPresenter;
    private EditText userNameEditText;
    private EditText passWordEditText;
    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button1 = findViewById(R.id.newaccount);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewaccountActivity();
            }
        });

        Button button2 = findViewById(R.id.login);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });

    }

    /**
     * check whether this is an existing user.
     */
    public void check() {

        userNameEditText = findViewById(R.id.username);
        passWordEditText = findViewById(R.id.password);
        progressBar = findViewById(R.id.progress);

        String userName = userNameEditText.getText().toString();
        String passWord = passWordEditText.getText().toString();


        Intent intent = getIntent();

        super.appManager = super.masterPresenter.retrieveAppManager(intent);
        super.appManager.setAccountManagerUserName(userName);
        super.appManager.setAccountManagerPassWord(passWord);


        accountPresenter = new AccountPresenter(this, super.appManager.getAccountMananger());
        accountPresenter.check(userName, passWord,"Login");
    }

    /**
     * Open NewaccountActivity
     */
    public void openNewaccountActivity() {
        Intent intent = new Intent(this, NewaccountActivity.class);
        intent.putExtra(MasterActivity.APP_MANAGER,getIntent().getStringExtra(MasterActivity.APP_MANAGER));
        startActivity(intent);
    }

    /**
     * Open LoginActivity
     */
    public void openLoginActivity() {
        Toast.makeText(this, "Your username and password combination is not valid, please enter again", Toast.LENGTH_LONG).show();

        userNameEditText.setText("");
        passWordEditText.setText("");
    }

    /**
     * Open SetupActivity
     */
    public void openSetupActivity() {
        accountPresenter.load(this);
        Toast.makeText(this, "Welcome to the game!", Toast.LENGTH_LONG).show();
        super.appManager.updateAccountManager();
        Intent newIntent = new Intent(this, SetupActivity.class);
        super.attachToIntent(newIntent);
        startActivity(newIntent);
    }

    /**
     * set progress bar visible
     */
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * set progress bar gone
     */
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }


}
