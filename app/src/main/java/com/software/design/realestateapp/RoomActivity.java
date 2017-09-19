package com.software.design.realestateapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    List<String> roomList = new ArrayList<String>();
    List<String> flooringList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        Spinner spinner1 = (Spinner) findViewById(R.id.room_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> room_adapter = ArrayAdapter.createFromResource(this,
                R.array.room_types, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        room_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(room_adapter);
        spinner1.setOnItemSelectedListener(this);


        Spinner spinner2 = (Spinner) findViewById(R.id.flooring_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> flooring_adapter = ArrayAdapter.createFromResource(this,
                R.array.flooring_types, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        flooring_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner2.setAdapter(flooring_adapter);
        spinner2.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()){
            case R.id.room_spinner:
                //Do something
                Toast.makeText(this, "Room Selected: " + parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.flooring_spinner:
                //Do another thing
                Toast.makeText(this, "Flooring Selected: " + parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
