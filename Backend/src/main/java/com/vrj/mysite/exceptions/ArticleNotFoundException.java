package com.vrj.mysite.exceptions;

public class ArticleNotFoundException extends Exception {

    public ArticleNotFoundException() {
        super("Article not found");
    }
}
