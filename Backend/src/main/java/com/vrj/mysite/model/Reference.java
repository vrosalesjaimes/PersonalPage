package com.vrj.mysite.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reference", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Reference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    /* The type of reference for example: book, magazine, article, etc. */
    @NotBlank
    private String type;
    /* Date associated with the reference, e.g. publication date, access date (in the case of online resources. */
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;
    /* The title of recurse. */
    @NotBlank
    private String title;
    /* Reference source e.g. magazine name, newspaper, website */
    @NotBlank
    private String source;
    /* Physical or digital location of the resource */
    @NotBlank
    private String location;
    /* Edition of recurse */
    private Integer edition;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Author.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "author_reference", joinColumns = @JoinColumn(name = "reference_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;

    public Reference update(Reference reference) {
        if (reference.getName() != null)
            this.name = reference.getName();
        if (reference.getType() != null)
            this.type = reference.getType();
        if (reference.getDate() != null)
            this.date = reference.getDate();
        if (reference.getTitle() != null)
            this.title = reference.getTitle();
        if (reference.getSource() != null)
            this.source = reference.getSource();
        if (reference.getLocation() != null)
            this.location = reference.getLocation();
        if (reference.edition != null)
            this.edition = reference.getEdition();

        return this;
    }

    public void addAuthors(Set<Author> addedAuthors) {
        this.authors.addAll(addedAuthors);
    }

}
