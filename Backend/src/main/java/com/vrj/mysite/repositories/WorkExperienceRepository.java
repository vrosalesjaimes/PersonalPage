package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Article;
import com.vrj.mysite.model.Idiom;
import com.vrj.mysite.model.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {

    public Set<WorkExperience> findAllByIdiom(Idiom idiom);

    public Optional<WorkExperience> findByTitle(String title);
    public Set<WorkExperience> findAllByTags_NameContainingIgnoreCaseAndIdiom_Id(String tagName, Long idiomId);
}