package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;
@Getter
@Setter
public class File {
    private int id;
    private String name;
    private String contentType;
    private String size;
    private int userId;
    private Blob fileData;
}
