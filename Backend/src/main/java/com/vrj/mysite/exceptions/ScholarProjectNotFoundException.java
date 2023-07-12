package com.vrj.mysite.exceptions;

public class ScholarProjectNotFoundException extends Exception {
    public ScholarProjectNotFoundException() {
        super("Project not found.");
    }
}
