package org.example.globetrotterapplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/globetrotte")
public class HelloController {


    @GetMapping("/destination")
    private String sayHello() {
        return "Hello World";
    }
}