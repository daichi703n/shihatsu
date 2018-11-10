package com.departure.shihatsu;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

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

        LinkedHashMap result = new LinkedHashMap<>();

        result = restTemplate.getForObject(ekiUrl, LinkedHashMap.class);
        Object resultObj = new Gson().toJson(result, Object.class);
        LinkedHashMap resultSet = (LinkedHashMap) result.get("ResultSet");
        LinkedHashMap timeTable = (LinkedHashMap) resultSet.get("TimeTable");
        LinkedHashMap info = (LinkedHashMap) timeTable.get("Station");
        
        ArrayList jikoku = new ArrayList<>();
        jikoku = (ArrayList) timeTable.get("HourTable");

        System.out.println(jikoku.size());

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
