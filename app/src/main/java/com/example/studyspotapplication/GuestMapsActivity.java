package com.example.studyspotapplication;

import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

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

    public void grabStudySpots() {
        new Thread() {
            @Override
            public void run() {
                ArrayList<StudySpot> res = Util.retrieveAllStudySpots();
                if (res == null) {
                    runOnUiThread(() -> Toast.makeText(GuestMapsActivity.this, "No Study Spots Available", Toast.LENGTH_SHORT).show());
                } else {
                    runOnUiThread(() -> {
                        spots = res;
                        place_markers();
                    });
                }
            }
        }.start();
    }

    private void place_markers() {
        HashMap<String, StudySpot> temp = new HashMap<String, StudySpot>();
        for (int i = 0; i < spots.size(); ++i) {
            StudySpot cur = spots.get(i);
            Log.d("myTag", cur.getName());
            temp.put(cur.getName(), cur);
            mMap.addMarker(new MarkerOptions().position(cur.getPosition()).title(cur.getName()));
        }
        names_to_spots = temp;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_activity_maps);
        this.spots = new ArrayList<StudySpot>();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button mButton = findViewById(R.id.login);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(GuestMapsActivity.this, LandingPageActivity.class);
                startActivity(myIntent);
            }
        });
    }

    @SuppressLint("PotentialBehaviorOverride")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        grabStudySpots();
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
                Intent myIntent = new Intent(GuestMapsActivity.this, StudySpotGuestActivity.class);
                myIntent.putExtra("name", marker.getTitle());
                startActivity(myIntent);
            }
        });
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(34.0224, -118.2851), 15));
    }
}