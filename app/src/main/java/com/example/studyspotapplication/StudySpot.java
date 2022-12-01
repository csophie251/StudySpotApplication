package com.example.studyspotapplication;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class StudySpot {
    LatLng position;
    String name;
    String location;
    String hours;
    boolean busy;
    boolean quiet;
    boolean outlets;

    public StudySpot(StudySpotData data) {
        position = new LatLng(data.latitude, data.longitude);
        this.name = data.name;
        this.location = data.location;
        this.hours = data.hours;;
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

    @Override
    public String toString() {
        return "StudySpot{" +
                "position=" + position +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", hours='" + hours + '\'' +
                ", busy=" + busy +
                ", quiet=" + quiet +
                ", outlets=" + outlets +
                '}';
    }
}

class StudySpotData {
    String name;
    String location;
    double latitude;
    double longitude;
    String hours;
    boolean busy;
    boolean quiet;
    boolean outlets;
}

class StudySpotsData {
    ArrayList<StudySpotData> data;
}