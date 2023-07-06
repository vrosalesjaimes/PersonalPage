package com.vrj.mysite.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    @URL
    private String initialImage;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;

    @URL
    private String link;

    @NotBlank
    private String content;

    @OneToOne
    @JoinColumn(name = "idiom_id")
    private Idiom idiom;

    private Set<Image> images;

    private Set<Reference> references;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Tag.class, cascade = CascadeType.PERSIST)
    @JoinTable(name="article_tag", joinColumns = @JoinColumn(name =  "article_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;
}
