package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Article;
import com.vrj.mysite.model.Idiom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    public Set<Article> findAllByIdiom(Idiom idiom);
    public Optional<Article> findByTitleAndIdiom_id(String title, Long idiomId);
    public Set<Article> findAllByTitleContainingIgnoreCaseAndIdiom_Id(String title, Long idiomId);
    public Set<Article> findAllByAuthors_NameContainingIgnoreCaseAndIdiom_Id(String authorName, Long idiomId);
    public Set<Article> findAllByTags_NameContainingIgnoreCaseAndIdiom_Id(String tagName, Long idiomId);
}
