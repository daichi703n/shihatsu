package com.departure.shihatsu.web;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

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
    public String jukoku(Model model) throws URISyntaxException,IOException {
        String ekey=System.getenv("EKEY");
        String stationCode = "22602";
        String code = "1150";

        String ekiUrl="https://api.ekispert.jp/v1/json/operationLine/timetable?key="+ekey+"&stationCode="+stationCode+"&code="+code;
        URI uri = new URI(ekiUrl);
        RestTemplate restTemplate = new RestTemplate();

        String resultStr = restTemplate.getForObject(ekiUrl, String.class);
        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(resultStr);
        JsonNode resultSet = root.get("ResultSet");
        JsonNode timeTable = resultSet.get("TimeTable");
        JsonNode station = timeTable.get("Station");
        String info = station.toString();
        
        ArrayList<String> hour = new ArrayList<>();
        ArrayList<String> minuteTable = new ArrayList<>();
        for (JsonNode node : timeTable.get("HourTable")){
            hour.add(node.get("Hour").toString());
            ArrayList<String> minute = new ArrayList<>();
            for (JsonNode node2 : node.get("MinuteTable")){
                minute.add(node2.get("Minute").asText());
            }
            minuteTable.add(minute.toString());
        }

        model.addAttribute("info",info);
        model.addAttribute("hour",hour);
        model.addAttribute("minute",minuteTable);
        return "main/main";
    }
}