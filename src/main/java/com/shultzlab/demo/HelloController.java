package com.shultzlab.demo;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloController {
    // http://localhost:8080
    @RequestMapping("/")
    public String helloWorld() {
        return "Hello World!!";
    }

    // http://localhost:8080/tom
    @RequestMapping("/{name}")
    public String getMessage(@PathVariable("name") String name) {
        return formatName(name);
    }

    // http://localhost:8080/json?name=tom
    private final AtomicLong counter = new AtomicLong();
    @GetMapping("/json")
    public Message greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Message(counter.incrementAndGet(), formatName(name));
    }

    // Helper functions
    private String formatName(String name) {
        // Format name to have uppercase first letter and lowercase rest
        String firstLetter = name.charAt(0) + "";
        firstLetter = firstLetter.toUpperCase();

        String restOfLetters = name.substring(1).toLowerCase();

        String finalName = firstLetter + restOfLetters;
        String returnString = "Hello, " + finalName + "!!!";

        return returnString;
    }
}

