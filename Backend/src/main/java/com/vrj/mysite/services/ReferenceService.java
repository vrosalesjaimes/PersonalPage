package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.ReferenceFoundException;
import com.vrj.mysite.exceptions.ReferenceNotFoundException;
import com.vrj.mysite.model.Reference;
import org.springframework.http.ResponseEntity;

public interface ReferenceService {

    public ResponseEntity<Reference> createReference(Reference reference) throws ReferenceFoundException;
    public ResponseEntity<Reference> updateReference(Long id, Reference reference) throws ReferenceNotFoundException;
    public void deleteReference(Long id);
}