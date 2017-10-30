package com.software.design.realestateapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class HouseActivity extends AppCompatActivity implements VolleyResponce {

    ToggleButton toggleButton;
    String urlCheck = "http://lamp.ms.wits.ac.za/~s1037363/realestate_app/checkFavourites.php";
    String urlSet = "http://lamp.ms.wits.ac.za/~s1037363/realestate_app/insertFavourites.php";

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
        final String house_id = prevInt.getStringExtra("HOUSE_ID");
        final String user_id = prevInt.getStringExtra("USER_ID");

        ((TextView) findViewById(R.id.txtSubName)).setText(suburb);
        ((TextView) findViewById(R.id.txtPlotSize)).setText(plotArea);
        ((TextView) findViewById(R.id.txtNumBath)).setText(numBath);
        ((TextView) findViewById(R.id.txtNumBed)).setText(numBed);
        ((TextView) findViewById(R.id.txtPool)).setText(pool);
        ((TextView) findViewById(R.id.txtAddName)).setText(address);
        ((TextView) findViewById(R.id.txtNumGar)).setText(numGarage);
        ((TextView) findViewById(R.id.txtHouseSize)).setText(houseArea);
        ((TextView) findViewById(R.id.txtEval)).setText(evaluationAmo);

        toggleButton = (ToggleButton) findViewById(R.id.favourite_button);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    setFavourite(house_id,user_id);
            }
        });
        isFavourite(house_id,user_id);

    }

    public void isFavourite(String house_id, String user_id){

        Map<String, String> params = new HashMap<String, String>();
        params.put("HOUSE_ID", house_id);
        params.put("USER_ID", user_id);


        int key = 1;
        VolleyRequest volleyRequest = new VolleyRequest(urlCheck, params,this, key);
        volleyRequest.makeRequest();

    }

    public void setFavourite(String house_id, String user_id){
        Map<String, String> params = new HashMap<String, String>();
        params.put("HOUSE_ID", house_id);
        params.put("USER_ID", user_id);

        Toast.makeText(this, "Setting favourite", Toast.LENGTH_SHORT).show();
        //int key = 2;
        VolleyRequest volleyRequest = new VolleyRequest(urlSet, params,this, 2);
        volleyRequest.makeRequest();

        //toggleButton.setChecked(true);

    }

    @Override
    public void handleResponce(Object response, Map<String, String> map, int key) {
        System.out.println("Key is " + key);
        String c_response = (String) response;
        if (key == 1){
            if(c_response.contains("0")){
                toggleButton.setChecked(true);
            }
            else{
                toggleButton.setChecked(false);
            }
        }
        if(key == 2){
            Toast.makeText(this, "Volley", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void handleError(Object error, int key) {

    }

    @Override
    public Context getContext() {
        return this;
    }
}