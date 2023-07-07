package com.vrj.mysite.exceptions;

public class AboutNotFoundException extends Exception {

    public AboutNotFoundException() {
        super("About section not found.");
    }
}
