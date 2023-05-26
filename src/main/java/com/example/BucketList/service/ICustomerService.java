package com.example.BucketList.service;

import com.example.BucketList.domain.Destination;
import com.example.BucketList.domain.User;

public interface ICustomerService {
    User saveDestination(Long userId, Destination destination);
}
