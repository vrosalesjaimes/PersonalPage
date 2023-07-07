package com.vrj.mysite.exceptions;

public class PersonalProjectFoundException extends Exception {
    public PersonalProjectFoundException() {
        super("The project name has already been used.");
    }
}
