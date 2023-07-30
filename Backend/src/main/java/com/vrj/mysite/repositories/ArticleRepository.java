package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findByTitle(String title);

    List<Article> findAll();

    Set<Article> findAllByTitleContainingIgnoreCase(String title);

    Set<Article> findAllByAuthors_NameContainingIgnoreCase(String authorName);

    Set<Article> findAllByTags_NameContainingIgnoreCase(String tagName);
}
