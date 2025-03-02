package org.example.globetrotterapplication.api.controller;

import org.example.globetrotterapplication.api.model.Destination;
import org.example.globetrotterapplication.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/globetrotter")
public class DestinationController {

    private final DestinationService destinationService;

    @Autowired
    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping("/destination/{index}")
    @CrossOrigin
    public Destination getDestination(@PathVariable("index") Integer index) {
            return destinationService.getDestination(index);
    }
}
