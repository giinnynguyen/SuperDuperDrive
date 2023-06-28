package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Credential {
    private int id;
    private String url;
    private String userName;
    private String key;
    private String password;
    private int userId;
}
