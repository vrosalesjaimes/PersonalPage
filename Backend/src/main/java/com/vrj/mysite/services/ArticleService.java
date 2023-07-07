package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.ArticleFoundException;
import com.vrj.mysite.exceptions.ArticleNotFoundException;
import com.vrj.mysite.model.Article;
import org.springframework.http.ResponseEntity;

public interface ArticleService {

    public ResponseEntity<Article> createArticle(Article article) throws ArticleFoundException;
    public ResponseEntity<Article> updateArticle(Long id, Article article) throws ArticleNotFoundException;
    public void deleteArticle(Long id);

}
