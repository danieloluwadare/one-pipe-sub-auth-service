package com.example.onepipeproject.exception;

/**
 * @author Abiola.Adebanjo
 */
public class OnePipeOPerationNotAllowedException extends AbstractException {

    public OnePipeOPerationNotAllowedException(String message) {
        this("D01", message);
    }

    public OnePipeOPerationNotAllowedException(String code, String message) {
        super(code, message);
    }
}