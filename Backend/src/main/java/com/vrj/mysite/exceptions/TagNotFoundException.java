package com.vrj.mysite.exceptions;

public class TagNotFoundException extends Exception{
    public TagNotFoundException() {
        super("Tag no found.");
    }
}
