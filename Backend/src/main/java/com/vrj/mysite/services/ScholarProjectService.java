package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.ScholarProjectFoundException;
import com.vrj.mysite.exceptions.ScholarProjectNotFoundException;
import com.vrj.mysite.model.ScholarProject;
import org.springframework.http.ResponseEntity;

public interface ScholarProjectService {

    public ResponseEntity<ScholarProject> creteScholarProject(ScholarProject scholarProject) throws ScholarProjectFoundException;
    public ResponseEntity<ScholarProject> updateScholarProject(Long id, ScholarProject scholarProject) throws ScholarProjectNotFoundException;
    public void deleteScholarProject(Long id);
}
