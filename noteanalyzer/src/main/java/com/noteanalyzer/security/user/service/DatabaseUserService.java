package com.noteanalyzer.security.user.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.noteanalyzer.security.entity.User;
import com.noteanalyzer.security.security.UserService;

/**
 * Mock implementation.Need to be replace with real service.
 */
@Service
public class DatabaseUserService implements UserService {

    @Override
    public Optional<User> getByUsername(String username) {
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return Optional.of(new User(new Long(1),"arvind",passwordEncoder.encode("pwd"),null));
        
    	
    }
}
