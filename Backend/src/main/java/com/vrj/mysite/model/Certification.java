package com.vrj.mysite.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "certification")
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String institution;

    @DateTimeFormat(pattern = "yyyy")
    private Date date;

    @NotBlank
    private String certificationUrl;

    @ManyToOne
    @JoinColumn(name = "idiom_id")
    private Idiom idiom;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Tag.class, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name="certification_tag", joinColumns = @JoinColumn(name =  "certification_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    public Certification update(Certification certification){
        if (certification.title != null)
            this.title = certification.getTitle();
        if (certification.getInstitution() != null)
            this.institution = certification.getInstitution();
        if (certification.getDate() != null)
            this.date = certification.getDate();
        if(certification.getCertificationUrl() != null)
            this.certificationUrl = certification.getCertificationUrl();

        return this;
    }

    public void addCertifications(Set<Tag> addedTags){
        this.tags.addAll(addedTags);
    }

}
