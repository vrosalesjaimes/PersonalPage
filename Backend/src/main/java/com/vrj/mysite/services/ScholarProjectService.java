package com.vrj.mysite.services;

import com.vrj.mysite.dto.ScholarProjectDTO;
import com.vrj.mysite.exceptions.ScholarProjectNotFoundException;
import com.vrj.mysite.exceptions.ScholarProjectNotFoundException;
import com.vrj.mysite.exceptions.ScholarProjectFoundException;
import com.vrj.mysite.exceptions.ScholarProjectNotFoundException;
import com.vrj.mysite.model.*;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface ScholarProjectService {

    public ResponseEntity<ScholarProject> creteScholarProject(Long idiomId, ScholarProject scholarProject) throws ScholarProjectFoundException;
    public ResponseEntity<String> updateScholarProject(Long id, ScholarProject scholarProject) throws ScholarProjectNotFoundException;
    public ResponseEntity<String> deleteScholarProject(Long id);
    public ResponseEntity<ScholarProject> getById(Long id) throws ScholarProjectNotFoundException;
    public ResponseEntity<Set<ScholarProjectDTO>> getAllByIdiom(Long idiom_id);
    public ResponseEntity<Set<ScholarProjectDTO>> getByTitleAndIdiomId(String title, Long idiomId);
    public ResponseEntity<Set<ScholarProjectDTO>> getByTagNameAndIdiomId(String tagNAme, Long idiomId);
    public ResponseEntity<String> addImages(Long id, Set<Image> images) throws ScholarProjectNotFoundException;
    public ResponseEntity<String> addAuthors(Long id, Set<Author> authors) throws ScholarProjectNotFoundException;
    public ResponseEntity<String> addReferences(Long id, Set<Reference> references) throws ScholarProjectNotFoundException;
    public ResponseEntity<String> addTags(Long id, Set<Tag> tags) throws ScholarProjectNotFoundException;

}
