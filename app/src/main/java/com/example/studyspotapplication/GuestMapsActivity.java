package com.example.studyspotapplication;

import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;


public class GuestMapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private ArrayList<StudySpot> spots;
    private HashMap<String, StudySpot> names_to_spots;

    private ArrayList<StudySpot> generateStudySpots() {
//        ArrayList<StudySpot> cur_spots = new ArrayList<StudySpot>();
//        StudySpot leavey = new StudySpot(34.02193, -118.28277, "Leavey Library");
//        StudySpot doheny = new StudySpot(34.02015, -118.28372, "Doheny Library");
//        StudySpot sidney = new StudySpot(34.02235, -118.28512, "Sydney Harman");
//        cur_spots.add(leavey);
//        cur_spots.add(doheny);
//        cur_spots.add(sidney);
//        return cur_spots;
        return Util.retrieveAllPages();
    }

    private void place_markers() {
        // synchronized?
        HashMap<String, StudySpot> temp = new HashMap<String, StudySpot>();
        for (int i = 0; i < spots.size(); ++i) {
            StudySpot cur = spots.get(i);
            Log.d("myTag", cur.getTitle());
            temp.put(cur.getTitle(), cur);
            mMap.addMarker(new MarkerOptions().position(cur.getPosition()).title(cur.getTitle()));
        }
        names_to_spots = temp;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_activity_maps);
        this.spots = generateStudySpots();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        // Synchronize on study spots arraylist?
        mapFragment.getMapAsync(this);

    }

    @SuppressLint("PotentialBehaviorOverride")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        place_markers();
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15));
                return false;
            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //switch page to studyspot page
                Intent myIntent = new Intent(GuestMapsActivity.this, StudySpotGuestActivity.class);
                myIntent.putExtra("name", marker.getTitle());
                startActivity(myIntent);
            }
        });
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(34.0224, -118.2851), 15));
    }
}