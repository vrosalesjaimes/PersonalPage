package com.vrj.mysite.exceptions;

public class IdiomNotFoundException extends Exception {
    public IdiomNotFoundException() {
        super("Idiom not found.");
    }
}
