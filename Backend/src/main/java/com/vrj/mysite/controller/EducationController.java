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
@RequestMapping("/education")
@CrossOrigin("*")
public class EducationController {

    @Autowired
    private EducationService educationService;

    @PostMapping("/create")
    public ResponseEntity<?> createEducation(@RequestBody Education education) {
        try {
            return this.educationService.createEducation(education);
        } catch (EducationFoundException e) {
            System.out.println("Education already exists.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Education already exists.");
        }
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<String> updateEducation(@PathVariable(value = "id") Long id,
                                                   @RequestBody Education education) {
        try {
            return this.educationService.updateEducation(id, education);
        } catch (EducationNotFoundException e) {
            System.out.println("Image already exist.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Image already exist.");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteEducation(@PathVariable(value = "id") Long id) {
        return this.educationService.deleteEducation(id);
    }

    @GetMapping("/get-all-by-idiom/{id}")
    public ResponseEntity<Set<Education>> getAllByIdiom(@PathVariable(value = "id") Long id) {
        return this.educationService.getAllByIdiom(id);

    }
}
