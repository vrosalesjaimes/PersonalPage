package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.PersonalProjectFoundException;
import com.vrj.mysite.exceptions.PersonalProjectNotFoundException;
import com.vrj.mysite.model.PersonalProject;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface PersonalProjectService {

    public ResponseEntity<PersonalProject> createPersonalProject(Long idiomId,PersonalProject personalProject) throws PersonalProjectFoundException;
    public ResponseEntity<String> updatePersonalProject(Long id, PersonalProject personalProject) throws PersonalProjectNotFoundException;
    public ResponseEntity<String> deletePersonalProject(Long id);
    public ResponseEntity<PersonalProject> getById(Long id) throws PersonalProjectNotFoundException;
    public ResponseEntity<Set<PersonalProject>> getAllByIdiom(Long idiom_id);
    public ResponseEntity<Set<PersonalProject>> getByTitleAndIdiomId(String title, Long idiomId);
    public ResponseEntity<Set<PersonalProject>> getByTagNameAndIdiomId(String tagNAme, Long idiomId);
    public ResponseEntity<Set<PersonalProject>> getByAuthorNameAndIdiomId(String authorName, Long idiomId);

}
