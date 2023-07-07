package com.vrj.mysite.exceptions;

public class AuthorNotFoundException extends Exception{
    public AuthorNotFoundException() {
        super("Author not found.");
    }
}
