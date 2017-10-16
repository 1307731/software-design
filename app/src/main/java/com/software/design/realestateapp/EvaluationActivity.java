package com.software.design.realestateapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EvaluationActivity extends AppCompatActivity{

    //declare on screen element variables
    EditText address, suburb, plotArea, houseArea, numBath, numBed, numGarage;


    boolean checked =false;
    String insertUrl = "http://lamp.ms.wits.ac.za/~s1037363/realestate_app/insertHouse2.php", weightsUrl, subUrl = "http://lamp.ms.wits.ac.za/~s1037363/realestate_app/getSuburbPrice.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        //assign screen element variables to fields
        address = (EditText) findViewById(R.id.edAddress);
        suburb = (EditText)findViewById(R.id.edSuburb);
        numBath = (EditText)findViewById(R.id.edNumBath);
        numBed = (EditText)findViewById(R.id.edNumBed);
        numGarage = (EditText)findViewById(R.id.edNumGarages);
        plotArea = (EditText)findViewById(R.id.edSizePlot);
        houseArea = (EditText)findViewById(R.id.edSizeHouse);

        //loads weights to be used
        //loadWeights(weightsArr);
        //php url


    }

    public void sendInfo(final String suburbData,  final String addressData,final String bedData,final String bathData,final String plotAreaData,final String houseAreaData,final String garageData,final boolean bPoolData, final String evaluationData)
    {
        System.out.println("Suburb is " + suburbData);
        int check=0;
        if(bPoolData)
        {
            check=1;
        }
        final int poolData=check;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, insertUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
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
                params.put("ADDRESS", addressData);
                params.put("SUBURB", suburbData);
                params.put("PLOT_AREA", plotAreaData);
                params.put("HOUSE_AREA", houseAreaData);
                params.put("BEDROOMS_NUM", bedData);
                params.put("BATHROOMS_NUM", bathData);
                params.put("GARAGES_NUM", garageData);
                params.put("POOL", poolData+"");
                params.put("EVALUATION_AMOUNT", evaluationData);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


        Intent startNewActivity = new Intent(this,HouseActivity.class);
        startNewActivity.putExtra("ADDRESS", addressData);
        startNewActivity.putExtra("SUBURB", suburbData);
        startNewActivity.putExtra("PLOT_AREA", plotAreaData);
        startNewActivity.putExtra("HOUSE_AREA", houseAreaData);
        startNewActivity.putExtra("BEDROOMS_NUM", bedData);
        startNewActivity.putExtra("BATHROOMS_NUM", bathData);
        startNewActivity.putExtra("GARAGES_NUM", garageData);
        startNewActivity.putExtra("POOL", poolData+"");
        startNewActivity.putExtra("EVALUATION_AMOUNT", evaluationData);
        startActivity(startNewActivity);
    }

    public void doEvaluation(View view) //gets current value from fields
    {

        final String suburbData = suburb.getText().toString().trim();
        final String addressData = address.getText().toString().trim();
        final String bedData = numBed.getText().toString().trim();
        final String bathData = numBath.getText().toString().trim();
        final String plotAreaData = plotArea.getText().toString().trim();
        final String houseAreaData = houseArea.getText().toString().trim();
        final String garageData = numGarage.getText().toString().trim();
        final boolean poolData = checked;

        //int subPrice = fetchSuburbPrice(suburbData);
        int subPrice=1000000;
        double weights[]=new double[10];
        loadWeights(weights);
        int evalAmount = evaluate(subPrice, addressData, bedData, bathData, plotAreaData,houseAreaData,garageData,poolData, weights);
        sendInfo(suburbData, addressData, bedData, bathData, plotAreaData,houseAreaData,garageData,poolData,(evalAmount+""));
        int num = 100;
        changeNum(num);

        System.out.println(num);

    }

    public void loadWeights(double [] weightsArray)
    {
        ///weights are suburb, plot, house,bath, bed, garages, pool
        weightsArray[0]=0.7;
        weightsArray[1]=0.3;
        weightsArray[2]=0.3;
        weightsArray[3]=0.1;
        weightsArray[4]=0.1;
        weightsArray[5]=0.1;
        weightsArray[6]=0.05;
    }

    public int evaluate(int e_subPrice, String e_addressData, String e_bedData, String e_bathData, String e_plotAreaData, String e_houseAreaData, String e_garageData, boolean e_poolData, double [] e_weights)
    {
        double total =0;
        int poolPres=0;
        if(e_poolData)
        {
            poolPres=1;
        }
        total=e_subPrice*e_weights[0]+Double.parseDouble(e_plotAreaData)*e_weights[1]+Double.parseDouble(e_houseAreaData)*e_weights[2]+Double.parseDouble(e_bathData)*e_weights[3]+Double.parseDouble(e_bedData)*e_weights[4]+Double.parseDouble(e_garageData)*e_weights[5]+poolPres*e_weights[6];
        double normalized=e_subPrice*e_weights[0]+1000*e_weights[1]+700*e_weights[2]+1*e_weights[3]+1*e_weights[4]+1*e_weights[5]+1*e_weights[6];
        total=total/normalized;
        total=total*e_subPrice+400000;

        System.out.println("Total weight "+total);
        return (int) Math.round(total);
    }

    public void uploadEvaluation(String u_suburbData, String u_addressData, String u_bedData, String u_bathData, String u_plotAreaData, String u_houseAreaData, String u_garageData, boolean u_poolData)
    {

    }

    public void changeNum(int num)
    {
        num=100;
    }

    public int fetchSuburbPrice(String suburbData)
    {

        int subPric=-1;
        StringRequest stringRequest = new StringRequest(subUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("This is the response");
                System.out.println(response);
                String subPrice="";
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("result");
                    System.out.println(result);
                    JSONObject suburb = result.getJSONObject(0);
                    subPrice = suburb.getString("AVG_PRICE");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                final String price = subPrice;
                System.out.println(price);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        return subPric;
    }

    public void doCheck(View view)
    {
        checked = ((CheckBox) view).isChecked();
        System.out.println("Check is clicked and is "+checked);
    }


}
