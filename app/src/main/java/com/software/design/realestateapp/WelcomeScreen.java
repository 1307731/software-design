package com.software.design.realestateapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class WelcomeScreen extends Activity {
    private boolean start = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        startActivity(new Intent(WelcomeScreen.this, LogInActivity.class));

        finish();
    }
}