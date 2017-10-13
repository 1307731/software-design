package com.software.design.realestateapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class EvaluationActivity extends AppCompatActivity{

    //declare on screen element variables
    EditText address, floors, plotArea, houseArea, bathrooms, bedrooms, garages, suburb;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        //assign screen element variables to fields
        address = (EditText) findViewById(R.id.edAddress);
        // floors = (EditText)findViewById(R.id.editText_floors);
        plotArea = (EditText)findViewById(R.id.edSizePlot);
        houseArea = (EditText)findViewById(R.id.edSizeHouse);
        bathrooms = (EditText)findViewById(R.id.edNumBath);
        bedrooms = (EditText)findViewById(R.id.edNumBed);
        garages = (EditText)findViewById(R.id.edNumGarages);
        //php url
        url = "http://lamp.ms.wits.ac.za/~s1037363/realestate_app/insertHouse.php";

    }

    public void addHouse(View view){
        //gets current value from fields
        final String addressData = address.getText().toString().trim();
        final String floorsData = floors.getText().toString().trim();
        final String plotAreaData = plotArea.getText().toString().trim();
        final String houseAreaData = houseArea.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("address", addressData);
                params.put("no_floors", floorsData);
                params.put("plot_area", plotAreaData);
                params.put("house_area", houseAreaData);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


        //Intent startNewActivity = new Intent(this,RoomActivity.class);

        //startActivity(startNewActivity);
    }


}
