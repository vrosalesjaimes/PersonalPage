package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.WorkExperienceNotFoundException;
import com.vrj.mysite.model.WorkExperience;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface WorkExperienceService {

    ResponseEntity<WorkExperience> createWorkExperience(WorkExperience workExperience);

    ResponseEntity<String> updateWorkExperience(Long id, WorkExperience workExperience) throws WorkExperienceNotFoundException;

    ResponseEntity<String> deleteWorkExperience(Long id);

    ResponseEntity<List<WorkExperience>> getAll();
}
