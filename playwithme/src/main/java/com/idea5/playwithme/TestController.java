package com.idea5.playwithme;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/")
    public String test(Model model) {

        model.addAttribute("data", "test page");
        return "test";
    }
}
