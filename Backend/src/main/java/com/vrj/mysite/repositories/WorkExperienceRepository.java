package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Idiom;
import com.vrj.mysite.model.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {

    public Set<WorkExperience> findAllByIdiom(Idiom idiom);

    public WorkExperience findByTitle(String title);
}
