package com.software.design.realestateapp;

import android.content.Context;

import java.util.Map;

/**
 * Created by kyle on 2017/10/29.
 */

public interface VolleyResponce {
    void handleResponce(Object response, Map<String, String> map, int key);

    void handleError(Object error, int key);

    Context getContext();
}
