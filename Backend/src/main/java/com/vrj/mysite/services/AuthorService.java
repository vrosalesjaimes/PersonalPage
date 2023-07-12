package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.AuthorFoundException;
import com.vrj.mysite.exceptions.AuthorNotFoundException;
import com.vrj.mysite.model.Author;
import org.springframework.http.ResponseEntity;

public interface AuthorService {

    ResponseEntity<Author> createAuthor(Author author) throws AuthorFoundException;

    ResponseEntity<String> updateAuthor(Long id, Author author) throws AuthorNotFoundException;

    ResponseEntity<String> delete(Long id);
}
