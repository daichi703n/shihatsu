package com.departure.shihatsu.domain;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties (ignoreUnknown=true)
public class TimeTableResultSet implements Serializable {
  private static final long serialVersionUID = 1L;

  private String apiVersion;
  public String engineVersion;
  public Object timeTable;

  // https://www.baeldung.com/jackson-nested-values
  @SuppressWarnings("unchecked")
  @JsonProperty("ResultSet")
  private void unpackNested(Map<String,Object> resultSet){
    this.apiVersion = (String)resultSet.get("apiVersion");
    this.engineVersion = (String)resultSet.get("engineVersion");
    this.timeTable = resultSet.get("TimeTable");
  }

  // public HashMap<String,Object> ResultSet = new HashMap<>();
  // public class ResultSet implements Serializable{
  //   private static final long serialVersionUID = 1L;

  //   private HashMap<String,Object> TimeTable = new HashMap<>();
  //   private class TimeTable implements Serializable {
  //     private static final long serialVersionUID = 1L;
  
  //     private String trainCount;
  //     private String code;
  //     private String dateGroup;
  //     private HashMap<String,String> Station = new HashMap<>();
  //     private class Station implements Serializable{
  //       private static final long serialVersionUID = 1L;
  //       private String Name;
      // }
      // // private List<HashMap<String,Object> > HourTable; 
    // }

  
  // }

  
  // public String getResultSet(){
  //   return ResultSet.toString();
  // }

  public String getApiVersion(){
    return apiVersion;
  }

}