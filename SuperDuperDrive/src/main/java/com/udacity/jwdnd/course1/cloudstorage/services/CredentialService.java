package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {
    @Autowired
    private CredentialMapper credentialMapper;

    @Autowired
    private UserService userService;

    public int addCredential(String url, String username, String key, String password) {
        Integer loginId = this.userService.getLoginUserId();
        Credential credential = new Credential(null, url, username, key, password, loginId);
        return this.credentialMapper.addCredential(credential);
    }

    public Credential[] getCredentials() {
        Integer loginUserId = this.userService.getLoginUserId();
        return this.credentialMapper.getCredentials(loginUserId);
    }

    public int deleteCredential(Integer credentialId) {
        return this.credentialMapper.deleteCredential(credentialId);
    }

    public int updateCredential(Integer credentialId, String url, String username, String password) {
        Integer loginUserId = this.userService.getLoginUserId();
        Credential credential = new Credential(credentialId, url, username, null, password, loginUserId);
        return this.credentialMapper.updateCredential(credential);
    }

    public String getKey(Integer credentialId) {
        return this.credentialMapper.getKey(credentialId);
    }
}
