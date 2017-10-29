package com.software.design.realestateapp;

import android.widget.EditText;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;

/**
 * Created by kyle on 2017/10/15.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestEvaluationActivity {

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

}