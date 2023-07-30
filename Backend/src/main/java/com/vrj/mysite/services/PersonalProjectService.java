package com.vrj.mysite.services;


import com.vrj.mysite.dto.PersonalProjectDTO;
import com.vrj.mysite.exceptions.PersonalProjectFoundException;
import com.vrj.mysite.exceptions.PersonalProjectNotFoundException;
import com.vrj.mysite.model.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface PersonalProjectService {

    ResponseEntity<PersonalProject> createPersonalProject(PersonalProject personalProject) throws PersonalProjectFoundException;

    ResponseEntity<String> updatePersonalProject(Long id, PersonalProject personalProject) throws PersonalProjectNotFoundException;

    ResponseEntity<String> deletePersonalProject(Long id);

    ResponseEntity<PersonalProject> getById(Long id) throws PersonalProjectNotFoundException;

    ResponseEntity<List<PersonalProject>> getAll();

    ResponseEntity<Set<PersonalProjectDTO>> getByTitle(String title);

    ResponseEntity<Set<PersonalProjectDTO>> getByTagName(String tagName);

    ResponseEntity<Set<PersonalProjectDTO>> getByAuthorName(String authorName);

    ResponseEntity<String> addImages(Long id, Set<Image> images) throws PersonalProjectNotFoundException;

    ResponseEntity<String> addAuthors(Long id, Set<Author> authors) throws PersonalProjectNotFoundException;

    ResponseEntity<String> addReferences(Long id, Set<Reference> references) throws PersonalProjectNotFoundException;

    ResponseEntity<String> addTags(Long id, Set<Tag> tags) throws PersonalProjectNotFoundException;

}
