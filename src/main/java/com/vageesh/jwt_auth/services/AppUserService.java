package com.vageesh.jwt_auth.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vageesh.jwt_auth.models.AppUser;
import com.vageesh.jwt_auth.repository.AppUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;

@Service
public class AppUserService {

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
}

