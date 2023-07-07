package com.vrj.mysite.exceptions;

public class EducationFoundException extends Exception{
    public EducationFoundException() {
        super("Education already exists.");
    }
}
