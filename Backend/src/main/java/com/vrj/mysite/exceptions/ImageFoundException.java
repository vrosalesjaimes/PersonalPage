package com.vrj.mysite.exceptions;

public class ImageFoundException extends Exception{
    public ImageFoundException() {
        super("Image already exists.");
    }
}
