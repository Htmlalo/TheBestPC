package com.thebest.thebestpc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/banner")
public class BannerController {

    @GetMapping
    public String index() {


        return "view/Banner";
    }
}
