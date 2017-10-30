package com.software.design.realestateapp;

import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.software.design.realestateapp.fragments.MyEvaluations;
import com.software.design.realestateapp.fragments.NearbyProperties;
import com.software.design.realestateapp.fragments.NewsFeed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboMenuItem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

/**
 * Created by kyle on 2017/10/15.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestDrawerActivity {

    @Test
    public void onCreate() throws Exception {

        DrawerActivity t = Robolectric.setupActivity(DrawerActivity.class);

        assertNotNull(t);
    }

    @Test
    public void testFragment() throws Exception {
        Fragment fragment1 = new MyEvaluations();
        startFragment(fragment1);
        assertNotNull(fragment1);

        Fragment fragment2 = new NearbyProperties();
        startFragment(fragment2);
        assertNotNull(fragment2);

        Fragment fragment3 = new NewsFeed();
        startFragment(fragment3);
        assertNotNull(fragment3);
    }

    @Test
    public void onBackPressed() {
        DrawerActivity t = Robolectric.setupActivity(DrawerActivity.class);

        DrawerLayout drawer = (DrawerLayout) t.findViewById(R.id.drawer_layout);

        drawer.openDrawer(GravityCompat.START);
        t.onBackPressed();
        assertEquals(drawer.isDrawerOpen(GravityCompat.START), false);

        t.onBackPressed();
        assertEquals(t.testReciever, "Else");

    }

    @Test
    public void onNavigationItemSelected() {

        DrawerActivity t = Robolectric.setupActivity(DrawerActivity.class);

        MenuItem menuItem1 = new RoboMenuItem(R.id.nav_newsFeed);
        MenuItem menuItem2 = new RoboMenuItem(R.id.nav_myEvals);
        MenuItem menuItem3 = new RoboMenuItem(R.id.nav_map);

        boolean menuResult;
        menuResult = t.onNavigationItemSelected(menuItem1);
        assertEquals(menuResult, true);
        assertEquals(t.testReciever, "newsFeed");
        menuResult = t.onNavigationItemSelected(menuItem2);
        assertEquals(t.testReciever, "myEvals");
        assertEquals(menuResult, true);
        menuResult = t.onNavigationItemSelected(menuItem3);
        assertEquals(t.testReciever, "navMap");
        assertEquals(menuResult, true);

        DrawerLayout drawer = (DrawerLayout) t.findViewById(R.id.drawer_layout);
        assertEquals(drawer.isDrawerOpen(GravityCompat.START), false);

    }

    @Test
    public void onOptionsItemSelected() {
        DrawerActivity t = Robolectric.setupActivity(DrawerActivity.class);

        MenuItem menuItem1 = new RoboMenuItem(R.id.action_settings);
        MenuItem menuItem2 = new RoboMenuItem(2);

        t.onOptionsItemSelected(menuItem1);

        assertEquals(t.testReciever, "action");

        t.onOptionsItemSelected(menuItem2);

        assertEquals(t.testReciever, "notAction");


    }

//    @Test
//    public void fragments() throws Exception{
//
//        DrawerActivity t = Robolectric.setupActivity(DrawerActivity.class);
//
//        //News feed
//
//        NewsFeed fragment =  new NewsFeed();
//        startFragment(fragment);
//
//        NewsFeed newsFeed = new NewsFeed();
//        assertEquals(t.getFragmentManager().findFragmentById(R.id.drawerContentFrame), );
//    }

}