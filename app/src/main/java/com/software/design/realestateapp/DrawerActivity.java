package com.software.design.realestateapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.software.design.realestateapp.fragments.Favourites;
import com.software.design.realestateapp.fragments.MyEvaluations;
import com.software.design.realestateapp.fragments.NearbyProperties;
import com.software.design.realestateapp.fragments.NewsFeed;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String userName;
    String userEmail;
    String s_user_id;

    String testReciever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drawer_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /////////////Start of my code, that above is auto generated

        userName = getIntent().getStringExtra("Username");
        userEmail = getIntent().getStringExtra("Email");
        //System.out.println("Username" + userName);
        //Toast.makeText(getApplicationContext(),"Username: " + userName, Toast.LENGTH_LONG).show();

        s_user_id = getIntent().getStringExtra("USER_ID");


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Fragment fragment = new NewsFeed();
        ft.add(R.id.drawerContentFrame,fragment);
        ft.commit();

        View header = navigationView.getHeaderView(0);
        TextView drawerUsername = (TextView)header.findViewById(R.id.drawerUsername);
        // TextView drawerEmail = (TextView) header.findViewById(R.id.drawerEmail);
        drawerUsername.setText(userName);
        // drawerEmail.setText(userEmail);

        //(R.layout.nav_header_drawer_activity)

        testReciever = new String();


    }

    public String getS_user_id(){
        return s_user_id;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            testReciever = "Else";
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        testReciever = "notAction";
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            testReciever = "action";
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment;

        if (id == R.id.nav_newsFeed) {

            fragment = new NewsFeed();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.drawerContentFrame, fragment);
            ft.commit();

            testReciever = "newsFeed";


        } else if (id == R.id.nav_myEvals) {

            fragment = new MyEvaluations();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.drawerContentFrame, fragment);
            ft.commit();

            testReciever = "myEvals";


        }else if (id == R.id.nav_map) {

            fragment = new NearbyProperties();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.drawerContentFrame, fragment);
            ft.commit();

            testReciever = "navMap";

        }
        else if(id == R.id.nav_favourites){
            fragment = new Favourites();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.drawerContentFrame, fragment);
            ft.commit();

            testReciever = "favourites";
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
