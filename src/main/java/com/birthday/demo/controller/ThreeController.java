package com.birthday.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/three")
public class ThreeController {

    @GetMapping
    public String three() {
        return "three";
    }

    @GetMapping("/fly")
    public String fly() {
        return "fly";
    }

}
