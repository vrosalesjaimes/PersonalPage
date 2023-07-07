package com.vrj.mysite.exceptions;

public class EducationNotFoundException extends Exception {
    public EducationNotFoundException() {
        super("Education not found.");
    }
}
