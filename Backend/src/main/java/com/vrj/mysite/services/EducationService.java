package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.EducationFoundException;
import com.vrj.mysite.exceptions.EducationNotFoundException;
import com.vrj.mysite.model.Education;
import org.springframework.http.ResponseEntity;

public interface EducationService {

    public ResponseEntity<Education> createEducation(Education education) throws EducationFoundException;
    public ResponseEntity<Education> updateEducation(Long id, Education education) throws EducationNotFoundException;
    public void deleteEducation(Long id);

}
