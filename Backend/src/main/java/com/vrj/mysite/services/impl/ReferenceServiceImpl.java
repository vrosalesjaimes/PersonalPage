package com.vrj.mysite.services.impl;

import com.vrj.mysite.exceptions.AuthorFoundException;
import com.vrj.mysite.exceptions.ReferenceFoundException;
import com.vrj.mysite.exceptions.ReferenceNotFoundException;
import com.vrj.mysite.model.Author;
import com.vrj.mysite.model.Reference;
import com.vrj.mysite.repositories.AuthorRepository;
import com.vrj.mysite.repositories.ReferenceRepository;
import com.vrj.mysite.services.AuthorService;
import com.vrj.mysite.services.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ReferenceServiceImpl implements ReferenceService {

    @Autowired
    private ReferenceRepository referenceRepository;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public ResponseEntity<Reference> createReference(Reference reference) throws ReferenceFoundException {
        Optional<Reference> localReference = this.referenceRepository.findByName(reference.getName());

        if (localReference.isPresent())
            throw new ReferenceFoundException();

        Set<Author> savedAuthors = new HashSet<>();

        for (Author author : reference.getAuthors()) {
            try {
                savedAuthors.add(this.authorService.createAuthor(author).getBody());
            } catch (AuthorFoundException e) {
                savedAuthors.add(this.authorRepository.findByName(author.getName()).get());
            }
        }

        reference.setAuthors(savedAuthors);
        reference = this.referenceRepository.save(reference);

        return ResponseEntity.ok(reference);
    }

    @Override
    public ResponseEntity<String> updateReference(Long id, Reference reference) throws ReferenceNotFoundException {
        Optional<Reference> localReference = this.referenceRepository.findById(id);

        if (localReference.isEmpty())
            throw new ReferenceNotFoundException();

        Reference savedReference = localReference.get().update(reference);

        this.referenceRepository.save(savedReference);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("The references has been update.");
    }

    @Override
    public ResponseEntity<String> deleteReference(Long id) {
        if (this.referenceRepository.existsById(id)) {
            this.referenceRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Reference successfully removed.");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reference not found.");
    }

    @Override
    public ResponseEntity<String> addAuthors(Long id, Set<Author> authors) throws ReferenceFoundException {
        Optional<Reference> localReference = this.referenceRepository.findById(id);

        if (localReference.isEmpty())
            throw new ReferenceFoundException();

        Set<Author> savedAuthors = new HashSet<>();
        for (Author author : authors) {
            try {
                savedAuthors.add(this.authorService.createAuthor(author).getBody());
            } catch (AuthorFoundException e) {
                savedAuthors.add(this.authorRepository.findByName(author.getName()).get());
            }
        }

        localReference.get().addAuthors(savedAuthors);
        Reference savedReference = this.referenceRepository.save(localReference.get());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Authors have been successfully added");

    }
}
