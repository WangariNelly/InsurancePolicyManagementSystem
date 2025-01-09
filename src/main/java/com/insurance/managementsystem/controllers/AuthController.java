package com.insurance.managementsystem.controllers;


import com.insurance.managementsystem.dtos.LoginRequest;
import com.insurance.managementsystem.models.User;
import com.insurance.managementsystem.services.AuthService;
import com.insurance.managementsystem.services.UserDetailsServiceImpl;
import com.insurance.managementsystem.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthController(AuthService authService, JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            String responseMessage = authService.registerUser(user, "USER");
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            boolean isAuthenticated = authService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
            if (isAuthenticated) {
                String token = jwtUtil.generateToken(loginRequest.getUsername());
                return ResponseEntity.ok("Bearer " + token);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid credentials");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid login");
        }
    }
}

