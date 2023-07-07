package com.vrj.mysite.exceptions;

public class ScholarProjectFoundException extends Exception {
    public ScholarProjectFoundException() {
        super("The project name has already been used.");
    }
}