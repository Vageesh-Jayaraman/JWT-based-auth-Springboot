package com.vageesh.jwt_auth.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vageesh.jwt_auth.models.AppUser;
import com.vageesh.jwt_auth.repository.AppUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;

@Service
public class AppUserService {

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    JWTService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity<?> register(AppUser user) {

        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Username already exists");
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        AppUser savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    public String verify(AppUser user) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        return authentication.isAuthenticated() ? jwtService.generateToken(user.getUsername()) : "Failure";

    }
}

