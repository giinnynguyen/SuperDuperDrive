package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/credential")
public class CredentialController {
    @Autowired
    private CredentialService credentialService;

    @Autowired
    private EncryptionService encryptionService;

    @PostMapping("/add")
    public String addCredentials(@RequestParam("credentialId") Integer id, @RequestParam("url") String url, @RequestParam("username") String username, @RequestParam("credential_Password") String password) throws NoSuchAlgorithmException {
        if (id != null) {
            String key = this.credentialService.getKey(id);
            String encryptedPassword = this.encryptionService.encryptValue(password, key);
            this.credentialService.updateCredential(id, url, username, encryptedPassword);
        } else {
            String key = this.encryptionService.generateKey();
            String encryptedPassword = this.encryptionService.encryptValue(password, key);
            this.credentialService.addCredential(url, username, key, encryptedPassword);

        }
        return "redirect:/home#nav-credentials";
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteNote(@PathVariable Integer credentialId, Model model) {
        this.credentialService.deleteCredential(credentialId);
        return "redirect:/home#nav-credentials";
    }
}
