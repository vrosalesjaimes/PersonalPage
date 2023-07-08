package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.WorkExperienceNotFoundException;
import com.vrj.mysite.model.WorkExperience;
import org.springframework.http.ResponseEntity;

public interface WorkExperienceService {

    public ResponseEntity<?> createWorkExperience(WorkExperience workExperience);
    public ResponseEntity<String> updateWorkExperience(Long id, WorkExperience workExperience) throws WorkExperienceNotFoundException;
    public void deleteWorkExperience(Long id);
}
