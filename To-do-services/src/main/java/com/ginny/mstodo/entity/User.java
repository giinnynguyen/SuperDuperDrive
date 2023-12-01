package com.ginny.mstodo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue
    private int id;

   private String name;

    @OneToMany
    private List<ToDo> todos = new ArrayList<>();
}
