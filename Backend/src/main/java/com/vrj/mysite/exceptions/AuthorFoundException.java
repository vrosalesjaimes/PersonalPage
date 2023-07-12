package com.vrj.mysite.exceptions;

public class AuthorFoundException extends Exception {
    public AuthorFoundException() {
        super("Author already exists.");
    }
}
