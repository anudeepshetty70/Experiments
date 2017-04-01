package com.assignment.second.model;


public class PlaceModel {

   /* ""car": "80 Mins",
		"train": "120 Mins"*/

    private double id;
    private String name;
    private LocationModel location;
    private FromCentralModel fromcentral;

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public FromCentralModel getFromcentral() {
        return fromcentral;
    }

    public void setFromcentral(FromCentralModel fromcentral) {
        this.fromcentral = fromcentral;
    }
}
