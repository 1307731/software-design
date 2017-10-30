package com.software.design.realestateapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;

/**
 * Created by kyle on 2017/10/29.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestHouseActivity {

    @Test
    public void onCreate() throws Exception {

        HouseActivity t = Robolectric.setupActivity(HouseActivity.class);

        assertNotNull(t);
    }
}