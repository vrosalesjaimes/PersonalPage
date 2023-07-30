package com.vrj.mysite.controller;

import com.vrj.mysite.exceptions.WorkExperienceNotFoundException;
import com.vrj.mysite.model.WorkExperience;
import com.vrj.mysite.services.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/work-experiences")
@CrossOrigin("*")
public class WorkExperienceController {
    @Autowired
    private WorkExperienceService workExperienceService;

    @PostMapping("/create")
    public ResponseEntity<WorkExperience> createWorkExperience(@RequestBody WorkExperience workExperience) {
        return workExperienceService.createWorkExperience(workExperience);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateWorkExperience(@PathVariable("id") Long id,
                                                       @RequestBody WorkExperience workExperience) {
        try {
            return workExperienceService.updateWorkExperience(id, workExperience);
        } catch (WorkExperienceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWorkExperience(@PathVariable("id") Long id) {
        return workExperienceService.deleteWorkExperience(id);
    }

    @GetMapping("/")
    public ResponseEntity<List<WorkExperience>> getAll() {
        return workExperienceService.getAll();
    }
}
