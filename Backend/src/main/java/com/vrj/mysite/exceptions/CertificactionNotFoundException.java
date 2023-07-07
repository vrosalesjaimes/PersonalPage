package com.vrj.mysite.exceptions;

public class CertificactionNotFoundException extends Exception{
    public CertificactionNotFoundException() {
        super("Certification not found.");
    }
}
