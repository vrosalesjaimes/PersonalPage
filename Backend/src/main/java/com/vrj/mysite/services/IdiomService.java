package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.IdiomFoundException;
import com.vrj.mysite.exceptions.IdiomNotFoundException;
import com.vrj.mysite.model.Idiom;
import org.springframework.http.ResponseEntity;

public interface IdiomService {

    public ResponseEntity<Idiom> createIdiom(Idiom idiom) throws IdiomFoundException;
    public ResponseEntity<Idiom> updateIdiom(Long id, Idiom idiom) throws IdiomNotFoundException;
    public void deleteIdiom(Long id);
}
