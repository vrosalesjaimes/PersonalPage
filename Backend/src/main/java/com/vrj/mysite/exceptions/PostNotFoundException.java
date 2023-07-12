package com.vrj.mysite.exceptions;

public class PostNotFoundException extends Exception {
    public PostNotFoundException() {
        super("Post not found");
    }
}
