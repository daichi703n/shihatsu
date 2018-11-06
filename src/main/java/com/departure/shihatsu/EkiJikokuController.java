package com.departure.shihatsu;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.util.TreeMap;
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
        // HttpHeaders headers;

        LinkedHashMap result = new LinkedHashMap<>(); // It Works.
        // LinkedHashMap<String,String> result = new LinkedHashMap<String,String>();

        // String result = restTemplate.getForObject(ekiUrl, String.class);
        result = restTemplate.getForObject(ekiUrl, LinkedHashMap.class);
        // Gson gson = new Gson();
        // System.out.println(result);
        Object resultObj = new Gson().toJson(result, Object.class);
        // System.out.print(resultObj);
        System.out.println(result.getClass());
        // System.out.println(resultObj.getClass());
        System.out.println(result.entrySet());
        System.out.println(result.get("ResultSet"));
        LinkedHashMap resultSet = (LinkedHashMap) result.get("ResultSet");
        LinkedHashMap timeTable = (LinkedHashMap) resultSet.get("TimeTable");
        LinkedHashMap info = (LinkedHashMap) timeTable.get("Station");
        Object jikoku = timeTable.get("HourTable");

        System.out.println(result);
        System.out.println(result.getClass());
        System.out.println(result.get("apiVersion"));
        
        // String info = resultObj.toString();
        // String info = resultObj.ResultSet.TimeTable.Station.Name;
        // String jikoku = result."ResultSet"."TimeTable"."HourTable";

        model.addAttribute("info",info.toString());
        model.addAttribute("jikoku",jikoku.toString());
        return "main/main";
    }

}
