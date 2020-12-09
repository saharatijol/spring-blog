package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "This is a landing page!";
    }

    @GetMapping("/home")
    public String welcome() {
        return "home";
        // if you are using templating, it needs to match the filename
        // return type is often String if returning templating resources/templates/[filename]
    }
}
