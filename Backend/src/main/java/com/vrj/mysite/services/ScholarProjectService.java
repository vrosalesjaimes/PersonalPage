package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.ScholarProjectFoundException;
import com.vrj.mysite.exceptions.ScholarProjectNotFoundException;
import com.vrj.mysite.model.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface ScholarProjectService {

    ResponseEntity<ScholarProject> creteScholarProject(ScholarProject scholarProject) throws ScholarProjectFoundException;

    ResponseEntity<String> updateScholarProject(Long id, ScholarProject scholarProject) throws ScholarProjectNotFoundException;

    ResponseEntity<String> deleteScholarProject(Long id);

    ResponseEntity<ScholarProject> getById(Long id) throws ScholarProjectNotFoundException;

    ResponseEntity<List<ScholarProject>> getAll();

    ResponseEntity<Set<ScholarProject>> getByTitle(String title);

    ResponseEntity<Set<ScholarProject>> getByTagName(String tagNAme);

    ResponseEntity<String> addImages(Long id, Set<Image> images) throws ScholarProjectNotFoundException;

    ResponseEntity<String> addAuthors(Long id, Set<Author> authors) throws ScholarProjectNotFoundException;

    ResponseEntity<String> addReferences(Long id, Set<Reference> references) throws ScholarProjectNotFoundException;

    ResponseEntity<String> addTags(Long id, Set<Tag> tags) throws ScholarProjectNotFoundException;

}
