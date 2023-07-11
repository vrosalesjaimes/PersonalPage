package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.ScholarProjectNotFoundException;
import com.vrj.mysite.exceptions.ScholarProjectFoundException;
import com.vrj.mysite.exceptions.ScholarProjectNotFoundException;
import com.vrj.mysite.model.ScholarProject;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface ScholarProjectService {

    public ResponseEntity<ScholarProject> creteScholarProject(Long idiomId, ScholarProject scholarProject) throws ScholarProjectFoundException;
    public ResponseEntity<String> updateScholarProject(Long id, ScholarProject scholarProject) throws ScholarProjectNotFoundException;
    public ResponseEntity<String> deleteScholarProject(Long id);
    public ResponseEntity<ScholarProject> getById(Long id) throws ScholarProjectNotFoundException;
    public ResponseEntity<Set<ScholarProject>> getAllByIdiom(Long idiom_id);
    public ResponseEntity<Set<ScholarProject>> getByTitleAndIdiomId(String title, Long idiomId);
    public ResponseEntity<Set<ScholarProject>> getByTagNameAndIdiomId(String tagNAme, Long idiomId);

}
