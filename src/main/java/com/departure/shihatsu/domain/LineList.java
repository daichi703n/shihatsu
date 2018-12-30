package com.departure.shihatsu.domain;

import java.util.Map;
import com.fasterxml.jackson.annotation.*;

public class LineList {
    private String corporationIndex;
    private String code;
    private String name;
    private String yomi;
    private String color;

    @JsonProperty("corporationIndex")
    public String getCorporationIndex() { return corporationIndex; }
    @JsonProperty("corporationIndex")
    public void setCorporationIndex(String value) { this.corporationIndex = value; }

    @JsonProperty("code")
    public String getCode() { return code; }
    @JsonProperty("code")
    public void setCode(String value) { this.code = value; }

    @JsonProperty("Name")
    public String getName() { return name; }
    @JsonProperty("Name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("Yomi")
    public String getYomi() { return yomi; }
    @JsonProperty("Yomi")
    public void setYomi(String value) { this.yomi = value; }

    @JsonProperty("Color")
    public String getColor() { return color; }
    @JsonProperty("Color")
    public void setColor(String value) { this.color = value; }
}
