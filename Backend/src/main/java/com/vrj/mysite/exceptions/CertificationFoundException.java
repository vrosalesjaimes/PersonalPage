package com.vrj.mysite.exceptions;

public class CertificationFoundException extends Exception{
    public CertificationFoundException() {
        super("Certification already exists.");
    }
}
