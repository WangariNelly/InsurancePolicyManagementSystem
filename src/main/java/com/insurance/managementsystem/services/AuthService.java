package com.insurance.managementsystem.services;

import com.insurance.managementsystem.models.Role;
import com.insurance.managementsystem.models.User;
import com.insurance.managementsystem.repos.RoleRepository;
import com.insurance.managementsystem.repos.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class AuthService {


    private UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initRoles() {
        if (roleRepository.findByName("USER") == null) {
            roleRepository.save(new Role("USER"));
        }
        if (roleRepository.findByName("ADMIN") == null) {
            roleRepository.save(new Role("ADMIN"));
        }
    }

    public String registerUser(User user, String... roles) {
        HashSet<Role> assignedRoles = new HashSet<>();
        for (String roleName : roles) {
            Role role = roleRepository.findByName(roleName);
            if (role == null) {
                throw new IllegalStateException("Role " + roleName + " not found. Ensure roles are initialized.");
            }
            assignedRoles.add(role);
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRoles(assignedRoles);
        userRepository.save(newUser);
        return "User registered successfully";
    }

    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }

        return passwordEncoder.matches(password, user.getPassword());
//        return user != null && passwordEncoder.matches(password, user.getPassword());
    }
}
