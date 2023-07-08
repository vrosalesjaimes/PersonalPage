package com.vrj.mysite.services;

import com.vrj.mysite.model.About;
import org.springframework.http.ResponseEntity;

public interface AboutService {

    public ResponseEntity<?> createAbout(About about) throws Exception;
    public ResponseEntity<String> updateAbout(Long id, About about);
    public void deleteAbout(Long id);
}
