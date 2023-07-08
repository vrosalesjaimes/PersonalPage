package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.AuthorFoundException;
import com.vrj.mysite.exceptions.AuthorNotFoundException;
import com.vrj.mysite.model.Author;
import org.springframework.http.ResponseEntity;

public interface AuthorService {

    public ResponseEntity<Author> createAuthor(Author author) throws AuthorFoundException;
    public ResponseEntity<String> updateAuthor(Long id, Author author) throws AuthorNotFoundException;
    public void delete(Long id);
}
