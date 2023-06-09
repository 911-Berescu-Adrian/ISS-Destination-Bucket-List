package com.example.BucketList.service;

import com.example.BucketList.domain.Destination;
import com.example.BucketList.domain.Role;
import com.example.BucketList.domain.User;
import com.example.BucketList.dtos.UserDTO;
import com.example.BucketList.dtos.UserPasswordDTO;
import com.example.BucketList.repository.IBucketListRepository;
import com.example.BucketList.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IBucketListRepository bucketListRepository;


    public String saveUser(UserDTO userDTO) {
        User user = new User();

        List<Role> roles = new ArrayList<>();
        List<Destination> bucketList = new ArrayList<>();

        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRoles(roles);
        user.setBucketList(bucketList);
        this.userRepository.save(user);
        return "User successfully added!";
    }

    public String savePrivateDestination(User user, Destination destination) {
        List<Destination> bl = user.getBucketList();
        bl.add(destination);
        user.setBucketList(bl);
        return "+" + user.getEmail() + " added private destination";
    }

    public String deleteUser(Long userId) {
        User user = userRepository.findById(userId).get();
        userRepository.deleteById(userId);
        return "+" + user.getEmail() + " has been deleted";
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

    public User updatePassword(Long userId, UserPasswordDTO userPasswordDTO) {
        User user = userRepository.findById(userId).get();
        user.setPassword(userPasswordDTO.getPassword());
        return userRepository.findByEmail(user.getEmail());
    }
}
