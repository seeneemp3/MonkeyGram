package com.personal.monkeygram.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping("/home")
    public String homePage() {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Welcome to Monkeygram</title>
                </head>
                <body>
                    <h1>Welcome to Monkeygram</h1>
                    <p>Hello, users! This is Monkeygram, your social networking platform.</p>
                </body>
                </html>""";
    }
}
