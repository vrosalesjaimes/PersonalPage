package com.vrj.mysite.services;


import com.vrj.mysite.dto.PersonalProjectDTO;
import com.vrj.mysite.exceptions.PersonalProjectFoundException;
import com.vrj.mysite.exceptions.PersonalProjectNotFoundException;
import com.vrj.mysite.model.*;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface PersonalProjectService {

    public ResponseEntity<PersonalProject> createPersonalProject(Long idiomId,PersonalProject personalProject) throws PersonalProjectFoundException;
    public ResponseEntity<String> updatePersonalProject(Long id, PersonalProject personalProject) throws PersonalProjectNotFoundException;
    public ResponseEntity<String> deletePersonalProject(Long id);
    public ResponseEntity<PersonalProject> getById(Long id) throws PersonalProjectNotFoundException;
    public ResponseEntity<Set<PersonalProjectDTO>> getAllByIdiom(Long idiom_id);
    public ResponseEntity<Set<PersonalProjectDTO>> getByTitleAndIdiomId(String title, Long idiomId);
    public ResponseEntity<Set<PersonalProjectDTO>> getByTagNameAndIdiomId(String tagNAme, Long idiomId);
    public ResponseEntity<Set<PersonalProjectDTO>> getByAuthorNameAndIdiomId(String authorName, Long idiomId);
    public ResponseEntity<String> addImages(Long id, Set<Image> images) throws PersonalProjectNotFoundException;
    public ResponseEntity<String> addAuthors(Long id, Set<Author> authors) throws PersonalProjectNotFoundException;
    public ResponseEntity<String> addReferences(Long id, Set<Reference> references) throws PersonalProjectNotFoundException;
    public ResponseEntity<String> addTags(Long id, Set<Tag> tags) throws PersonalProjectNotFoundException;

}
