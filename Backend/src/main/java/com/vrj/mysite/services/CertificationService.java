package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.CertificationFoundException;
import com.vrj.mysite.exceptions.CertificationNotFoundException;
import com.vrj.mysite.model.Certification;
import com.vrj.mysite.model.Tag;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface CertificationService {

    ResponseEntity<Certification> createCertification(Long id, Certification certification) throws CertificationFoundException;

    ResponseEntity<String> updateCertification(Long id, Certification certification) throws CertificationNotFoundException;

    ResponseEntity<String> delete(Long id);

    ResponseEntity<Set<Certification>> getAllByIdiomId(Long id);

    ResponseEntity<Set<Certification>> searchByTitle(String title, Long idiomId);

    ResponseEntity<Set<Certification>> searchByTagAndIdiom(String tagName, Long idiomId);

    ResponseEntity<String> addTags(Long id, Set<Tag> tags) throws CertificationNotFoundException;
}
