package com.software.design.realestateapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.software.design.realestateapp.R;

/**
 * Created by alterith on 2017/09/15.
 */

public class NearbyProperties extends Fragment implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    MapView mapView;
    View mView;

    public NearbyProperties(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        //inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_properties_map, container, false);
        View root = mView.getRootView();
        root.setBackgroundColor(getResources().getColor(R.color.grey));

        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView =  (MapView)mView.findViewById(R.id.map);

        if(mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //min sdk is 23
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            MapsInitializer.initialize(getContext());
//        }else{
//
//            Toast.makeText(this.getContext(),"SDK version less than 23",Toast.LENGTH_LONG);
//
//        }
        MapsInitializer.initialize(getActivity().getApplicationContext());

        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //add marker to science stadium
        googleMap.addMarker(new MarkerOptions().position(new LatLng(-26.190662, 28.025340)).title("Science Stadium").snippet("University of the Witwatersrand"));
        //Set camera position to stadium
        CameraPosition scienceStadium = CameraPosition.builder().target(new LatLng(-26.190662, 28.025340)).zoom(600).bearing(0).tilt(45).build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(scienceStadium));

    }
}
