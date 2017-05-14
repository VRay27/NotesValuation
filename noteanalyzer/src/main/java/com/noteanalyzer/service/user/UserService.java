package com.noteanalyzer.service.user;

import java.util.Optional;

import com.noteanalyzer.entity.user.User;



public interface UserService {
    public Optional<User> getByUsername(String username);
}
