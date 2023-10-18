package com.eurodyn.simplewebapp.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class ApiError {

    @Getter @Setter
    private List<String> errors;

    public ApiError(List<String> errors) {
        this.errors = errors;
    }

}
