package com.departure.shihatsu.web;

import java.awt.List;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

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
import com.departure.shihatsu.domain.Corporation;
import com.departure.shihatsu.domain.LineList;
import com.departure.shihatsu.domain.JikokuInfo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/line")
public class EkiLineController {

    @RequestMapping(method = RequestMethod.GET)
    @ConfigurationProperties(prefix="secret")
    public String line(
        @RequestParam Map<String, String> queryParameters, 
        Model model
    ) throws URISyntaxException,IOException {
        // TODO: キーの取り方を改善したい
        String ekey=System.getenv("EKEY");

        // String stationCode = "22602";
        // String code = "1150";
        String prefectureCode = queryParameters.get("prefectureCode");
        String operationLineCode = queryParameters.get("operationLineCode");
        if (prefectureCode==null) {prefectureCode = "13";};
        if (operationLineCode==null) {operationLineCode = "13";};
        // TODO: エラーハンドリング
        
        String operationLineUrl="https://api.ekispert.jp/v1/json/operationLine?key="+ekey+"&prefectureCode="+prefectureCode;
        URI uri = new URI(operationLineUrl);
        RestTemplate restTemplate = new RestTemplate();

        // TODO: エラーハンドリング
        String resultStr = restTemplate.getForObject(operationLineUrl, String.class);
        ObjectMapper mapper = new ObjectMapper();

        // TODO: info取得方法を改善したい
        JsonNode root = mapper.readTree(resultStr);
        JsonNode resultSet = root.get("ResultSet");
        Corporation[] corporation = mapper.readValue(resultSet.get("Corporation").toString(), Corporation[].class);
        LineList[] lineList = mapper.readValue(resultSet.get("Line").toString(), LineList[].class);

        ArrayList<Object> hour = new ArrayList<>();
        ArrayList<ArrayList<JikokuMinute>> minuteTable = new ArrayList<>();
        // for (JsonNode node : timeTable.get("HourTable")){
        //     hour.add(node.get("Hour").asText());
        //     ArrayList<JikokuMinute> minute = new ArrayList<>();
        //     for (JsonNode node2 : node.get("MinuteTable")){
        //         JikokuMinute local_minute = new JikokuMinute();
        //         local_minute.setMinute(node2.get("Minute").asText());
        //         if (node2.get("Stop").get("first") != null){
        //             local_minute.setIsFirst(node2.get("Stop").get("first").asText());
        //         } 
        //         minute.add(local_minute);
        //     }
        //     minuteTable.add(minute);
        // }

        model.addAttribute("lineList",lineList);
        model.addAttribute("corporation",corporation);
        return "main/line";
    }
}