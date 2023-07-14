package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addNote(@RequestParam("title") String noteTitle, @RequestParam("description") String noteDescription) {
        Note note = new Note(null, noteTitle, noteDescription, userService.getLoginUserId());
        this.noteService.addNote(note);
        return "redirect:/#nav-notes";
    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, Model model) {
        this.noteService.deleteNote(noteId);
        return "redirect:/#nav-notes";
    }

    @PutMapping("/add")
    public String updateNote(@RequestParam("noteId") Integer noteId, @RequestParam("title") String noteTitle, @RequestParam("description") String noteDescription) {
        Note note = new Note(noteId, noteTitle, noteDescription, userService.getLoginUserId());
        this.noteService.updateNote(note);
        return "redirect:/#nav-notes";
    }
}
