package com.cydeo.controller;

import com.cydeo.bootstrap.DataGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student") //class level end point
public class StudentController {
                                                  //default
    @RequestMapping(value = "/register", method = RequestMethod.GET)//localhost:8080/student/register
    @GetMapping("/register")
    public String register(Model model){

        model.addAttribute("students", DataGenerator.createStudent());

        return "student/register"; //view
    }

    @RequestMapping("/welcome") //localhost:8080/student/welcome?name=Tierra
    @PostMapping("/welcome")
    public String welcome(){


        return "student/welcome"; //view
    }



}
