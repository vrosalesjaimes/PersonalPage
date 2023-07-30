package com.vrj.mysite.services.impl;

import com.vrj.mysite.exceptions.CertificationFoundException;
import com.vrj.mysite.exceptions.CertificationNotFoundException;
import com.vrj.mysite.exceptions.TagFoundException;
import com.vrj.mysite.model.Certification;
import com.vrj.mysite.model.Idiom;
import com.vrj.mysite.model.Tag;
import com.vrj.mysite.repositories.CertificationRepository;
import com.vrj.mysite.repositories.TagRepository;
import com.vrj.mysite.services.CertificationService;
import com.vrj.mysite.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CertificationServiceImpl implements CertificationService {

    @Autowired
    private CertificationRepository certificationRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TagService tagService;

    @Override
    public ResponseEntity<Certification> createCertification(Certification certification) throws CertificationFoundException {

        Optional<Certification> localCertification =
                this.certificationRepository.findByTitle(certification.getTitle());

        if (localCertification.isPresent())
            throw new CertificationFoundException();

        Set<Tag> savedTag = new HashSet<>();
        for (Tag tag : certification.getTags()) {
            try {
                savedTag.add(this.tagService.createTag(tag).getBody());
            } catch (TagFoundException e) {
                savedTag.add(this.tagRepository.findByName((tag.getName())).get());
            }
        }
        certification.setTags(savedTag);

        certification = this.certificationRepository.save(certification);
        return ResponseEntity.ok(certification);
    }

    @Override
    public ResponseEntity<String> updateCertification(Long id, Certification certification) throws CertificationNotFoundException {
        Optional<Certification> localCertification =
                this.certificationRepository.findByTitle(certification.getTitle());

        if (localCertification.isEmpty())
            throw new CertificationNotFoundException();

        certification = localCertification.get().update(certification);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The certification has been update.");
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        if (this.certificationRepository.existsById(id)) {
            this.certificationRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Certification successfully removed.");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Certification not found.");
    }

    @Override
    public ResponseEntity<List<Certification>> getAll() {
        return ResponseEntity.ok(this.certificationRepository.findAll());
    }

    @Override
    public ResponseEntity<Set<Certification>> searchByTitle(String title) {
        return ResponseEntity.ok(this.certificationRepository
                .findAllByTitleContainingIgnoreCase(title));
    }

    @Override
    public ResponseEntity<Set<Certification>> searchByTag(String tagName) {
        return ResponseEntity.ok(this.certificationRepository
                .findAllByTags_NameContainingIgnoreCase(tagName));
    }

    @Override
    public ResponseEntity<String> addTags(Long id, Set<Tag> tags) throws CertificationNotFoundException {
        Optional<Certification> localProject = this.certificationRepository.findById(id);

        if (localProject.isEmpty())
            throw new CertificationNotFoundException();

        Set<Tag> savedTag = new HashSet<>();
        for (Tag tag : tags) {
            try {
                savedTag.add(this.tagService.createTag(tag).getBody());
            } catch (TagFoundException e) {
                savedTag.add(this.tagRepository.findByName((tag.getName())).get());
            }
        }
        localProject.get().addTags(savedTag);
        this.certificationRepository.save(localProject.get());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Images have been successfully added");
    }
}
