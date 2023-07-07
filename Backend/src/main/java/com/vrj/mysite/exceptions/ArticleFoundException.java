package com.vrj.mysite.exceptions;

public class ArticleFoundException extends Exception{

    public ArticleFoundException() {
        super("Article already exists.");
    }
}
