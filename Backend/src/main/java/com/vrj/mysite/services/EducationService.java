package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.EducationFoundException;
import com.vrj.mysite.exceptions.EducationNotFoundException;
import com.vrj.mysite.exceptions.IdiomFoundException;
import com.vrj.mysite.exceptions.ImageFoundException;
import com.vrj.mysite.model.Education;
import com.vrj.mysite.model.Idiom;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface EducationService {

    public ResponseEntity<Education> createEducation(Education education) throws EducationFoundException;
    public ResponseEntity<String> updateEducation(Long id, Education education) throws EducationNotFoundException;
    public ResponseEntity<String> deleteEducation(Long id);
    public ResponseEntity<Set<Education>> getAllByIdiom(Long id);

}
