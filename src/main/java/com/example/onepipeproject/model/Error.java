package com.example.onepipeproject.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Error {
    private String code;
    private String message;

    public Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
