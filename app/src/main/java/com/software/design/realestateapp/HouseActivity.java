package com.software.design.realestateapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HouseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);

        Intent prevInt = getIntent();
        String address = prevInt.getStringExtra("ADDRESS");
        String suburb = prevInt.getStringExtra("SUBURB");
        String numBath = prevInt.getStringExtra("BATHROOMS_NUM");
        String numBed = prevInt.getStringExtra("BEDROOMS_NUM");
        String numGarage = prevInt.getStringExtra("GARAGES_NUM");
        String plotArea = prevInt.getStringExtra("PLOT_AREA");
        String pool = prevInt.getStringExtra("POOL");
        String houseArea = prevInt.getStringExtra("HOUSE_AREA");
        String evaluationAmo = prevInt.getStringExtra("EVALUATION_AMOUNT");

        ((TextView) findViewById(R.id.txtSubName)).setText(suburb);
        ((TextView) findViewById(R.id.txtPlotSize)).setText(plotArea);
        ((TextView) findViewById(R.id.txtNumBath)).setText(numBath);
        ((TextView) findViewById(R.id.txtNumBed)).setText(numBed);
        ((TextView) findViewById(R.id.txtPool)).setText(pool);
        ((TextView) findViewById(R.id.txtAddName)).setText(address);
        ((TextView) findViewById(R.id.txtNumGar)).setText(numGarage);
        ((TextView) findViewById(R.id.txtHouseSize)).setText(houseArea);
        ((TextView) findViewById(R.id.txtEval)).setText(evaluationAmo);

    }
}