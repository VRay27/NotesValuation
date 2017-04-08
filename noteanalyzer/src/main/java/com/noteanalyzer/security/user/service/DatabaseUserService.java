package com.noteanalyzer.security.user.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.noteanalyzer.security.entity.User;
import com.noteanalyzer.security.security.UserService;

/**
 * Mock implementation.
 * 
 * @author vladimir.stankovic
 *
 * Aug 4, 2016
 */
@Service
public class DatabaseUserService implements UserService {
   // private final UserRepository userRepository;
    
   /* @Autowired
    public DatabaseUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public UserRepository getUserRepository() {
        return userRepository;
    }*/

    @Override
    public Optional<User> getByUsername(String username) {
    	//UserRole userRole = new UserRole(new Long(1),Role.ADMIN);
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return Optional.of(new User(new Long(1),"arvind",passwordEncoder.encode("pwd"),null));
        
    	
    	//return this.userRepository.findByUsername(username);
    }
}
