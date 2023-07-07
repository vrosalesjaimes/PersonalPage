package com.vrj.mysite.services;

import com.vrj.mysite.model.About;
import org.springframework.http.ResponseEntity;

public interface AboutService {

    public ResponseEntity<About> createAbout(About about) throws Exception;
    public ResponseEntity<About> updateAbout(Long id, About about);
    public void deleteAbout(Long id);
}
