package com.vageesh.jwt_auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.vageesh.jwt_auth.models.AppUser;
import com.vageesh.jwt_auth.repository.AppUserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService{


    @Autowired
    AppUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .build();
    }
    
    
}
