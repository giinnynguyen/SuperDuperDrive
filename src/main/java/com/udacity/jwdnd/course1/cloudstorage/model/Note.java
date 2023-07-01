package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Note {
    private Integer noteId;
    private String title;
    private String description;
    private Integer userId;
    public Note(Integer id, String title, String description) {
        this.noteId = id;
        this.title = title;
        this.description = description;
    }
}
