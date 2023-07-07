package com.vrj.mysite.exceptions;

public class CertificationNotFoundException extends Exception{
    public CertificationNotFoundException() {
        super("Certification not found.");
    }
}
