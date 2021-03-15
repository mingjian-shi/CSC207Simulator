package com.example.csc207simulator.AccountManagement.AccountManagementUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.csc207simulator.AccountManagement.AccountManagementBackend.AccountManager;
import com.example.csc207simulator.AccountManagement.AccountPresenter;
import com.example.csc207simulator.MasterActivity.MasterActivity;
import com.example.csc207simulator.R;

/**
 * This activity is responsible for creating and storing new accounts.
 */
public class NewaccountActivity extends MasterActivity{

    private String userName;
    private String passWord;
    private AccountPresenter accountPresenter;
    private EditText userNameEditText;
    private EditText passWordEditText;
    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newaccount);

        Button button = findViewById(R.id.confirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });

    }


    /**
     * check whether the username & password combination is valid.
     */
    public void check() {
        userNameEditText = findViewById(R.id.username);
        passWordEditText = findViewById(R.id.password);
        progressBar = findViewById(R.id.progress);

        userName = userNameEditText.getText().toString();
        passWord = passWordEditText.getText().toString();

        Intent intent = getIntent();
        super.appManager = super.masterPresenter.retrieveAppManager(intent);


        accountPresenter = new AccountPresenter(this, super.appManager.getAccountMananger());
        accountPresenter.check(userName,passWord,"NewaccountCreation");

    }

    /**
     * Open LoginActivity
     */
    public void openLoginActivity() {
        accountPresenter.saveAccountToFile(this, accountPresenter.accountInitialization(userName,passWord));
        super.appManager.setAccountManager(accountPresenter.getAccountManager());

        Toast.makeText(this, "Congratulations on creating an account successfully! You can login with your account now!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, LoginActivity.class);
        super.attachToIntent(intent);
        startActivity(intent);
    }


    /**
     * Instead of reopen a new account activity, clear the username and password.
     */
    public void openNewaccountActivity() {
        Toast.makeText(this, "Your username and password combination is not acceptable, please use alternative combination", Toast.LENGTH_LONG).show();

        userNameEditText.setText("");
        passWordEditText.setText("");
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
