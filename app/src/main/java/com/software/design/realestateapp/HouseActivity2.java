package com.software.design.realestateapp;

/**
 * Created by Luca on 2017/10/30.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HouseActivity2 extends AppCompatActivity implements VolleyResponce{
    String fetchHouseURL = "http://lamp.ms.wits.ac.za/~s1037363/realestate_app/getHouseDetails.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);

        Intent prevInt = getIntent();
        String houseID = prevInt.getStringExtra("HOUSEID");
        System.out.println("House ID is :" +houseID);

        Map<String, String> params = new HashMap<String, String>();
        params.put("HOUSE_ID", houseID);

        Toast.makeText(getApplicationContext(), "Loading houses", Toast.LENGTH_LONG).show();

        int key =1;
        VolleyRequest volleyRequest = new VolleyRequest(fetchHouseURL, params, this, key);
        volleyRequest.makeRequest();

    }

    public void setTextViews(String address, String suburb,  String plotArea, String houseArea,  String numBed, String numBath, String numGarage, String pool,  String evaluationAmo)
    {
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

    @Override
    public void handleResponce(Object response, Map<String, String> map, int key)
    {
        try {
            String h_response = (String) response;
            System.out.println("Response is: " + h_response);
            JSONObject jsonObject = new JSONObject( h_response);
            JSONArray result= jsonObject.getJSONArray("SUBURB");
            System.out.println(result);
            JSONObject house = result.getJSONObject(0);

            String address = house.getString("ADDRESS");
            String suburb = house.getString("SUBURB");
            String numBath = house.getString("NUM_BATHROOMS");
            String numBed= house.getString("NUM_BEDROOMS");
            String plotArea= house.getString("PLOT_AREA");
            String numGarage= house.getString("NUM_GARAGES");
            String pool = house.getString("POOL");
            String houseArea=house.getString("HOUSE_AREA");
            String evaluationAmo=house.getString("EVALUATION");
            Toast.makeText(getApplicationContext(), "handled", Toast.LENGTH_LONG).show();
            setTextViews(address, suburb,  plotArea, houseArea,  numBed, numBath, numGarage, pool,  evaluationAmo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleError(Object error, int key) {
        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}

