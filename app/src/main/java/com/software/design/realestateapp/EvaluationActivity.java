package com.software.design.realestateapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EvaluationActivity extends AppCompatActivity implements VolleyResponce {

    //declare on screen element variables

    EditText address, suburb, plotArea, houseArea, numBath, numBed, numGarage;
    String userID, suburbData, addressData, bedData, bathData, plotAreaData, houseAreaData, garageData;
    double avgBed, avgBath, avgPlot, avgHouse, avgGarage;
    boolean poolData;
    int evalAmountTest, evaluationAmount;

    double suburbPri = -1;
    double[] weights = new double[7];
    boolean checked = false;
    String insertUrl = "http://lamp.ms.wits.ac.za/~s1037363/realestate_app/insertHouse2.php", avgUrl = "http://lamp.ms.wits.ac.za/~s1037363/realestate_app/getAverageSoldHouses.php", weightsUrl = "http://lamp.ms.wits.ac.za/~s1037363/realestate_app/getWeights.php", subUrl = "http://lamp.ms.wits.ac.za/~s1037363/realestate_app/getSuburbPrice.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        //assign screen element variables to fields
        address = (EditText) findViewById(R.id.edAddress);

        suburb = (EditText) findViewById(R.id.edSuburb);
        numBath = (EditText) findViewById(R.id.edNumBath);
        numBed = (EditText) findViewById(R.id.edNumBed);
        numGarage = (EditText) findViewById(R.id.edNumGarages);
        plotArea = (EditText) findViewById(R.id.edSizePlot);
        houseArea = (EditText) findViewById(R.id.edSizeHouse);
        userID = getIntent().getStringExtra("USER_ID");
        //loads weights to be used
        //loadWeights(weightsArr);

        //php urlCheck


    }

    /*@Override
    public void handleResponce(Object response, int key) {
        if (key == 1) {

            Toast.makeText(getApplicationContext(), "handled", Toast.LENGTH_LONG).show();
            //Suburb price stuff here
            //KEY IS SO THAT BOTH VOLLEY REQUESTS CAN CALL THE SAME FUNCTION, BUT DO DIFFERENT THINGS. NOT IDEAL, BUT I DONT HAVE TIME TO DO IT PROPERLY
            //CAST IT BEFORE, ITS A BASIC OBJECT SO THAT MANY DIFFERENT THINGS CAN USE IT AS THEY NEED IT

            //(JSONArray) response ........
        }
        if (key == 2) {
            Toast.makeText(getApplicationContext(), "handled", Toast.LENGTH_LONG).show();
        }

    }*/

    public void sendInfo(final String suburbData, final String addressData, final String bedData, final String bathData, final String plotAreaData, final String houseAreaData, final String garageData, final boolean bPoolData, final String evaluationData, boolean isTest) {


        System.out.println("Suburb is " + suburbData);
        int check = 0;
        if (bPoolData) {
            check = 1;
        }
        int poolData = check;
        Map<String, String> params = new HashMap<String, String>();
        params.put("USER_ID", userID);
        params.put("ADDRESS", addressData);
        params.put("SUBURB", suburbData);
        params.put("PLOT_AREA", plotAreaData);
        params.put("HOUSE_AREA", houseAreaData);
        params.put("BEDROOMS_NUM", bedData);
        params.put("BATHROOMS_NUM", bathData);
        params.put("GARAGES_NUM", garageData);
        params.put("POOL", poolData + "");
        params.put("EVALUATION_AMOUNT", evaluationData);
        int key = 2;
        VolleyRequest volleyRequest = new VolleyRequest(insertUrl, params, this, key);
        if (!isTest) {
            volleyRequest.makeRequest();
        }
    }

    public void doEvaluation(View view) //gets current value from fields
    {

        doEvaluationTestable(false);
    }

    public void getAverages() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("SUBURB", suburbData);
        int key = 4;
        VolleyRequest volleyRequest = new VolleyRequest(avgUrl, params, this, key);
        volleyRequest.makeRequest();
    }

    public void doEvaluationTestable(boolean isTest) {
        suburbData = suburb.getText().toString().trim();
        addressData = address.getText().toString().trim();
        bedData = numBed.getText().toString().trim();
        bathData = numBath.getText().toString().trim();
        plotAreaData = plotArea.getText().toString().trim();
        houseAreaData = houseArea.getText().toString().trim();
        garageData = numGarage.getText().toString().trim();
        poolData = checked;


        loadWeights(isTest, new double[7]);
        ///fetchSuburbPrice(suburbData);

    }

    public void loadWeights(Object... a) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("Sexy", "Luca");

        Boolean isTest = (Boolean) a[0];
        System.out.println(isTest.toString());

        int key = 3;
        VolleyRequest volleyRequest = new VolleyRequest(weightsUrl, params, this, key);
        if (!isTest) {
            volleyRequest.makeRequest();
        } else {
            ///weights are suburb, plot, house,bath, bed, garages, pool
            double[] weightsArray = (double[]) a[1];
            weightsArray[0] = 0.7;
            weightsArray[1] = 0.3;
            weightsArray[2] = 0.3;
            weightsArray[3] = 0.1;
            weightsArray[4] = 0.1;
            weightsArray[5] = 0.1;
            weightsArray[6] = 0.05;
        }
    }

    public int evaluate(int e_subPrice, String e_addressData, String e_bedData, String e_bathData, String e_plotAreaData, String e_houseAreaData, String e_garageData, boolean e_poolData, double[] e_weights) {
        double total = 0;
        int poolPres = 0;
        if (e_poolData) {
            poolPres = 1;
        }

        double sPrice=e_subPrice, bed=Double.parseDouble(e_bedData), garage=Double.parseDouble(e_garageData), bath=Double.parseDouble(e_bathData), house =Double.parseDouble(e_houseAreaData), plot = Double.parseDouble(e_plotAreaData);
        bed = bed / avgBed;
        bath = bath / avgBath;
        garage = garage / avgGarage;
        house = house / avgHouse;
        plot = plot / avgPlot;
        total = e_subPrice*e_weights[0];
        System.out.println("Current for e sub is :" + total);
        System.out.println("Sub price " + e_subPrice);
        double totalWeight = (plot*e_weights[1])+(house*e_weights[2])+(bath*e_weights[3])+(bed*e_weights[4])+(garage*e_weights[5])+poolPres*e_weights[6];
        total=total+ ((e_subPrice-total)*totalWeight);
      
        //double normalized=e_subPrice*e_weights[0]+1000*e_weights[1]+700*e_weights[2]+1*e_weights[3]+1*e_weights[4]+1*e_weights[5]+1*e_weights[6];
        // total=total/normalized;
        //total=total*e_subPrice+400000;

        System.out.println("Total weight " + totalWeight);
        System.out.println("Total is :" + total);
        return (int) Math.round(total);
    }

    public void continueEvaluation() {
        System.out.println("Averages are :" + avgBath + " " + avgBed + " " + avgGarage + " " + avgHouse + " " + avgPlot);
        fetchSuburbPrice(suburbData);
    }

    public void uploadEvaluation(String sPrice, boolean isTest) {
        int evalAmount;
        if (!isTest) {
            evalAmount = evaluate(Integer.parseInt(sPrice), addressData, bedData, bathData, plotAreaData, houseAreaData, garageData, poolData, weights);

        } else {
            evalAmount = 32323;
        }
        evalAmountTest = evalAmount;
        sendInfo(suburbData, addressData, bedData, bathData, plotAreaData, houseAreaData, garageData, poolData, (evalAmount + ""), isTest);
    }


    public void fetchSuburbPrice(String suburbData) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("NAME", suburbData);
        int key = 1;
        VolleyRequest volleyRequest = new VolleyRequest(subUrl, params, this, key);
        volleyRequest.makeRequest();
    }

    public void doCheck(View view) {
        checked = ((CheckBox) view).isChecked();
        System.out.println("Check is clicked and is " + checked);
    }


    @Override
    public void handleResponce(Object response, Map<String, String> map, int key) {
        String h_response = (String) response;

        boolean isTest = false;

        if (map.containsKey("isTest")) {
            isTest = true;
        }

        System.out.println("Fetch response is " + h_response);
        System.out.println("Key is :" + key);
        try {
            JSONObject jsonObject;
            JSONArray result;
            switch (key) {
                case 1: //the fetch suburb is called
                    jsonObject = new JSONObject(h_response);
                    System.out.println("Entered case 1");
                    result = jsonObject.getJSONArray("SUBURB");
                    System.out.println(result);
                    JSONObject suburb = result.getJSONObject(0);
                    String subPrice = suburb.getString("AVG_PRICE");
                    System.out.println(subPrice);
                    Toast.makeText(getApplicationContext(), "handled", Toast.LENGTH_LONG).show();
                    suburbPri = Double.parseDouble(subPrice);
                    uploadEvaluation(subPrice, isTest);
                    break;

                case 2:
                    String[] array = h_response.split(" ");
                    System.out.println("Array contains :" + array[0] + " and " + array[1]);
                    System.out.println("Entered case 2");
                    System.out.println("Fetch response is " + h_response);
                    String houseID = h_response.substring(2, h_response.length());
                    System.out.println("House ID is: " + houseID);
                    Toast.makeText(getApplicationContext(), "handled", Toast.LENGTH_LONG).show();
//
                    displayEvaluation(houseID);
                    break;

                case 3:
                    jsonObject = new JSONObject(h_response);
                    System.out.println("Entered case 3");
                    result = jsonObject.getJSONArray("WEIGHTS");
                    System.out.println(result);
                    JSONObject weight = result.getJSONObject(0);
                    weights[0] = Double.parseDouble(weight.getString("SUBURB"));
                    weights[1] = Double.parseDouble(weight.getString("PLOT_SIZE"));
                    weights[2] = Double.parseDouble(weight.getString("HOUSE_SIZE"));
                    weights[3] = Double.parseDouble(weight.getString("NUM_BEDROOMS"));
                    weights[4] = Double.parseDouble(weight.getString("NUM_BATHROOMS"));
                    weights[5] = Double.parseDouble(weight.getString("NUM_GARAGES"));
                    weights[6] = Double.parseDouble(weight.getString("POOL"));
                    System.out.println("Weights info is : " + weights[0] + " " + weights[1] + " " + weights[2] + " " + weights[3] + " " + weights[4] + " " + weights[5] + " " + weights[6]);
                    Toast.makeText(getApplicationContext(), "Weights Handled", Toast.LENGTH_LONG).show();

                    getAverages();
                    break;
                case 4:
                    jsonObject = new JSONObject(h_response);
                    System.out.println("Entered case 4");
                    result = jsonObject.getJSONArray("HOUSE");
                    System.out.println(result);
                    JSONObject house = result.getJSONObject(0);
                    avgBed = Double.parseDouble(house.getString("NUM_BEDROOMS"));
                    avgBath = Double.parseDouble(house.getString("NUM_BATHROOMS"));
                    avgPlot = Double.parseDouble(house.getString("PLOT_AREA"));
                    avgHouse = Double.parseDouble(house.getString("HOUSE_AREA"));
                    avgGarage = Double.parseDouble(house.getString("NUM_GARAGES"));

                    Toast.makeText(getApplicationContext(), "Weights Handled", Toast.LENGTH_LONG).show();
                    continueEvaluation();
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void displayEvaluation(String houseID) {
        Intent startNewActivity = new Intent(this, HouseActivity.class);
        startNewActivity.putExtra("HOUSEID", houseID);
        startNewActivity.putExtra("ADDRESS", addressData);
        startNewActivity.putExtra("SUBURB", suburbData);
        startNewActivity.putExtra("PLOT_AREA", plotAreaData);
        startNewActivity.putExtra("HOUSE_AREA", houseAreaData);
        startNewActivity.putExtra("BEDROOMS_NUM", bedData);
        startNewActivity.putExtra("BATHROOMS_NUM", bathData);
        startNewActivity.putExtra("GARAGES_NUM", garageData);
        startNewActivity.putExtra("POOL", poolData + "");
        startNewActivity.putExtra("EVALUATION_AMOUNT", evalAmountTest + "");
        startActivity(startNewActivity);
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
