package com.software.design.realestateapp;

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
public class TestRoomActivity {
    @Test
    public void onCreate() throws Exception {

        RoomActivity t = Robolectric.setupActivity(RoomActivity.class);

        assertNotNull(t);
    }

}