package com.noteanalyzer.security.security;

import java.util.Optional;

import com.noteanalyzer.security.entity.User;

/**
 * 
 * @author vladimir.stankovic
 *
 * Aug 17, 2016
 */
public interface UserService {
    public Optional<User> getByUsername(String username);
}
