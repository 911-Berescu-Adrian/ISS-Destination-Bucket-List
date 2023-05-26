package com.example.BucketList.config;

import com.example.BucketList.domain.Role;
import com.example.BucketList.domain.User;
import com.example.BucketList.repository.IRoleRepository;
import com.example.BucketList.repository.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        createRoleIfNotFound("ADMIN");
        createRoleIfNotFound("USER");

        Role adminRole = roleRepository.findByName("ADMIN");
        User user = new User();
        user.setPassword(passwordEncoder.encode("admin"));
        user.setEmail("admin@bld.com");
        user.setRoles(Arrays.asList(adminRole));
        if(userRepository.findByEmail(user.getEmail()) == null) {
            userRepository.save(user);
        }

        alreadySetup = true;
    }

    @Transactional
    Role createRoleIfNotFound(String name) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }
}