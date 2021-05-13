package com.example.onepipeproject.exception;

import com.example.onepipeproject.model.Error;
import lombok.Data;

@Data
public class AbstractException extends RuntimeException {
    String code;
    Error error;

    public AbstractException(String code, String message) {
        super(message);
        this.code = code;
        this.error = new Error(code, message);
    }
}