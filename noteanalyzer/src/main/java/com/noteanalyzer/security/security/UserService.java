package com.noteanalyzer.security.security;

import java.util.Optional;

import com.noteanalyzer.security.entity.User;

public interface UserService {
    public Optional<User> getByUsername(String username);
}
