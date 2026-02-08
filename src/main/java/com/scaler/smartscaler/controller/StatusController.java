package com.scaler.smartscaler.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping("/")
    public String home() {
        return "Smart Scaler Backend is Running";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
