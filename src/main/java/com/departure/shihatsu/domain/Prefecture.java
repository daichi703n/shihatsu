package com.departure.shihatsu.domain;

import java.util.Map;
import com.fasterxml.jackson.annotation.*;

public class Prefecture {
    private String code;
    private String name;

    @JsonProperty("code")
    public String getCode() { return code; }
    @JsonProperty("code")
    public void setCode(String value) { this.code = value; }

    @JsonProperty("Name")
    public String getName() { return name; }
    @JsonProperty("Name")
    public void setName(String value) { this.name = value; }
}
