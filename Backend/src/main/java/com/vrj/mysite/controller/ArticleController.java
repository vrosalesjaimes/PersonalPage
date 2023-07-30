package com.vrj.mysite.controller;

import com.vrj.mysite.dto.ArticleDTO;
import com.vrj.mysite.exceptions.ArticleFoundException;
import com.vrj.mysite.exceptions.ArticleNotFoundException;
import com.vrj.mysite.model.*;
import com.vrj.mysite.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/articles")
@CrossOrigin("*")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/create")
    public ResponseEntity<?> createArticle(@RequestBody Article article){
        try {
            return this.articleService.createArticle(article);
        } catch (ArticleFoundException e) {
            System.out.println("Article already exists.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Article already exists.");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateArticle(@PathVariable(value = "id") Long id,
                                                 @RequestBody Article article){
        try {
            return this.articleService.updateArticle(id, article);
        } catch (ArticleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article not found.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable(value = "id") Long id){
        return this.articleService.deleteArticle(id);
    }

    @PutMapping("/{id}/add-images")
    public ResponseEntity<String> addImages(@PathVariable(value = "id") Long id, 
                                            @RequestBody Set<Image> images){
        try {
            return this.articleService.addImages(id, images);
        } catch (ArticleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article not found.");
        }
    }

    @PutMapping("/{id}/add-authors")
    public ResponseEntity<String> addAuthors(@PathVariable(value = "id") Long id,
                                            @RequestBody Set<Author> authors){
        try {
            return this.articleService.addAuthors(id, authors);
        } catch (ArticleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article not found.");
        }
    }

    @PutMapping("/{id}/add-references")
    public ResponseEntity<String> addReferences(@PathVariable(value = "id") Long id,
                                            @RequestBody Set<Reference> references){
        try {
            return this.articleService.addReferences(id, references);
        } catch (ArticleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article not found.");
        }
    }

    @PutMapping("/{id}/add-tags")
    public ResponseEntity<String> addTags(@PathVariable(value = "id") Long id,
                                            @RequestBody Set<Tag> tags){
        try {
            return this.articleService.addTags(id, tags);
        } catch (ArticleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article not found.");
        }
    }

    @GetMapping("/card-view")
    public ResponseEntity<List<Article>> getAllForCards(){
        return this.articleService.getAllForCards();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getArticle(@PathVariable(value = "id") Long id){
        try {
            return this.articleService.getArticle(id);
        } catch (ArticleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article not found.");
        }
    }

    @GetMapping("/search/title")
    public ResponseEntity<Set<ArticleDTO>> searchByTitleAndIdiom(@RequestParam("title") String title){
        return this.articleService.searchByTitle(title);
    }

    @GetMapping("/search/authorName")
    private ResponseEntity<Set<ArticleDTO>> searchByAuthorNameAndIdiomId(@RequestParam("authorName") String authorName){
        return this.articleService.searchByNameAuthor(authorName);
    }

    @GetMapping("/search/tagName")
    private ResponseEntity<Set<ArticleDTO>> searchByTagNameAndIdiomId(@RequestParam("tagName") String tagName){
        return  this.articleService.searchByTagName(tagName);
    }
}
