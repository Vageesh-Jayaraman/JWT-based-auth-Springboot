package com.vageesh.jwt_auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vageesh.jwt_auth.models.AppUser;
import com.vageesh.jwt_auth.services.AppUserService;

@RestController
public class AppUserController {

    @Autowired
    private AppUserService appUserService;
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUser user) {
        return appUserService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody AppUser user){
        return appUserService.verify(user);
    }
}
