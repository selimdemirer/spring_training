package com.cydeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SelimController {

    @RequestMapping("/selim")
    public String getSelimsPage(){
        return "selim.html";
    }

}
