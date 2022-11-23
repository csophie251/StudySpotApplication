package com.example.studyspotapplication;

import com.google.android.gms.maps.model.LatLng;

public class StudySpot {
    LatLng position;
    String name;
    String location;
    String openHours;
    boolean busy;
    boolean quiet;
    boolean outlets;

    public StudySpot(StudySpotData data) {
        position = new LatLng(data.latitude, data.longitude);
        this.name = data.name;
        this.location = data.location;
        this.openHours = data.openHours;;
        this.busy = data.busy;
        this.quiet = data.quiet;
        this.outlets = data.outlets;
    }

    public LatLng getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

//    public String getSnippet() {
//        return snippet;
//    }
}

class StudySpotData {
    String name;
    String location;
    double latitude;
    double longitude;
    String openHours;
    boolean busy;
    boolean quiet;
    boolean outlets;
}