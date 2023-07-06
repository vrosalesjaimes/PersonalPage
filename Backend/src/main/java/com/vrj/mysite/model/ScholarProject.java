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
@Table(name = "scholar_project")
public class ScholarProject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @URL
    @NotBlank
    private String initialImage;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;

    @URL
    @NotBlank
    private String repository;

    @NotBlank
    private String content;

    @OneToOne
    @JoinColumn(name = "idiom_id")
    private Idiom idiom;

    private Set<Image> images;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Reference.class, cascade = CascadeType.PERSIST)
    @JoinTable(name="scholar_project_reference", joinColumns = @JoinColumn(name =  "scholar_project_id"), inverseJoinColumns = @JoinColumn(name = "reference_id"))
    private Set<Reference> references;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Tag.class, cascade = CascadeType.PERSIST)
    @JoinTable(name="scholar_project_tag", joinColumns = @JoinColumn(name =  "scholar_project_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Author.class, cascade = CascadeType.PERSIST)
    @JoinTable(name="scholar_project_author", joinColumns = @JoinColumn(name =  "scholar_project_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;
}
