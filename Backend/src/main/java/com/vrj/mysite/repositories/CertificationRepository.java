package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
    Set<Certification> findAllByIdiom_id(Long idiom_id);

    Optional<Certification> findByTitleAndIdiom_id(String title, Long idiom_id);

    Set<Certification> findAllByTitleContainingIgnoreCaseAndIdiom_Id(String title, Long idiomId);

    Set<Certification> findAllByTags_NameContainingIgnoreCaseAndIdiom_Id(String tagName, Long idiomId);
}
