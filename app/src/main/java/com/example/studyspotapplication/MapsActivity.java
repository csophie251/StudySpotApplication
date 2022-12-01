package com.example.studyspotapplication;

import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {GoogleMap mMap;
    private List<Boolean> filter_status;


    //AutoComplete
    private HashMap<String, StudySpot> names_to_spots;
    private AutoCompleteTextView autocomplete_searchField;
    private ArrayList<String> places;
    private ArrayList<StudySpot> spots;
    public String username;

    private ArrayList<StudySpot> generateStudySpots() {
        ArrayList<StudySpot> cur_spots = new ArrayList<StudySpot>();
        StudySpotData leaveyData = new StudySpotData();
        leaveyData.name = "Leavey Library";
        leaveyData.location = "testlocation";
        leaveyData.latitude =34.02193;
        leaveyData.longitude =-118.28277;
        leaveyData.hours = "test hours";
        leaveyData.busy = true;
        leaveyData.quiet = false;
        leaveyData.outlets = true;

        StudySpotData dohenyData = new StudySpotData();
        dohenyData.name = "Doheny Memorial Library";
        dohenyData.location = "testlocation";
        dohenyData.latitude =34.02015;
        dohenyData.longitude =-118.28372;
        dohenyData.hours = "test hours";
        dohenyData.busy = false;
        dohenyData.quiet = true;
        dohenyData.outlets = true;

        StudySpotData sidneyData = new StudySpotData();
        sidneyData.name = "Sydney Harman";
        sidneyData.location = "testlocation";
        sidneyData.latitude =34.02235;
        sidneyData.longitude =-118.28512;
        sidneyData.hours = "test hours";
        sidneyData.busy = true;
        sidneyData.quiet = false;
        sidneyData.outlets = false;

        StudySpot leavey = new StudySpot(leaveyData);
        StudySpot doheny = new StudySpot(dohenyData);
        StudySpot sidney = new StudySpot(sidneyData);

        //StudySpot leavey = new StudySpot(34.02193, -118.28277, "Leavey Library", "testlocation", "testopenHours", true, false, true);
        //StudySpot doheny = new StudySpot(34.02015, -118.28372, "Doheny Library");
        // StudySpot sidney = new StudySpot(34.02235, -118.28512, "Sydney Harman");
        cur_spots.add(leavey);
        cur_spots.add(doheny);
        cur_spots.add(sidney);
        return cur_spots;
//        return Util.retrieveAllStudySpots();
    }

    private void placeMarkers() {
        // synchronized?
        HashMap<String, StudySpot> temp = new HashMap<String, StudySpot>();
        for (int i = 0; i < spots.size(); ++i) {
            StudySpot cur = spots.get(i);
            Log.d("myTag", cur.getName());
            temp.put(cur.getName(), cur);
            mMap.addMarker(new MarkerOptions().position(cur.getPosition()).title(cur.getName()));
        }
        names_to_spots = temp;
    }


    private ArrayList<String> get_dropDown_titles() {
        ArrayList<String> places = new ArrayList<String>();
        for (int i = 0; i < spots.size(); ++i) {
            places.add(spots.get(i).getName());
            //places.add(spots.get(i).getTitle());
        }
        return places;
    }

    public void sendFilterValues(List<Boolean> stuff) {
        // remove all previous markers
        for (int i = 0; i < stuff.size(); ++i) {
            filter_status.set(i,stuff.get(i));
        }
        // update map to show markers that belong to filters
        for (int i = 0; i < spots.size(); ++i) {
            // match bool values of filters
            // add marker if bool values are added properly
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        this.spots = generateStudySpots();

        Button mButton = findViewById(R.id.logout);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(MapsActivity.this, LoginPageActivity.class);
                startActivity(myIntent);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        // Synchronize on study spots arraylist?

        //Filters
        // https://www.geeksforgeeks.org/modal-bottom-sheet-in-android-with-examples/
        Button OpenBottomSheet = findViewById(R.id.open_bottom_sheet);

        //hardcoded over hashmap
        filter_status = Collections.synchronizedList(new ArrayList<Boolean>());
        filter_status.add(false);
        filter_status.add(false);
        filter_status.add(false);
        filter_status.add(false);
        OpenBottomSheet.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        BottomSheetDialog bottomSheet = new BottomSheetDialog();

                        bottomSheet.setCancelable(false);
                        // send filter data
                        bottomSheet.addList(filter_status);
                        bottomSheet.addMap(MapsActivity.this);
                        bottomSheet.show(getSupportFragmentManager(),
                                "ModalBottomSheet");
                    }
                });


        // Colors and Appearance!!!
        // https://stackoverflow.com/questions/30763879/clicked-drop-down-item-in-autocompletetextview-does-not-respond-on-the-first-cli
        autocomplete_searchField = (AutoCompleteTextView) findViewById(R.id.map_toolbar);
//        displayField = (TextView) findViewById(R.id.dropdown_item);

        // Gets the string array
        places = get_dropDown_titles();

        // Creates the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, places);
        autocomplete_searchField.setAdapter(adapter);
        autocomplete_searchField.setThreshold(1);
        autocomplete_searchField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autocomplete_searchField.showDropDown();
            }
        });
        autocomplete_searchField.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){
                String selected_Place = autocomplete_searchField.getText().toString();
                Log.d("myTag", "Clicked Dropdown Item " + selected_Place);
                StudySpot cur_spot = names_to_spots.get(selected_Place);
                mMap.addMarker(new MarkerOptions().position(cur_spot.getPosition()).title(selected_Place));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cur_spot.getPosition(), 18));
            }
        });
        mapFragment.getMapAsync(this);
    }

    @SuppressLint("PotentialBehaviorOverride")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        placeMarkers();
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 18));
                return true;
            }
        });
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //switch page to studyspot page
                Intent myIntent = new Intent(MapsActivity.this, StudySpotAuthenticatedActivity.class);
                username = myIntent.getStringExtra("username");
                Log.d("myTag", "clicked marker window: " + marker.getTitle());
                myIntent.putExtra("name", marker.getTitle());
                myIntent.putExtra("username", username);
                startActivity(myIntent);
            }
        });
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(34.0224, -118.2851), 15));
        //Set Custom InfoWindow Adapter
        CustomInfoWindowAdapter adapter = new CustomInfoWindowAdapter(MapsActivity.this);
        mMap.setInfoWindowAdapter(adapter);
    }
}