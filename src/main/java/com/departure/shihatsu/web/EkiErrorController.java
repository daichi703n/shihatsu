package com.departure.shihatsu.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Ref: https://qiita.com/leonis_sk/items/c954face2c5c1cbf3802
@Controller
public class EkiErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(
        Model model,
        RedirectAttributes attributes
        ) {
        attributes.addFlashAttribute("alert_message", "エラー：指定の情報は表示できませんでした。");
        return "redirect:/line";
    }
    
    @Override
    public String getErrorPath() {
        return "/error";
    }
}
