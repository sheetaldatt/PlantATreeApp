

package com.example.theshrubs.plantatree.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import com.example.theshrubs.plantatree.R;
import android.view.View;

import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.database.UserTable;
import com.example.theshrubs.plantatree.models.User;

public class UserLoginActivity extends AppCompatActivity implements View.OnClickListener {

        private final AppCompatActivity activity = UserLoginActivity.this;

        private NestedScrollView nestedScrollView;

        private TextInputLayout textInputLayoutUsername;
        private TextInputLayout textInputLayoutPassword;

        private TextInputEditText textInputEditTextUsername;
        private TextInputEditText textInputEditTextPassword;

        private AppCompatButton appCompatButtonLogin;

        private AppCompatTextView textViewLinkRegister;

        private InputValidation inputValidation;
        private DatabaseHelper databaseHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
//        getSupportActionBar().hide();

            initViews();
            initListeners();
            initObjects();
        }
        @SuppressLint("WrongViewCast")
        private void initViews(){
            nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

            textInputLayoutUsername = (TextInputLayout) findViewById(R.id.textInputLayoutUsername);
            textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

            textInputEditTextUsername = (TextInputEditText) findViewById(R.id.textInputEditTextUsername);
            textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

            appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);

            textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);
        }

        private void initListeners(){
            appCompatButtonLogin.setOnClickListener(this);
            textViewLinkRegister.setOnClickListener(this);
        }

        private void initObjects(){
            databaseHelper = new DatabaseHelper(activity);
            inputValidation = new InputValidation(activity);
        }

        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.appCompatButtonLogin:
                    verifyFromSQLite();
                    break;
                case R.id.textViewLinkRegister:
                    Intent intentRegister = new Intent(getApplicationContext(), UserRegistrationActivity.class);
                    startActivity(intentRegister);
                    break;
            }
        }

        private void verifyFromSQLite(){
            if (!inputValidation.isInputEditTextFilled(textInputEditTextUsername, textInputLayoutUsername, getString(R.string.error_message_email))) {
                return;
            }
            if (!inputValidation.isInputEditTextEmail(textInputEditTextUsername, textInputLayoutUsername, getString(R.string.error_message_email))) {
                return;
            }
            if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
                return;
            }
//    }

            UserTable userTable = new UserTable();
            User user = userTable.checkLogin(textInputEditTextUsername.getText().toString().trim(), textInputEditTextPassword.getText().toString().trim(), databaseHelper);



                System.out.println("Login User " + textInputEditTextUsername.getText().toString().trim() + " pass " +  textInputEditTextPassword.getText().toString().trim());
            if (user != null) {
                System.out.println("found user with id "+ user.getUserID());
                Intent accountsIntent = new Intent(activity, LandingPageActivity.class);
                accountsIntent.putExtra("EMAIL", textInputEditTextUsername.getText().toString().trim());
                accountsIntent.putExtra("USER_ID", user.getUserID());
                emptyInputEditText();
                startActivity(accountsIntent);
            } else {

                Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
            }
        }

        private void emptyInputEditText(){
            textInputEditTextUsername.setText(null);
            textInputEditTextPassword.setText(null);
        }
    }