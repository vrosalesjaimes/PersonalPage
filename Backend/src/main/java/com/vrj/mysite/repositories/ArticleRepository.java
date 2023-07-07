package com.vrj.mysite.repositories;

import com.vrj.mysite.model.Article;
import com.vrj.mysite.model.Idiom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ArticleRepository extends JpaRepository<Article, Long> {


    public Set<Article> findAllByIdiom(Idiom idiom);

    public Optional<Article> findByTitle(String title);
}
