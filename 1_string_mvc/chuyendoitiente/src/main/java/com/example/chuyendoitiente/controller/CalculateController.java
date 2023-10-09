package com.example.chuyendoitiente.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculateController {
    @GetMapping("/calculate")
    public String showForm(){
        return "calculate";
    }
     @PostMapping("/calculate")
    public String save(Model model, @RequestParam float dong){
        float usd=dong/23000;
        model.addAttribute("usd",usd);
        return "calculate";
     }

}
