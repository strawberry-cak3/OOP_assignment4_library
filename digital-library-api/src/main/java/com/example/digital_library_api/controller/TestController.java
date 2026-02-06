package com.example.digital_library_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Digital Library API! Сервер работает!";
    }

    @GetMapping("/status")
    public String status() {
        return "API is up and running. PostgreSQL connected.";
    }
}
