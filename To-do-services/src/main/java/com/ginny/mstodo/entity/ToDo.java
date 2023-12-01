package com.ginny.mstodo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="to_do")
public class ToDo {
    @Id
    @GeneratedValue
    private Long id;

    private String task;

    @Enumerated
    private Status status;

    private int userId;

    public ToDo(String task, Status status, int userId) {
        this.task = task;
        this.status = status;
        this.userId = userId;
    }
}
