package com.example.studyspotapplication;

import com.google.android.gms.maps.model.LatLng;

public class StudySpot {
    private final LatLng position;
    private final String title;

    public StudySpot(double lat, double lng, String title) {
        position = new LatLng(lat, lng);
        this.title = title;
    }

    public LatLng getPosition() {
        return position;
    }

    public String getTitle() {
        return title;
    }

//    public String getSnippet() {
//        return snippet;
//    }
}