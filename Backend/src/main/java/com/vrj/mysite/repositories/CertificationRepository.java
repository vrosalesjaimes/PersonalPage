package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Certification;
import com.vrj.mysite.model.Idiom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface CertificationRepository extends JpaRepository<Certification, Long> {

    public Set<Certification> findAllByIdiom(Idiom idiom);
    public Optional<Certification> findByTitle(String title);
    public Set<Certification> findAllByTitleContainingIgnoreCaseAndIdiom_Id(String title, Long idiomId);
    public Set<Certification> findAllByTags_NameContainingIgnoreCaseAndIdiom_Id(String tagName, Long idiomId);
}
