package com.departure.shihatsu.domain;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties (ignoreUnknown=true)
public class TimeTable implements Serializable {
  private static final long serialVersionUID = 1L;

  private String trainCount;
  public String code;

  // public HashMap<String,Object> timeTable;
  // https://www.baeldung.com/jackson-nested-values
  @SuppressWarnings("unchecked")
  // @JsonProperty("TimeTable")
  // private void unpackNested(Map<String,Object> timeTable){
  //   this.trainCount = (String)timeTable.get("trainCount");
  //   this.code = (String)timeTable.get("code");
  //   // this.timeTable = (HashMap<String,Object>)resultSet.get("TimeTable");
  // }


  public String getTrainCount(){
    return trainCount;
  }

}