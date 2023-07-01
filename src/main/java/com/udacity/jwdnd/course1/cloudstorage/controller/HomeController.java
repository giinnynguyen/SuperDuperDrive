package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.FileUploadService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private NoteService noteService;

    @GetMapping()
    public String getHomePage(Model model) {
        File[] files = this.fileUploadService.getFiles();
        model.addAttribute("files", files);

        Note[] notes = this.noteService.getNotes();
        model.addAttribute("notes", notes);

        Note note = new Note(null, "Title", "Description");
        model.addAttribute("newNote", note);
        return "home";
    }

    @PostMapping("/add")
    public String addNote(@RequestParam("noteId") String noteId, @RequestParam("title") String noteTitle, @RequestParam("description") String noteDescription) {
        return "home";
    }
}
