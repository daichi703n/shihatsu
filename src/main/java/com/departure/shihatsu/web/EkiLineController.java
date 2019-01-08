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
import com.departure.shihatsu.domain.StationList;
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
        // if (operationLineCode==null) {operationLineCode = "115";};
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
        
        model.addAttribute("lineList",lineList);
        model.addAttribute("corporation",corporation);

        StationList[] stationList = null;
        // Get Station Info
        if(operationLineCode!=null){
            String stationListUrl="https://api.ekispert.jp/v1/json/station?key="+ekey+"&operationLineCode="+operationLineCode;
            RestTemplate stationRestTemplate = new RestTemplate();
            String stationResultStr = stationRestTemplate.getForObject(stationListUrl, String.class);
            JsonNode stationResultSet = mapper.readTree(stationResultStr).get("ResultSet");
            ObjectMapper stationMapper = new ObjectMapper();
            stationList = stationMapper.readValue(stationResultSet.get("Point").toString(), StationList[].class);
        }else{
            
        }

        model.addAttribute("stationList", stationList);
        model.addAttribute("prefectureCode", prefectureCode);
        model.addAttribute("operationLineCode", operationLineCode);

        return "main/line";
    }
}