package com.departure.shihatsu.web;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.validation.constraints.Min;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.departure.shihatsu.domain.Minute;
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
        
        ArrayList<Object> hour = new ArrayList<>();
        ArrayList<ArrayList<Minute>> minuteTable = new ArrayList<>();
        for (JsonNode node : timeTable.get("HourTable")){
            hour.add(node.get("Hour").asText());
            ArrayList<Minute> minute = new ArrayList<>();
            for (JsonNode node2 : node.get("MinuteTable")){
                Minute local_minute = new Minute();
                local_minute.setMinute(node2.get("Minute").asText());
                if (node2.get("Stop").get("first") != null){
                    local_minute.setIsFirst(node2.get("Stop").get("first").asText());
                } 
                minute.add(local_minute);
            }
            minuteTable.add(minute);
        }

        model.addAttribute("info",info);
        model.addAttribute("hour",hour);
        model.addAttribute("minute",minuteTable);
        return "main/jikoku";
    }
}