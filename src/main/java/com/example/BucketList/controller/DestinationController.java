package com.example.BucketList.controller;

import com.example.BucketList.domain.Destination;
import com.example.BucketList.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("destination")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @PostMapping("/save")
    public ResponseEntity<Destination> saveDestination(@RequestBody Destination destination) {
        return new ResponseEntity<Destination>(destinationService.saveDestination(destination), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Destination>> getAllDestinations() {
        return new ResponseEntity<List<Destination>>(destinationService.getAllDestinations(), HttpStatus.OK);
    }

    @GetMapping("/{destinationId}")
    public ResponseEntity<Destination> getSingleDestination(@PathVariable Long destinationId) {
        return new ResponseEntity<Destination>(destinationService.getSingleDestination(destinationId), HttpStatus.OK);
    }
}
