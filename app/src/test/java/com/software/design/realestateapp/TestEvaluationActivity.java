package com.software.design.realestateapp;

import android.content.Intent;
import android.widget.EditText;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by kyle on 2017/10/15.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestEvaluationActivity {

    String TEST_STRING_NUMBER = "2";
    boolean poolData;
    double[] weights;
    String suburbData, addressData, bedData, bathData, plotAreaData, houseAreaData, garageData;
    private String TEST_STRING = "TEST";
    private int TEST_NUMBER = 2;
    private boolean checked = false;

    @Test
    public void onCreate() throws Exception {
        EvaluationActivity t = Robolectric.setupActivity(EvaluationActivity.class);

        assertNotNull(t);
    }

    @Test
    public void doEvaluation() throws Exception {
        EvaluationActivity t = Robolectric.setupActivity(EvaluationActivity.class);

        EditText address = (EditText) t.findViewById(R.id.edAddress);

        EditText suburb = (EditText) t.findViewById(R.id.edSuburb);
        EditText numBath = (EditText) t.findViewById(R.id.edNumBath);
        EditText numBed = (EditText) t.findViewById(R.id.edNumBed);
        EditText numGarage = (EditText) t.findViewById(R.id.edNumGarages);
        EditText plotArea = (EditText) t.findViewById(R.id.edSizePlot);
        EditText houseArea = (EditText) t.findViewById(R.id.edSizeHouse);

        address.setText(TEST_STRING);
        suburb.setText(TEST_STRING);
        numBath.setText(Integer.toString(TEST_NUMBER));
        numBed.setText(Integer.toString(TEST_NUMBER));
        numGarage.setText(Integer.toString(TEST_NUMBER));
        plotArea.setText(Integer.toString(TEST_NUMBER));
        houseArea.setText(Integer.toString(TEST_NUMBER));

        t.doEvaluationTestable(true);

        assertNotNull(t.evalAmountTest);

    }

    @Test
    public void evaluate() throws Exception {

        suburbData = TEST_STRING;
        addressData = TEST_STRING;
        bedData = TEST_STRING_NUMBER;
        bathData = TEST_STRING_NUMBER;
        plotAreaData = TEST_STRING_NUMBER;
        houseAreaData = TEST_STRING_NUMBER;
        garageData = TEST_STRING_NUMBER;
        poolData = checked;

        EvaluationActivity t = Robolectric.setupActivity(EvaluationActivity.class);

        double[] weightsArray = new double[7];


        t.loadWeights(true, weightsArray);

        int result = t.evaluate(TEST_NUMBER, TEST_STRING, TEST_STRING_NUMBER, TEST_STRING_NUMBER, TEST_STRING_NUMBER, TEST_STRING_NUMBER, TEST_STRING_NUMBER, true, weightsArray);

        assertNotNull(result);
    }

    @Test
    public void uploadEvaluation() throws Exception {
        EvaluationActivity t = Robolectric.setupActivity(EvaluationActivity.class);

        weights = new double[7];
        t.loadWeights(true, weights);

        suburbData = TEST_STRING;
        addressData = TEST_STRING;
        bedData = TEST_STRING_NUMBER;
        bathData = TEST_STRING_NUMBER;
        plotAreaData = TEST_STRING_NUMBER;
        houseAreaData = TEST_STRING_NUMBER;
        garageData = TEST_STRING_NUMBER;
        poolData = checked;

        String price = "22222";

        t.uploadEvaluation(price, true);

        assertNotNull(t);

    }

    @Test
    public void displayEvatuation() throws Exception {
        EvaluationActivity t = Robolectric.setupActivity(EvaluationActivity.class);

        String houseID = "2";
        suburbData = TEST_STRING;
        addressData = TEST_STRING;
        bedData = TEST_STRING_NUMBER;
        bathData = TEST_STRING_NUMBER;
        plotAreaData = TEST_STRING_NUMBER;
        houseAreaData = TEST_STRING_NUMBER;
        garageData = TEST_STRING_NUMBER;
        poolData = checked;

        t.displayEvaluation(houseID);


        Intent expectedIntent = new Intent(t, HouseActivity.class);
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }

}