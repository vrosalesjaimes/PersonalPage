package com.vrj.mysite.exceptions;

public class TagFoundException extends Exception{
    public TagFoundException() {
        super("The tag name has already been used.");
    }
}
