package com.vrj.mysite.controller;

import com.vrj.mysite.exceptions.EducationFoundException;
import com.vrj.mysite.exceptions.EducationNotFoundException;
import com.vrj.mysite.model.Education;
import com.vrj.mysite.services.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/educations")
@CrossOrigin("*")
public class EducationController {

    @Autowired
    private EducationService educationService;

    @PostMapping("/create")
    public ResponseEntity<?> createEducation(@RequestBody Education education) {
        try {
            return educationService.createEducation(education);
        } catch (EducationFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Education already exists.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEducation(@PathVariable("id") Long id, @RequestBody Education education) {
        try {
            return educationService.updateEducation(id, education);
        } catch (EducationNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Education not found.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEducation(@PathVariable("id") Long id) {
        return educationService.deleteEducation(id);
    }

    @GetMapping("/idiom/{id}")
    public ResponseEntity<Set<Education>> getAllByLanguage(@PathVariable("id") Long id) {
        return educationService.getAllByIdiom(id);
    }
}
