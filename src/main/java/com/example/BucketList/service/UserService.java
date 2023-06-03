package com.example.BucketList.service;

import com.example.BucketList.domain.Destination;
import com.example.BucketList.domain.User;
import com.example.BucketList.repository.IBucketListRepository;
import com.example.BucketList.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IBucketListRepository bucketListRepository;

    public String savePrivateDestination(User user, Destination destination) {
        List<Destination> bl = user.getBucketList();
        bl.add(destination);
        user.setBucketList(bl);
        return "+" + user.getEmail() + " added private destination";
    }

    public String savePublicDestination(Destination destination) {
        bucketListRepository.save(destination);
        return "+" + "admin added public destination";
    }

    public String deleteUser(Long userId) {
        User user = userRepository.findById(userId).get();
        userRepository.deleteById(userId);
        return "+" + user.getEmail() + " has been deleted";
    }

    public String deletePublicDestination(Long destinationId) {
        Destination dest = bucketListRepository.findById(destinationId).get();
        if(dest.getStartDate() == null && dest.getEndDate() == null) {
            bucketListRepository.deleteById(destinationId);
            return "+" + "admin has deleted a destination with id " + destinationId;
        }
        return "+" + "admin has not been able to delete destination with id " + destinationId;
    }

    public String deletePrivateDestination(User user, Long destinationId) {
        List<Destination> bl = user.getBucketList();
        int pos = -1;
        for(int i = 0; i < bl.size(); i++) {
            if(Objects.equals(bl.get(i).getDestinationId(), destinationId)
                    && bucketListRepository.findById(destinationId).isPresent()
                    && bl.get(i).getStartDate()!=null && bl.get(i).getEndDate()!=null) {
                bucketListRepository.deleteById(destinationId);
                pos = i;
                break;
            }
        }
        if(pos == -1) {
            return "+" + user.getEmail() + " has not been able to delete destination with id" + destinationId;
        } else {
            bl.remove(pos);
            user.setBucketList(bl);
            return "+" + user.getEmail() + " deleted destination with id " + destinationId;
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getSingleUser(Long userId) {
        return userRepository.findById(userId).get();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
