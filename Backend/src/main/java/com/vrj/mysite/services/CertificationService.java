package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.CertificationFoundException;
import com.vrj.mysite.exceptions.CertificationNotFoundException;
import com.vrj.mysite.model.Certification;
import org.springframework.http.ResponseEntity;

public interface CertificationService {

    public ResponseEntity<Certification> createCertification(Certification certification) throws CertificationFoundException;
    public ResponseEntity<Certification> updateCertification(Long id, Certification certification) throws CertificationNotFoundException;
    public void delete(Long id);
}