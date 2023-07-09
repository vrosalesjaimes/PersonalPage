package com.vrj.mysite.services.impl;

import com.vrj.mysite.exceptions.AuthorFoundException;
import com.vrj.mysite.exceptions.AuthorNotFoundException;
import com.vrj.mysite.model.Author;
import com.vrj.mysite.repositories.AuthorRepository;
import com.vrj.mysite.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public ResponseEntity<Author> createAuthor(Author author) throws AuthorFoundException {
        Optional<Author> localAuthor = this.authorRepository.findByName(author.getName());

        if(localAuthor.isPresent())
            throw new AuthorFoundException();

        author = this.authorRepository.save(author);

        return ResponseEntity.ok(author);
    }

    @Override
    public ResponseEntity<String> updateAuthor(Long id, Author author) throws AuthorNotFoundException {
        Optional<Author> localAuthor = this.authorRepository.findById(id);

        if(localAuthor.isEmpty())
            throw new AuthorNotFoundException();

        Author updatedAuthor = localAuthor.get().update(author);
        this.authorRepository.save(updatedAuthor);

        return ResponseEntity.status(HttpStatus.OK).body("The author has been successfully updated");
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        if(this.authorRepository.existsById(id)){
            authorRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Author successfully removed");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");

    }
}
