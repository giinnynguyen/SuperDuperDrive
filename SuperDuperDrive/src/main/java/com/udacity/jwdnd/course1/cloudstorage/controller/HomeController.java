package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileUploadService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private EncryptionService encryptionService;

    @GetMapping()
    public String getHomePage(Model model, @ModelAttribute("message") String message){
        File[] files = this.fileUploadService.getFiles();
        model.addAttribute("files", files);

        Note[] notes = this.noteService.getNotes();
        model.addAttribute("notes", notes);

        Credential[] credentials = this.credentialService.getCredentials();
        model.addAttribute("credentials", credentials);

        Note note = new Note(null, "Title", "Description");
        model.addAttribute("newNote", note);
        Credential credential = new Credential();
        model.addAttribute("newCredential", credential);
        model.addAttribute("encryptionService", this.encryptionService);

        String[] messages = Message.homeMessages();
        model.addAttribute("messages", messages);
        model.addAttribute("message", message);
        return "home";
    }

}
