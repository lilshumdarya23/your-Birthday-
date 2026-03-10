package com.birthday.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @GetMapping("/one")
    public String home(Model model) {
        model.addAttribute("githubLink", "https://github.com/lilshumdarya23/your-Birthday-");
        return "index";
    }
    @GetMapping("/")
    public String home() {
        return "gifts";
    }
}
