package com.epam.jmp.api;


public class ResourceException extends Exception {

    public ResourceException(String message) {
        super(message);
    }

    public ResourceException(Throwable cause) {
        super(cause);
    }
}
