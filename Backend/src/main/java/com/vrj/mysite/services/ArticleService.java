package com.vrj.mysite.services;

import com.vrj.mysite.dto.ArticleDTO;
import com.vrj.mysite.exceptions.ArticleFoundException;
import com.vrj.mysite.exceptions.ArticleNotFoundException;
import com.vrj.mysite.model.*;
import org.springframework.http.ResponseEntity;

import java.util.Set;


public interface ArticleService {

    public ResponseEntity<Article> createArticle(Article article) throws ArticleFoundException;
    public ResponseEntity<String> updateArticle(Long id, Article article) throws ArticleNotFoundException;
    public ResponseEntity<String> deleteArticle(Long id);
    public ResponseEntity<String> addImages(Long id, Set<Image> images) throws ArticleNotFoundException;
    public ResponseEntity<String> addAuthors(Long id, Set<Author> authors) throws ArticleNotFoundException;
    public ResponseEntity<String> addReferences(Long id, Set<Reference> references) throws ArticleNotFoundException;
    public ResponseEntity<String> addTags(Long id, Set<Tag> tags) throws ArticleNotFoundException;
    public ResponseEntity<Set<ArticleDTO>> getAllForCards(Long idiomId);
    public ResponseEntity<Article> getArticle(Long id) throws ArticleNotFoundException;
    public ResponseEntity<Set<ArticleDTO>> searchByTitleAndIdiom(String title, Long idiomId);
    public ResponseEntity<Set<ArticleDTO>> searchByNameAuthorAndIdIdiom(String authorName, Long idIdiom);
    public ResponseEntity<Set<ArticleDTO>> searchByTagNameAndIdiomId(String tagName, Long id);

}
