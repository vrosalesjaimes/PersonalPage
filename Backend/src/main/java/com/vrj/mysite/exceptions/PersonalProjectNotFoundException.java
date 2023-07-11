package com.vrj.mysite.exceptions;

public class PersonalProjectNotFoundException extends Exception {
    public PersonalProjectNotFoundException() {
        super("The project not found.");
    }
}
