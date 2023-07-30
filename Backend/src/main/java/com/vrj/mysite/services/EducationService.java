package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.EducationFoundException;
import com.vrj.mysite.exceptions.EducationNotFoundException;
import com.vrj.mysite.model.Education;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface EducationService {

    ResponseEntity<Education> createEducation(Education education) throws EducationFoundException;

    ResponseEntity<String> updateEducation(Long id, Education education) throws EducationNotFoundException;

    ResponseEntity<String> deleteEducation(Long id);

    ResponseEntity<List<Education>> getAll();

}
