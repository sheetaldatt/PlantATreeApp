package com.example.ben.carttotalconfirm.activity;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ben.carttotalconfirm.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;


public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setTitle("Payment Method"); //  set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // add back arrow in action bar


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        configureBackButton();
        configureContinueButton();
       // Log.d("myTag","hi");
        saveVisaInfo();
    }

    // creates a back button that returns to Cart Total Activity
    private void configureBackButton(){
        Button backButton = (Button) findViewById(R.id.visaBackToCartButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override

            //Ends this activity and returns to previous activity
            public void onClick(View view){
                finish();
            }
        });
    }

    // creates a continue button that connects to Payment Activity
    private void configureContinueButton(){
        Button nextButton = (Button) findViewById(R.id.VisaContinueButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override

            //start the Payment Activity and link the current activity to it
            public void onClick(View view){
                startActivity(new Intent(PaymentActivity.this,OrderConfirmActivity.class));
            }
        });
    }

    //if user checks switch to save card info, store in db
    //TODO implement database insert
    private void saveVisaInfo(){
        Switch visaSwitch = findViewById(R.id.SaveVisa);
        visaSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //currently only sends toast to user confirming detail save
                    Toast.makeText(getApplicationContext(), "Payment Information Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}