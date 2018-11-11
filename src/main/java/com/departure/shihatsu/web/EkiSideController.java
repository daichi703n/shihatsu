package com.departure.shihatsu.web;

// import java.net.URI;
import java.net.URISyntaxException;

// import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/side")
public class EkiSideController {

    @RequestMapping(method = RequestMethod.GET)
    public String side(Model model) throws URISyntaxException {
        String ekey=System.getenv("EKEY");
        String ekiUrl="https://api.ekispert.jp/v1/json/operationLine/timetable?key="+ekey+"&stationCode=22602&code=1150";
        // Object list="";
        // URI uri = new URI(ekiUrl);
        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        // HttpHeaders headers;

        //HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        //ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity, String.class);
        String index1 = "42";
        String index2 = "21";
        String result = restTemplate.getForObject(ekiUrl, String.class, index1, index2);

        model.addAttribute("msg",result);
        return "check/check";
    }

}
