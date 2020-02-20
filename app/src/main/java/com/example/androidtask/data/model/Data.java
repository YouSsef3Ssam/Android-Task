package com.example.androidtask.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {
    @SerializedName("attractions")
    @Expose
    private List<ItemHome> attractions;
    @SerializedName("hot_spots")
    @Expose
    private List<ItemHome> hotspots;
    @SerializedName("events")
    @Expose
    private List<ItemHome> events;

    public List<ItemHome> getAttractions() {
        return attractions;
    }

    public List<ItemHome> getHotspots() {
        return hotspots;
    }

    public List<ItemHome> getEvents() {
        return events;
    }
}
