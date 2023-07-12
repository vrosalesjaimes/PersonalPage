package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.CertificationFoundException;
import com.vrj.mysite.exceptions.CertificationNotFoundException;
import com.vrj.mysite.model.Certification;
import com.vrj.mysite.model.Tag;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface CertificationService {

    public ResponseEntity<Certification> createCertification(Long id, Certification certification) throws CertificationFoundException;
    public ResponseEntity<String> updateCertification(Long id, Certification certification) throws CertificationNotFoundException;
    public ResponseEntity<String> delete(Long id);
    public ResponseEntity<Set<Certification>> getAllByIdiomId(Long id);
    public ResponseEntity<Set<Certification>> searchByTitle(String title, Long idiomId);
    public ResponseEntity<Set<Certification>> searchByTagAndIdiom(String tagName, Long idiomId);
    public ResponseEntity<String> addTags(Long id, Set<Tag> tags) throws CertificationNotFoundException;
}
