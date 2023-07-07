package com.vrj.mysite.exceptions;

public class ReferenceNotFoundException extends Exception{
    public ReferenceNotFoundException() {
        super("Reference not found.");
    }
}
