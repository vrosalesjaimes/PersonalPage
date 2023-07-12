package com.vrj.mysite.services;

import com.vrj.mysite.dto.ArticleDTO;
import com.vrj.mysite.exceptions.ArticleFoundException;
import com.vrj.mysite.exceptions.ArticleNotFoundException;
import com.vrj.mysite.model.*;
import org.springframework.http.ResponseEntity;

import java.util.Set;


public interface ArticleService {

    ResponseEntity<Article> createArticle(Article article) throws ArticleFoundException;

    ResponseEntity<String> updateArticle(Long id, Article article) throws ArticleNotFoundException;

    ResponseEntity<String> deleteArticle(Long id);

    ResponseEntity<String> addImages(Long id, Set<Image> images) throws ArticleNotFoundException;

    ResponseEntity<String> addAuthors(Long id, Set<Author> authors) throws ArticleNotFoundException;

    ResponseEntity<String> addReferences(Long id, Set<Reference> references) throws ArticleNotFoundException;

    ResponseEntity<String> addTags(Long id, Set<Tag> tags) throws ArticleNotFoundException;

    ResponseEntity<Set<ArticleDTO>> getAllForCards(Long idiomId);

    ResponseEntity<Article> getArticle(Long id) throws ArticleNotFoundException;

    ResponseEntity<Set<ArticleDTO>> searchByTitleAndIdiom(String title, Long idiomId);

    ResponseEntity<Set<ArticleDTO>> searchByNameAuthorAndIdIdiom(String authorName, Long idIdiom);

    ResponseEntity<Set<ArticleDTO>> searchByTagNameAndIdiomId(String tagName, Long id);

}
