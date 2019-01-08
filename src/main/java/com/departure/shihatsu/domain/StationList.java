package com.departure.shihatsu.domain;

import java.util.Map;
import com.fasterxml.jackson.annotation.*;

public class StationList {
    private Station station;
    private Prefecture prefecture;
    private GeoPoint geoPoint;

    @JsonProperty("Station")
    public Station getStation() { return station; }
    @JsonProperty("Station")
    public void setStation(Station value) { this.station = value; }

    @JsonProperty("Prefecture")
    public Prefecture getPrefecture() { return prefecture; }
    @JsonProperty("Prefecture")
    public void setPrefecture(Prefecture value) { this.prefecture = value; }

    @JsonProperty("GeoPoint")
    public GeoPoint getGeoPoint() { return geoPoint; }
    @JsonProperty("GeoPoint")
    public void setGeoPoint(GeoPoint value) { this.geoPoint = value; }
}
