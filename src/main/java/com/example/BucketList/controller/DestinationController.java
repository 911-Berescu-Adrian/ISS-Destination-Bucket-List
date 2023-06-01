package com.example.BucketList.controller;

import com.example.BucketList.domain.Destination;
import com.example.BucketList.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @PostMapping("/destination")
    public Destination addDestination(@RequestBody Destination destination) {
        return destinationService.saveDestination(destination);
    }
}
