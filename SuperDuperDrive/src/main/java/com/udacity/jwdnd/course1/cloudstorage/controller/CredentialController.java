package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.utils.ERROR;
import com.udacity.jwdnd.course1.cloudstorage.utils.SUCCESS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/credential")
public class CredentialController {
    @Autowired
    private CredentialService credentialService;

    @Autowired
    private EncryptionService encryptionService;

    @PostMapping("/add")
    public String addCredentials(@RequestParam("credentialId") Integer id, @RequestParam("url") String url, @RequestParam("username") String username, @RequestParam("credential_Password") String password,
                                 RedirectAttributes redirectAttributes) {
        try {
            if (id != null) {
                String key = this.credentialService.getKey(id);
                String encryptedPassword = this.encryptionService.encryptValue(password, key);
                this.credentialService.updateCredential(id, url, username, encryptedPassword);
                redirectAttributes.addFlashAttribute("message", SUCCESS.CREDENTIAL_UPDATED.toString());
            } else {
                String key = this.encryptionService.generateKey();
                String encryptedPassword = this.encryptionService.encryptValue(password, key);
                this.credentialService.addCredential(url, username, key, encryptedPassword);
                redirectAttributes.addFlashAttribute("message", SUCCESS.CREDENTIAL_CREATED.toString());

            }
        } catch (NoSuchAlgorithmException e) {
            redirectAttributes.addFlashAttribute("message", ERROR.SOMETHING_WRONG.toString());
        }


        return "redirect:/#nav-credentials";
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteNote(@PathVariable Integer credentialId, RedirectAttributes redirectAttributes) {
        this.credentialService.deleteCredential(credentialId);
        redirectAttributes.addFlashAttribute("message", SUCCESS.CREDENTIAL_DELTED.toString());
        return "redirect:/#nav-credentials";
    }
}
