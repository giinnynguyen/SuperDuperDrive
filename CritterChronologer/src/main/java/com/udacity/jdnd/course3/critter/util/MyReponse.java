package com.udacity.jdnd.course3.critter.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class MyReponse<T> implements Serializable {
    private String message;
    private T data;

    public MyReponse(T data) {
        this.data = data;
        this.message = "";
    }
}
