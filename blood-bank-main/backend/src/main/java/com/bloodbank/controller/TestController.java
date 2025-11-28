package com.bloodbank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @GetMapping("/api/test")
    public String test() {
        return "Blood Bank Backend is running successfully!";
    }
    
    @GetMapping("/api/health")
    public String health() {
        return "Server is healthy and connected to database!";
    }
}