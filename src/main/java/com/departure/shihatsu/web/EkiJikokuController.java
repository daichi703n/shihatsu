package com.departure.shihatsu.web;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.departure.shihatsu.domain.TimeTable;
import com.departure.shihatsu.domain.TimeTableResultSet;

import org.apache.catalina.webresources.Cache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/jikoku")
public class EkiJikokuController {

    @RequestMapping(method = RequestMethod.GET)
    public String jukoku(Model model) throws URISyntaxException {
        String ekey=System.getenv("EKEY");
        String stationCode = "22602";
        String code = "1150";

        String ekiUrl="https://api.ekispert.jp/v1/json/operationLine/timetable?key="+ekey+"&stationCode="+stationCode+"&code="+code;
        URI uri = new URI(ekiUrl);
        RestTemplate restTemplate = new RestTemplate();

        LinkedHashMap result = new LinkedHashMap<>();

        result = restTemplate.getForObject(ekiUrl, LinkedHashMap.class);

        String resultStr = restTemplate.getForObject(ekiUrl, String.class);

        ObjectMapper mapper = new ObjectMapper();
        TimeTableResultSet timeTableResultSet = new TimeTableResultSet();

        try{
            JsonNode root = mapper.readTree(resultStr);
            System.out.println(root.toString());
            timeTableResultSet = mapper.readValue(resultStr, TimeTableResultSet.class);
            // System.out.println(resultStr);
            // System.out.println("ResultSet: "+timeTableResultSet.getClass());
            // System.out.println("ResultSet: "+timeTableResultSet.getResultSet());
            System.out.println("apiVersion: "+timeTableResultSet.getApiVersion());
            System.out.println("engineVersion: "+timeTableResultSet.engineVersion);
            
            ObjectMapper mapper2 = new ObjectMapper();
            TimeTable timeTable = new TimeTable();
            // System.out.println(timeTableResultSet.timeTable);

        } catch (IOException e){
            e.printStackTrace();
        }

        LinkedHashMap resultSet = (LinkedHashMap) result.get("ResultSet");
        LinkedHashMap timeTable = (LinkedHashMap) resultSet.get("TimeTable");
        LinkedHashMap info = (LinkedHashMap) timeTable.get("Station");
        
        ArrayList jikoku = new ArrayList<>();
        jikoku = (ArrayList) timeTable.get("HourTable");

        ArrayList<Integer> hour = new ArrayList<>();
        ArrayList<String> minuteList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> minuteTable = new ArrayList<>();
        for(Object object : jikoku){
            LinkedHashMap linkedHashMap = (LinkedHashMap) object;
            Object hourObject = linkedHashMap.get("Hour");
            hour.add(Integer.valueOf(hourObject.toString()));
            minuteList = (ArrayList) linkedHashMap.get("MinuteTable");
            ArrayList<Integer> minute = new ArrayList<>();
            for(Object object2 : minuteList){
                LinkedHashMap linkedHashMap2 = (LinkedHashMap) object2;
                Object minuteObject = linkedHashMap2.get("Minute");
                minute.add(Integer.parseInt(minuteObject.toString()));
            }
            Integer hourCurrent = Integer.valueOf(hourObject.toString());
            minuteTable.add(minute);
        }
        
        model.addAttribute("info",info.toString());
        model.addAttribute("hour",hour);
        model.addAttribute("minute",minuteTable);
        return "main/main";
    }

}
