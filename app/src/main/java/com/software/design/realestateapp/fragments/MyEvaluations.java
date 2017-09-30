package com.software.design.realestateapp.fragments;

import android.content.Intent;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.software.design.realestateapp.EvaluationActivity;
import com.software.design.realestateapp.R;
import com.software.design.realestateapp.RoomActivity;

public class MyEvaluations extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_evaluations, container, false);
        Button evalBtn = (Button) v.findViewById(R.id.addEvaluationBtn);
        evalBtn.setOnClickListener(this);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v){
        ///Toast.makeText(getActivity(),"Button Working",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity(), EvaluationActivity.class);
        startActivity(intent);
    }


}
