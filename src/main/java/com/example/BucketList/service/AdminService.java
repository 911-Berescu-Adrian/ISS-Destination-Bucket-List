package com.example.BucketList.service;

import com.example.BucketList.domain.Destination;
import com.example.BucketList.repository.IBucketListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService {
    @Autowired
    private IBucketListRepository bucketListRepository;

    @Override
    public Destination saveDestination(Destination destination) {
        return this.bucketListRepository.save(destination);
    }
}
