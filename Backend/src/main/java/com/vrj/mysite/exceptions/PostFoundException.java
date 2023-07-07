package com.vrj.mysite.exceptions;

public class PostFoundException extends Exception {
    public PostFoundException() {
        super("The post title has already been used.");
    }
}
