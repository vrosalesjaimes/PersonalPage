package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

    public Image findByUrl(String url);
}
