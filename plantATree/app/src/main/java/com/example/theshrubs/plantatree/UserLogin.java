package com.example.theshrubs.plantatree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theshrubs.plantatree.activity.LandingPageActivity;
import com.example.theshrubs.plantatree.activity.UserRegistrationActivity;
import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.User;

public class UserLogin extends AppCompatActivity implements View.OnClickListener{

    private Button loginButton;
    private EditText textUsername;
    private EditText textPassword;
    private TextView registerLink;
    private DatabaseHelper dbHelper;
    private int USERID;
//    private Toast

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        this.loginButton = (Button) findViewById(R.id.login_button);
        this.textUsername = (EditText) findViewById(R.id.user_name);
        this.textPassword = (EditText) findViewById(R.id.user_password);
        this.registerLink = (TextView) findViewById(R.id.regiser_link);

        this.loginButton.setOnClickListener(this);
        this.registerLink.setOnClickListener(this);
        dbHelper = new DatabaseHelper(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.login_button){
            String username = textUsername.getText().toString().trim();
            String password = textPassword.getText().toString().trim();

            if(username.isEmpty() || password.isEmpty()){
                showToast("Please make sure all fields are filled out");
            }else{
                dbHelper.setUser(username, password);
                Object obj = dbHelper.findHandle(1, "findExistingUser");
                if(obj == null){
                    showToast("Invalid username or password");
                }
            }
        }else if(v.getId() == R.id.regiser_link){
            Intent intent = new Intent(UserLogin.this, UserRegistrationActivity.class);
            startActivity(intent);
        }

    }

    private void showToast(String message){
        Toast noMatch = Toast.makeText(UserLogin.this, message, Toast.LENGTH_SHORT);
        noMatch.show();

    }
}