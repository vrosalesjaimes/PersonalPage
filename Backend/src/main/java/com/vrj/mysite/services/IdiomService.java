package com.vrj.mysite.services;

import com.vrj.mysite.exceptions.IdiomFoundException;
import com.vrj.mysite.exceptions.IdiomNotFoundException;
import com.vrj.mysite.model.Idiom;
import org.springframework.http.ResponseEntity;

public interface IdiomService {

    ResponseEntity<Idiom> createIdiom(Idiom idiom) throws IdiomFoundException;

    ResponseEntity<String> updateIdiom(Long id, Idiom idiom) throws IdiomNotFoundException;

    ResponseEntity<?> deleteIdiom(Long id);
}
