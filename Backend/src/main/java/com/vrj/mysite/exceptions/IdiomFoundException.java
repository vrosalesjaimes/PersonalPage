package com.vrj.mysite.exceptions;

public class IdiomFoundException extends Exception {
    public IdiomFoundException() {
        super("Idiom already exists.");
    }

    public static class PersonalProjectNotFoundException extends Exception {
        public PersonalProjectNotFoundException() {
            super("Project not found.");
        }
    }
}
