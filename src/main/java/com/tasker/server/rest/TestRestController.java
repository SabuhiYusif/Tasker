package com.tasker.server.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    @GetMapping("/home")
    public String home(){
        return "Hello World2!";
    }
}
