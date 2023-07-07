package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Certification;
import com.vrj.mysite.model.Idiom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CertificationRepository extends JpaRepository<Certification, Long> {

    public Set<Certification> findAllByIdiom(Idiom idiom);

    public Certification findByTitle(String title);
}
