package com.ginny.mstodo.model;

import com.ginny.mstodo.entity.Status;
import lombok.Getter;
@Getter
public class TodoDTO {
    private String task;
    private int userId;
    private Status status;
}
