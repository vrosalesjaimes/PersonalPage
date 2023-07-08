package com.vrj.mysite.exceptions;

public class ImageNotFoundException extends Exception{
    public ImageNotFoundException() {
        super("Image not found.");
    }
}
