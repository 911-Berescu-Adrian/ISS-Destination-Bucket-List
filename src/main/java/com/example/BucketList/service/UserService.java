package com.example.BucketList.service;

import com.example.BucketList.domain.Destination;
import com.example.BucketList.domain.Role;
import com.example.BucketList.domain.User;
import com.example.BucketList.dtos.UserDTO;
import com.example.BucketList.repository.IRoleRepository;
import com.example.BucketList.repository.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private IUserRepository userRepository;
    private IRoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(IUserRepository userRepository, IRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveDestination(Long userId, Destination destination) {
        User user = this.userRepository.findById(userId).get();
        List<Destination> bucketList = user.getBucketList();
        bucketList.add(destination);
        return this.userRepository.save(user);
    }

    @Override
    public void saveUser(UserDTO UserDTO) {
        User user = new User();
        user.setEmail(UserDTO.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(UserDTO.getPassword()));
        Role role = roleRepository.findByName("USER");
        user.setRoles(List.of(role));

        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDTO(user))
                .collect(Collectors.toList());
    }

    private UserDTO mapToUserDTO(User user){
        UserDTO UserDTO = new UserDTO();
        UserDTO.setEmail(user.getEmail());
        return UserDTO;
    }
}
