package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.WorkExperienceNotFoundException;
import com.vrj.mysite.model.WorkExperience;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface WorkExperienceService {

    public ResponseEntity<WorkExperience> createWorkExperience(WorkExperience workExperience);
    public ResponseEntity<String> updateWorkExperience(Long id, WorkExperience workExperience) throws WorkExperienceNotFoundException;
    public ResponseEntity<String> deleteWorkExperience(Long id);
    public ResponseEntity<Set<WorkExperience>> getAllByIdiomId(Long idiomId);
}
