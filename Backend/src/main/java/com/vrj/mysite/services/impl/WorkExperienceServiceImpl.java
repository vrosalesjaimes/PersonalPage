package com.vrj.mysite.services.impl;

import com.vrj.mysite.exceptions.WorkExperienceNotFoundException;
import com.vrj.mysite.model.Idiom;
import com.vrj.mysite.model.WorkExperience;
import com.vrj.mysite.repositories.WorkExperienceRepository;
import com.vrj.mysite.services.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {

    @Autowired
    private WorkExperienceRepository workExperienceRepository;

    @Override
    public ResponseEntity<WorkExperience> createWorkExperience(WorkExperience workExperience) {

        workExperience = this.workExperienceRepository.save(workExperience);

        return ResponseEntity.ok(workExperience);
    }

    @Override
    public ResponseEntity<String> updateWorkExperience(Long id, WorkExperience workExperience) throws WorkExperienceNotFoundException {
        Optional<WorkExperience> localExperience = this.workExperienceRepository.findById(id);

        if (localExperience.isEmpty())
            throw new WorkExperienceNotFoundException();

        localExperience.get().update(workExperience);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Experience has been update.");
    }

    @Override
    public ResponseEntity<String> deleteWorkExperience(Long id) {
        if (this.workExperienceRepository.existsById(id)) {
            this.workExperienceRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Experience successfully removed");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Experience not found");
    }

    @Override
    public ResponseEntity<List<WorkExperience>> getAll() {
        return ResponseEntity.ok(this.workExperienceRepository.findAll());
    }
}
