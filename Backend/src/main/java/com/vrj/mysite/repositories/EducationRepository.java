package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface EducationRepository extends JpaRepository<Education, Long> {
    Set<Education> findAllByIdiom_id(Long id);

    Optional<Education> findByTitle(String title);
}
