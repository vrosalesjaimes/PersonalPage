package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findByTitle(String title);

    Set<Article> findAllByIdiom_id(Long idiomId);

    Set<Article> findAllByTitleContainingIgnoreCaseAndIdiom_Id(String title, Long idiomId);

    Set<Article> findAllByAuthors_NameContainingIgnoreCaseAndIdiom_Id(String authorName, Long idiomId);

    Set<Article> findAllByTags_NameContainingIgnoreCaseAndIdiom_Id(String tagName, Long idiomId);
}
