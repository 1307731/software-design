package com.software.design.realestateapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //initialize variables
    Button signUp;
    Button login;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //assign the signup button to the variable and initialize a listener for a click
        signUp = (Button)findViewById(R.id.button_signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create intent to switch to signup activity
                Intent changeToSignUp = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(changeToSignUp);
            }
        });

        login = (Button)findViewById(R.id.button_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = (EditText)findViewById(R.id.editText_Username_Login);
                password = (EditText)findViewById(R.id.editText_Password_Login);
                Toast toast = Toast.makeText(getApplicationContext(), username.getText().toString()+" "+ password.getText().toString(), Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}
