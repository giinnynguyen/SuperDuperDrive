package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addNote(@RequestParam("title") String noteTitle, @RequestParam("description") String noteDescription, RedirectAttributes redirectAttributes) {
        Note note = new Note(null, noteTitle, noteDescription, userService.getLoginUserId());
        this.noteService.addNote(note);
        redirectAttributes.addFlashAttribute("message", "Note added successfully");
        return "redirect:/#nav-notes";
    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, RedirectAttributes redirectAttributes) {
        this.noteService.deleteNote(noteId);
        redirectAttributes.addFlashAttribute("message", "Note deleted successfully");
        return "redirect:/#nav-notes";
    }

    @PutMapping("/add")
    public String updateNote(@RequestParam("noteId") Integer noteId, @RequestParam("title") String noteTitle,
                             @RequestParam("description") String noteDescription, RedirectAttributes redirectAttributes) {
        Note note = new Note(noteId, noteTitle, noteDescription, userService.getLoginUserId());
        this.noteService.updateNote(note);
        redirectAttributes.addFlashAttribute("message", "Note updated successfully");
        return "redirect:/#nav-notes";
    }
}
