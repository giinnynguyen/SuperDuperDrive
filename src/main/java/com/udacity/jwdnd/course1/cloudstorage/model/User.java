package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class User {
    private int id;
    private String name;
    private String salt;
    private String password;
    private String firstName;
    private String lastName;
}
