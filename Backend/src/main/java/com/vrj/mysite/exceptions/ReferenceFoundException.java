package com.vrj.mysite.exceptions;

public class ReferenceFoundException extends Exception {
    public ReferenceFoundException() {
        super("The reference name has already been used.");
    }
}
