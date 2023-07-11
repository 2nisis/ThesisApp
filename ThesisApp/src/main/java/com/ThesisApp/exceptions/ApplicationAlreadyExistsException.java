package com.ThesisApp.exceptions;

public class ApplicationAlreadyExistsException extends RuntimeException{

    public ApplicationAlreadyExistsException(String message) {
        super(message);
    }
}
