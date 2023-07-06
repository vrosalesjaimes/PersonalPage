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
@Table(name = "certification")
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String institution;

    @DateTimeFormat(pattern = "yyyy")
    private Date date;

    @OneToOne
    @JoinColumn(name = "idiom_id")
    private Idiom idiom;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Tag.class, cascade = CascadeType.PERSIST)
    @JoinTable(name="certification_tag", joinColumns = @JoinColumn(name =  "certification_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

}
