package com.departure.shihatsu.domain;

import java.util.Map;
import com.fasterxml.jackson.annotation.*;

public class GeoPoint {
    private String longi;
    private String lati;
    private String longiD;
    private String latiD;
    private String gcs;

    @JsonProperty("longi")
    public String getLongi() { return longi; }
    @JsonProperty("longi")
    public void setLongi(String value) { this.longi = value; }

    @JsonProperty("lati")
    public String getLati() { return lati; }
    @JsonProperty("lati")
    public void setLati(String value) { this.lati = value; }

    @JsonProperty("longi_d")
    public String getLongiD() { return longiD; }
    @JsonProperty("longi_d")
    public void setLongiD(String value) { this.longiD = value; }

    @JsonProperty("lati_d")
    public String getLatiD() { return latiD; }
    @JsonProperty("lati_d")
    public void setLatiD(String value) { this.latiD = value; }

    @JsonProperty("gcs")
    public String getGcs() { return gcs; }
    @JsonProperty("gcs")
    public void setGcs(String value) { this.gcs = value; }
}
