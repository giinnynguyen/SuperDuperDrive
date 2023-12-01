package com.ginny.mstodo.service;

import com.ginny.mstodo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public boolean isUserExist(Integer userId) {
        return userRepository.findUserById(userId) != null;
    }
}
