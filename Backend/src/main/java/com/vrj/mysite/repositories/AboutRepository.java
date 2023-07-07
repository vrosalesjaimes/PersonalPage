package com.vrj.mysite.repositories;

import com.vrj.mysite.model.About;
import com.vrj.mysite.model.Idiom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AboutRepository extends JpaRepository<About, Long> {

    public About findByIdiom(Idiom idiom);

}
