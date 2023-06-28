package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {
    public UserService(UserMapper userMapper, HashService hashService) {
        this.hashService = hashService;
        this.userMapper = userMapper;
    }
    private HashService hashService;
    private UserMapper userMapper;
    public int createUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashValue = this.hashService.getHashedValue(user.getPassword(), encodedSalt);
        return this.userMapper.insertUser(new User(null, user.getUsername(), encodedSalt, hashValue, user.getFirstName(), user.getLastName()));
    }
}
