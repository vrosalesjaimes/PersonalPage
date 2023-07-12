package com.vrj.mysite.model;

import com.vrj.mysite.dto.PersonalProjectDTO;
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
@Table(name = "personal_project", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
public class PersonalProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

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

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Image.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name="personal_project_image", joinColumns = @JoinColumn(name =  "personal_project_id"), inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Reference.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name="personal_project_reference", joinColumns = @JoinColumn(name =  "personal_project_id"), inverseJoinColumns = @JoinColumn(name = "reference_id"))
    private Set<Reference> references;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Tag.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name="personal_project_tag", joinColumns = @JoinColumn(name =  "personal_project_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Author.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name="personal_project_author", joinColumns = @JoinColumn(name =  "personal_project_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;

    public PersonalProject update(PersonalProject project){
        if (project.getTitle() != null)
            this.title = project.getTitle();
        if (project.getDescription() != null)
            this.description = project.getDescription();
        if (project.getInitialImage() != null)
            this.initialImage = project.getInitialImage();
        if (project.getDate() != null)
            this.date = project.getDate();
        if (project.getRepository() != null)
            this.repository = project.getRepository();
        return this;
    }

    public void addImages(Set<Image> savedImages){
        this.images.addAll(savedImages);
    }

    public void addReferences(Set<Reference> savedReferences){
        this.references.addAll(savedReferences);
    }

    public void addTags(Set<Tag> savedTags){
        this.tags.addAll(savedTags);
    }

    public void addAuthors(Set<Author> savedAuthors){
        this.authors.addAll(savedAuthors);
    }

    public PersonalProjectDTO toDTO(){
        PersonalProjectDTO projectDTO = new PersonalProjectDTO();
        projectDTO.setTitle(this.getTitle());
        projectDTO.setDescription(this.getDescription());
        projectDTO.setInitialImage(this.getInitialImage());
        projectDTO.setDate(this.getDate());
        projectDTO.setRepository(this.getRepository());
        projectDTO.setTags(this.tags);
        return projectDTO;
    }
}
