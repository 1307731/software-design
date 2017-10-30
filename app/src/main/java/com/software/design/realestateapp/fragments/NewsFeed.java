package com.software.design.realestateapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.software.design.realestateapp.DrawerActivity;
import com.software.design.realestateapp.EvaluationActivity;
import com.software.design.realestateapp.HouseActivity;
import com.software.design.realestateapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsFeed extends Fragment implements View.OnClickListener {

    List<String> addresses = new ArrayList<String>();
    List<String> suburbs = new ArrayList<String>();
    List<String> plot_area = new ArrayList<String>();
    List<String> house_area = new ArrayList<String>();
    List<String> bedrooms_num = new ArrayList<String>();
    List<String> bathrooms_num = new ArrayList<String>();
    List<String> garages_num = new ArrayList<String>();
    List<String> pool = new ArrayList<String>();
    List<String> evaluation = new ArrayList<String>();
    List<String> house_ids = new ArrayList<String>();
    String user_id;

    private Button mButton;
    String url = "http://lamp.ms.wits.ac.za/~s1037363/realestate_app/getHouses.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_news_feed, container, false);
        View root = v.getRootView();
        root.setBackgroundColor(getResources().getColor(R.color.grey));

        mButton = (Button) v.findViewById(R.id.refresh_button_news);
        mButton.setOnClickListener(this);

        user_id = ((DrawerActivity)getActivity()).getS_user_id();

        /*LinearLayout ll = (LinearLayout) v.findViewById(R.id.newsEvaluationLayout);

        for(int i = 0; i < addresses.size(); i++) {
            RelativeLayout rl = (RelativeLayout) getActivity().getLayoutInflater().
                    inflate(R.layout.past_evaluations, null);

            TextView t;
            t = (TextView) rl.findViewById(R.id.idText);
            t.setText(""+(i+1));

            t = (TextView) rl.findViewById(R.id.addressText);
            t.setText(addresses.get(i));

            t = (TextView) rl.findViewById(R.id.suburbText);
            t.setText(suburbs.get(i));

            rl.setOnClickListener(this);
            //id will match number when on list
            rl.setId(i+1);
            ll.addView(rl);
        }*/

        // Inflate the layout for this fragment
        return v;
    }

    public void populateList(){

        LinearLayout ll = (LinearLayout) getView().findViewById(R.id.newsEvaluationLayout);
        ll.removeAllViews();

        for(int i = 0; i < addresses.size(); i++) {
            RelativeLayout rl = (RelativeLayout) getActivity().getLayoutInflater().
                    inflate(R.layout.past_evaluations, null);

            TextView t;
            t = (TextView) rl.findViewById(R.id.idText);
            t.setText(""+(i+1));

            t = (TextView) rl.findViewById(R.id.addressText);
            t.setText(addresses.get(i));

            t = (TextView) rl.findViewById(R.id.suburbText);
            t.setText(suburbs.get(i));

            rl.setOnClickListener(this);
            //id will match number when on list
            rl.setId(i+1);
            ll.addView(rl);
        }
        addresses.clear();
        suburbs.clear();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.refresh_button_news:
                Toast.makeText(getContext(), "Refreshing", Toast.LENGTH_SHORT).show();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
                                Log.d("Database Response:",response.toString());
                                try{
                                    JSONObject jsonObject = new JSONObject(response);
                                    JSONArray jsonArray = jsonObject.getJSONArray("HOUSE");
                                    Log.d("Array size",Integer.toString(jsonArray.getJSONArray(0).length()));
                                    for (int i = 0; i < jsonArray.getJSONArray(0).length(); i++){
                                        JSONObject curr = jsonArray.getJSONArray(0).getJSONArray(i).getJSONObject(0);
                                        String temp = curr.getString("ADDRESS");
                                        addresses.add(temp);
                                        temp = curr.getString("SUBURB");
                                        suburbs.add(temp);
                                        temp = curr.getString("PLOT_AREA");
                                        plot_area.add(temp);
                                        temp = curr.getString("HOUSE_AREA");
                                        house_area.add(temp);
                                        temp = curr.getString("NUM_BEDROOMS");
                                        bedrooms_num.add(temp);
                                        temp = curr.getString("NUM_BATHROOMS");
                                        bathrooms_num.add(temp);
                                        temp = curr.getString("NUM_GARAGES");
                                        garages_num.add(temp);
                                        temp = curr.getString("POOL");
                                        pool.add(temp);
                                        temp = curr.getString("EVALUATION");
                                        evaluation.add(temp);
                                        temp = curr.getString("HOUSE_ID");
                                        house_ids.add(temp);
                                    }
                                    //Toast.makeText(getContext(), curr.getString("ADDRESS"), Toast.LENGTH_SHORT).show();
                                }
                                catch (JSONException e){
                                    Toast.makeText(getContext(), "Error with JSON", Toast.LENGTH_SHORT).show();
                                }
                                //Toast.makeText(EvaluationActivity.this, response, Toast.LENGTH_LONG).show();
                                //t.setText(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                requestQueue.add(stringRequest);
                Log.d("Suburbs size",Integer.toString(suburbs.size()));
                populateList();
                break;
            default:
                //Toast.makeText(getActivity(),"Placeholder for evaluation "+ v.getId(),Toast.LENGTH_SHORT).show();
                Intent startNewActivity = new Intent(getActivity(), HouseActivity.class);
                startNewActivity.putExtra("ADDRESS", addresses.get((int)v.getId()-1));
                startNewActivity.putExtra("SUBURB", suburbs.get((int)v.getId()-1));
                startNewActivity.putExtra("PLOT_AREA", plot_area.get((int)v.getId()-1));
                startNewActivity.putExtra("HOUSE_AREA", house_area.get((int)v.getId()-1));
                startNewActivity.putExtra("BEDROOMS_NUM", bedrooms_num.get((int)v.getId()-1));
                startNewActivity.putExtra("BATHROOMS_NUM", bathrooms_num.get((int)v.getId()-1));
                startNewActivity.putExtra("GARAGES_NUM", garages_num.get((int)v.getId()-1));
                startNewActivity.putExtra("POOL", pool.get((int)v.getId()-1)+"");
                startNewActivity.putExtra("EVALUATION_AMOUNT", evaluation.get((int)v.getId()-1));
                startNewActivity.putExtra("HOUSE_ID", house_ids.get((int)v.getId()-1));
                startNewActivity.putExtra("USER_ID", user_id);
                startActivity(startNewActivity);
                break;

        }
        //Toast.makeText(getActivity(),"Placeholder for evaluation "+ v.getId(),Toast.LENGTH_SHORT).show();
    }

}
