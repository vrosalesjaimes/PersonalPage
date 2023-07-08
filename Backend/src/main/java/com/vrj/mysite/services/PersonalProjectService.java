package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.IdiomFoundException;
import com.vrj.mysite.exceptions.PersonalProjectFoundException;
import com.vrj.mysite.model.PersonalProject;
import org.springframework.http.ResponseEntity;

public interface PersonalProjectService {

    public ResponseEntity<PersonalProject> createPersonalProject(PersonalProject personalProject) throws PersonalProjectFoundException;
    public ResponseEntity<String> updatePersonalProject(Long id, PersonalProject personalProject) throws IdiomFoundException.PersonalProjectNotFoundException;
    public void deletePersonalProject(Long id);
}
