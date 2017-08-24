package com.software.design.realestateapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class WelcomeScreen extends Activity {
    private boolean start=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if(start == false)
                {
                    closeScreen();
                }
            }

        }, 10000);
    }



    public void clickImage(View view)
    {
        closeScreen();
    }

    public void closeScreen()
    {
        start=true;
        Intent logIn = new Intent(this, LogInActivity.class);
        startActivity(logIn);
        finish();
    }
}
