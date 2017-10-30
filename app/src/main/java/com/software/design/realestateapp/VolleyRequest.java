package com.software.design.realestateapp;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Created by kyle on 2017/10/29.
 */

public class VolleyRequest {
    private final Map<String, String> map;
    private final VolleyResponce vol;
    private final String url;
    private final int key;

    VolleyRequest(String url, Map<String, String> map, VolleyResponce vol, int key) {
        this.map = map;
        this.vol = vol;
        this.url = url;
        this.key = key;
    }

    public void makeRequest() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Response is: "+ response);
                        vol.handleResponce(response, map, key);
                        System.out.println("Response");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Error is: "+ error);
                        vol.handleError(error, key);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                return map;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(vol.getContext());
        requestQueue.add(stringRequest);
    }
}
