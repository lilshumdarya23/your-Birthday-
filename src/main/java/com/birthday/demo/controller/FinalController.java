package com.birthday.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FinalController {
    @GetMapping("/birthday")
    public String birthdayPage() {
        return "birthday";
    }
    @GetMapping("/gift")
    public String giftPage() {
        return "gift";
    }
}
