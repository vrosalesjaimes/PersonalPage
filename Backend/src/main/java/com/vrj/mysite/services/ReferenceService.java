package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.ReferenceFoundException;
import com.vrj.mysite.exceptions.ReferenceNotFoundException;
import com.vrj.mysite.model.Author;
import com.vrj.mysite.model.Reference;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface ReferenceService {

    ResponseEntity<Reference> createReference(Reference reference) throws ReferenceFoundException;

    ResponseEntity<String> updateReference(Long id, Reference reference) throws ReferenceNotFoundException;

    ResponseEntity<String> deleteReference(Long id);

    ResponseEntity<String> addAuthors(Long id, Set<Author> authors) throws ReferenceFoundException;
}
