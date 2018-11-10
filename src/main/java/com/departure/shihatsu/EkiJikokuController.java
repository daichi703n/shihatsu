package com.departure.shihatsu;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;

@Controller
@RequestMapping("/jikoku")
public class EkiJikokuController {

    @RequestMapping(method = RequestMethod.GET)
    public String side(Model model) throws URISyntaxException {
        String ekey=System.getenv("EKEY");
        String stationCode = "22602";
        String code = "1150";

        String ekiUrl="https://api.ekispert.jp/v1/json/operationLine/timetable?key="+ekey+"&stationCode="+stationCode+"&code="+code;
        URI uri = new URI(ekiUrl);
        RestTemplate restTemplate = new RestTemplate();

        LinkedHashMap result = new LinkedHashMap<>(); // It Works.
        // LinkedHashMap<String,String> result = new LinkedHashMap<String,String>();

        result = restTemplate.getForObject(ekiUrl, LinkedHashMap.class);
        // Gson gson = new Gson();
        // System.out.println(result);
        Object resultObj = new Gson().toJson(result, Object.class);
        // System.out.print(resultObj);
        // System.out.println(result.getClass());
        // System.out.println(resultObj.getClass());
        // System.out.println(result.entrySet());
        // System.out.println(result.get("ResultSet"));
        LinkedHashMap resultSet = (LinkedHashMap) result.get("ResultSet");
        LinkedHashMap timeTable = (LinkedHashMap) resultSet.get("TimeTable");
        LinkedHashMap info = (LinkedHashMap) timeTable.get("Station");
        
        ArrayList jikoku = new ArrayList<>();
        jikoku = (ArrayList) timeTable.get("HourTable");

        // System.out.println(jikoku.getClass());
        System.out.println(jikoku.size());

        ArrayList<Integer> hour = new ArrayList<>();
        ArrayList<String> minuteList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> minuteTable = new ArrayList<>();
        for(Object object : jikoku){
            // System.out.println(object);
            // System.out.println(object.getClass());
            LinkedHashMap linkedHashMap = (LinkedHashMap) object;
            // System.out.println(linkedHashMap.getClass());
            Object hourObject = linkedHashMap.get("Hour");
            hour.add(Integer.valueOf(hourObject.toString()));
            // System.out.println(hour);
            minuteList = (ArrayList) linkedHashMap.get("MinuteTable");
            ArrayList<Integer> minute = new ArrayList<>();
            // minute.clear();
            for(Object object2 : minuteList){
                // System.out.println(object2);
                LinkedHashMap linkedHashMap2 = (LinkedHashMap) object2;
                Object minuteObject = linkedHashMap2.get("Minute");
                minute.add(Integer.parseInt(minuteObject.toString()));
                // System.out.println(minute);
                // System.out.println("minute");
            }
            Integer hourCurrent = Integer.valueOf(hourObject.toString());
            // System.out.println(minute);
            minuteTable.add(minute);
            // minuteTable.add(minute);
            // System.out.println(minuteTable);
            // System.out.println("hour");
        }
        
        model.addAttribute("info",info.toString());
        // model.addAttribute("jikoku",jikoku.toString());
        model.addAttribute("hour",hour);
        // model.addAttribute("hour",hour.toString());
        model.addAttribute("minute",minuteTable);
        // model.addAttribute("minute",minuteTable.toString());
        return "main/main";
    }

}
