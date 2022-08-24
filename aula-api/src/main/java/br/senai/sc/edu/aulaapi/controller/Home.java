package br.senai.sc.edu.aulaapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class Home {

    @GetMapping("/index")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/logout")
    public String bye(){
        return "Tchau";
    }
}
