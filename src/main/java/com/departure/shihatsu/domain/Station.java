package com.departure.shihatsu.domain;

import java.util.Map;
import com.fasterxml.jackson.annotation.*;

public class Station {
    private String code;
    private String name;
    private String type;
    private String yomi;

    @JsonProperty("code")
    public String getCode() { return code; }
    @JsonProperty("code")
    public void setCode(String value) { this.code = value; }

    @JsonProperty("Name")
    public String getName() { return name; }
    @JsonProperty("Name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("Type")
    public String getType() { return type; }
    @JsonProperty("Type")
    public void setType(String value) { this.type = value; }

    @JsonProperty("Yomi")
    public String getYomi() { return yomi; }
    @JsonProperty("Yomi")
    public void setYomi(String value) { this.yomi = value; }
}
