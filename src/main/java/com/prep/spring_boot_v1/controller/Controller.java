package com.prep.spring_boot_v1.controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    @GetMapping("/")
    String getText() {
        return "Hello";
    }

    // Navigators can only do get request
    // Instead do this to test: curl -X POST http://localhost:8080/test/hello
    @PostMapping("/test/{id}")
    void showText(@PathVariable String id) {
        System.out.println("@@@@@@" + " " + id);
    }
}