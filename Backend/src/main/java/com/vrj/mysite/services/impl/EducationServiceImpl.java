package com.vrj.mysite.services.impl;

import com.vrj.mysite.exceptions.EducationFoundException;
import com.vrj.mysite.exceptions.EducationNotFoundException;
import com.vrj.mysite.model.Education;
import com.vrj.mysite.model.Idiom;
import com.vrj.mysite.repositories.EducationRepository;
import com.vrj.mysite.repositories.IdiomRepository;
import com.vrj.mysite.repositories.ImageRepository;
import com.vrj.mysite.services.EducationService;
import com.vrj.mysite.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private IdiomRepository idiomRepository;
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public ResponseEntity<Education> createEducation(Education education) throws EducationFoundException{
        Optional<Education> localEducation = this.educationRepository.findByTitle(education.getTitle());

        if (localEducation.isPresent())
            throw new EducationFoundException();

        Optional<Image> image = this.imageRepository.findByUrl(education.getImage().getUrl());
        if(image.isPresent())
            education.setImage(image.get());
        else
            education.setImage(this.imageRepository.save(education.getImage()));

        Optional<Idiom> idiom = this.idiomRepository.findByIdiom(education.getIdiom().getIdiom());
        if (idiom.isPresent())
            education.setIdiom(education.getIdiom());
        else
            education.setIdiom(this.idiomRepository.save(education.getIdiom()));

        education = this.educationRepository.save(education);

        return ResponseEntity.ok(education);
    }

    @Override
    public ResponseEntity<String> updateEducation(Long id, Education education) throws EducationNotFoundException {
        Optional<Education> localEducation = this.educationRepository.findById(id);

        if(localEducation.isEmpty())
            throw new EducationNotFoundException();

        Education savedEducation = localEducation.get().update(education);

        return ResponseEntity.status(HttpStatus.CONFLICT).body("The Education has been successfully updated");
    }

    @Override
    public ResponseEntity<String> deleteEducation(Long id) {
        if(this.educationRepository.existsById(id)){
            this.educationRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Eduaction successfully removed");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Education not found");
    }

    @Override
    public ResponseEntity<Set<Education>> getAllByIdiom(Long id) {
        return ResponseEntity.ok(this.educationRepository.findAllByIdiom_id(id));
    }
}
