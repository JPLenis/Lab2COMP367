package com.juanlenis.example;

import java.time.LocalTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Comp367Lab2Application {
    public static void main(String[] args) {
        SpringApplication.run(Comp367Lab2Application.class, args);
    }
}

@RestController
class GreetingController {
    private final String name = "Juan Lenis";

    @GetMapping("/")
    public String greeting() {
        LocalTime now = LocalTime.now();
        if (now.isBefore(LocalTime.NOON)) {
            return "Good morning, " + name + ", Welcome to COMP367";
        } else {
            return "Good afternoon, " + name + ", Welcome to COMP367";
        }
    }
}
