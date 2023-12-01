package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
@Scope("singleton")
public class UserService {
    private int userLoginId = 0;
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

    public Integer getLoginUserId() {
        if (this.userLoginId > 0) {
            return this.userLoginId;
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Integer userId = this.userMapper.getUserId(username);
            return userId;
        }
    }
}
