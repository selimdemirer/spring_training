package com.cydeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarController {

    @RequestMapping("/info") // End Point in the browser
    public String carInfo(@RequestParam String make, Model model){

        model.addAttribute("make",make);

        return "car/car-info";// View Resolver
    }

    @RequestMapping("/info2") // End Point in the browser
    public String carInfo2(@RequestParam(value="make",required = false,defaultValue = "KIA") String make, Model model){

        model.addAttribute("make",make);

        return "car/car-info";// View Resolver
    }

    @RequestMapping("/info3") // End Point in the browser
    public String carInfo3(@RequestParam String make, @RequestParam int year, Model model){ //http://localhost:8080/info3?make=BMW&year=2015

        model.addAttribute("make",make);
        model.addAttribute("year",year);


        return "car/car-info";// View Resolver
    }

    //localhost:8080/info/honda/2015
    @RequestMapping("/info/{make}/{year}") // End Point in the browser
    public String getCarInfo(@PathVariable String make,@PathVariable String year){

        System.out.println(make);
        System.out.println(year);

        return "car/car-info";// View Resolver
    }

}
