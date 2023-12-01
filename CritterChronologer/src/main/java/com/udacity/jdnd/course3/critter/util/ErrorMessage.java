package com.udacity.jdnd.course3.critter.util;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    PET_NOT_EXIST("Pet is not exist."),
    NO_ERROR(""),
    EMPLOYEE_NOT_EXIST("No Employee is available");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

}
