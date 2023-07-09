package com.vrj.mysite.controller;

import com.vrj.mysite.exceptions.AuthorFoundException;
import com.vrj.mysite.exceptions.AuthorNotFoundException;
import com.vrj.mysite.model.Author;
import com.vrj.mysite.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
@CrossOrigin("*")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/create")
    public ResponseEntity<?> createAuthor(@RequestBody Author author){
        try {
            return this.authorService.createAuthor(author);
        } catch (AuthorFoundException e) {
            System.out.println("Author already exist.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Author already exist.");
        }
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<?> updateAuthor(@PathVariable(value = "id") Long id,
                                           @RequestBody Author author){
        try {
            return this.authorService.updateAuthor(id, author);
        } catch (AuthorNotFoundException e) {
            System.out.println("Author not found.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Author not found.");
        }
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> deleteAuthor(@PathVariable(value = "id") Long id){
        return this.authorService.delete(id);
    }
}
