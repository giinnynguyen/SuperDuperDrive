package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Note {
    private int id;
    private String title;
    private String description;
    private String userId;
}
