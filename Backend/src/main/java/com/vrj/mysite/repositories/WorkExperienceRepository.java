package com.vrj.mysite.repositories;

import com.vrj.mysite.model.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {
    Set<WorkExperience> findAllByIdiom_id(Long idiomId);
}
