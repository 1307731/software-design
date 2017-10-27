package com.software.design.realestateapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.software.design.realestateapp.EvaluationActivity;
import com.software.design.realestateapp.R;

import java.util.ArrayList;
import java.util.List;

public class NewsFeed extends Fragment implements View.OnClickListener {

    List<String> addresses = new ArrayList<String>();
    List<String> suburb = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_news_feed, container, false);
        View root = v.getRootView();
        root.setBackgroundColor(getResources().getColor(R.color.grey));

        addresses.add("5 Janet Street");
        suburb.add("Glenvista");
        addresses.add("36 Basson Drive");
        suburb.add("Glenvista");
        addresses.add("1 Jan Smuts Avenue");
        suburb.add("Braamfontein");
        addresses.add("44 Stanley Avenue");
        suburb.add("Braamfontein");
        addresses.add("45 Vorster Avenue");
        suburb.add("Glenvista");

        LinearLayout ll = (LinearLayout) v.findViewById(R.id.newsEvaluationLayout);

        for(int i = 0; i < addresses.size(); i++) {
            RelativeLayout rl = (RelativeLayout) getActivity().getLayoutInflater().
                    inflate(R.layout.past_evaluations, null);

            TextView t;
            t = (TextView) rl.findViewById(R.id.idText);
            t.setText(""+(i+1));

            t = (TextView) rl.findViewById(R.id.addressText);
            t.setText(addresses.get(i));

            t = (TextView) rl.findViewById(R.id.suburbText);
            t.setText(suburb.get(i));

            rl.setOnClickListener(this);
            //id will match number when on list
            rl.setId(i+1);
            ll.addView(rl);
        }

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v){
        Toast.makeText(getActivity(),"Placeholder for evaluation "+ v.getId(),Toast.LENGTH_SHORT).show();
    }

}
