package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;
@Service
public class NoteService {
    private NoteMapper noteMapper;
    private UserService userService;

    public NoteService(NoteMapper noteMapper, UserService userService) {
        this.noteMapper = noteMapper;
        this.userService = userService;
    }

    public int addNote(Note note) {
        return this.noteMapper.addNote(note);
    }

    public Note[] getNotes() {
        Integer loginUserId = this.userService.getLoginUserId();
        Note[] tests = this.noteMapper.getNotes(loginUserId);
        return this.noteMapper.getNotes(loginUserId);
    }

    public int deleteNote(Integer noteId) {
        return this.noteMapper.deleleNote(noteId);
    }

    public int updateNote(Note note) {
        return this.noteMapper.updateNote(note);
    }

}
