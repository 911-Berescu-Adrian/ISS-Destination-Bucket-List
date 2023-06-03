package com.example.BucketList.service;

import com.example.BucketList.domain.Destination;
import com.example.BucketList.repository.IDestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {

    @Autowired
    private IDestinationRepository destinationRepository;

    public Destination saveDestination(Destination destination) {
        return destinationRepository.save(destination);
    }

    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    public Destination getSingleDestination(Long destinationId) {
        return destinationRepository.findById(destinationId).get();
    }
}
