package com.example.BucketList.service;

import com.example.BucketList.domain.Destination;
import com.example.BucketList.domain.User;
import com.example.BucketList.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private IUserRepository userRepository;

    /*TODO implement error handling for destination start date and end date
    *  they are mandatory for when the user adds a destination*/
    @Override
    public User saveDestination(Long userId, Destination destination) {
        User user = this.userRepository.findById(userId).get();
        List<Destination> bucketList = user.getBucketList();
        bucketList.add(destination);
        return this.userRepository.save(user);
    }
}
