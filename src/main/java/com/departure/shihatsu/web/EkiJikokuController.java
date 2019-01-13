package com.departure.shihatsu.web;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.departure.shihatsu.domain.JikokuMinute;
import com.departure.shihatsu.domain.JikokuInfo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/jikoku")
public class EkiJikokuController {

    @RequestMapping(method = RequestMethod.GET)
    @ConfigurationProperties(prefix="secret")
    public String jukoku(
        @RequestParam Map<String, String> queryParameters, 
        Model model
    ) throws URISyntaxException,IOException {
        // TODO: キーの取り方を改善したい
        String ekey=System.getenv("EKEY");

        // String stationCode = "22602";
        // String code = "1150";
        String stationCode = queryParameters.get("stationCode");
        String code = queryParameters.get("code");
        String lineName = queryParameters.get("line");
        // TODO: エラーハンドリング
        
        // 駅情報一覧を取得して当該路線の最初のcodeを取得する
        if (code == null){
            String tmpEkiUrl="https://api.ekispert.jp/v1/json/operationLine/timetable?key="+ekey+"&stationCode="+stationCode;
            RestTemplate tmpRestTemplate = new RestTemplate();
            String tmpResultStr = tmpRestTemplate.getForObject(tmpEkiUrl, String.class);
            ObjectMapper tmpMapper = new ObjectMapper();
            JsonNode tmpTimeTable = tmpMapper.readTree(tmpResultStr).get("ResultSet").get("TimeTable");

            List<String> tmpList = new ArrayList<>();
            for (Object obj : tmpTimeTable) {
                tmpList.add((String) obj.toString());
                if (obj.toString().indexOf(lineName) != -1){break;}
            }
            code = tmpTimeTable.get(tmpList.size()-1).get("code").asText();
        }
        String ekiUrl="https://api.ekispert.jp/v1/json/operationLine/timetable?key="+ekey+"&stationCode="+stationCode+"&code="+code;
        URI uri = new URI(ekiUrl);
        RestTemplate restTemplate = new RestTemplate();
        
        // TODO: エラーハンドリング
        String resultStr = restTemplate.getForObject(ekiUrl, String.class);
        ObjectMapper mapper = new ObjectMapper();

        // TODO: info取得方法を改善したい
        JsonNode root = mapper.readTree(resultStr);
        JsonNode resultSet = root.get("ResultSet");
        JsonNode timeTable = resultSet.get("TimeTable");
        JsonNode station = timeTable.get("Station");
        String info = station.toString();

        JikokuInfo jikokuInfo = new JikokuInfo();
        jikokuInfo.setStationName(timeTable.get("Station").get("Name").asText());
        jikokuInfo.setLineName(timeTable.get("Line").get("Name").asText());
        jikokuInfo.setLineDir(timeTable.get("Line").get("Direction").asText());

        // System.out.println(jikokuInfo);
        
        ArrayList<Object> hour = new ArrayList<>();
        ArrayList<ArrayList<JikokuMinute>> minuteTable = new ArrayList<>();
        for (JsonNode node : timeTable.get("HourTable")){
            hour.add(node.get("Hour").asText());
            ArrayList<JikokuMinute> minute = new ArrayList<>();
            for (JsonNode node2 : node.get("MinuteTable")){
                JikokuMinute local_minute = new JikokuMinute();
                local_minute.setMinute(node2.get("Minute").asText());
                if (node2.get("Stop").get("first") != null){
                    local_minute.setIsFirst(node2.get("Stop").get("first").asText());
                } 
                minute.add(local_minute);
            }
            minuteTable.add(minute);
        }

        model.addAttribute("info",jikokuInfo);
        model.addAttribute("hour",hour);
        model.addAttribute("minute",minuteTable);
        return "main/jikoku";
    }
}