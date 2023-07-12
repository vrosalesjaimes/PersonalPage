package com.vrj.mysite.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "work_experience")
public class WorkExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String site;

    @DateTimeFormat(pattern = "MM-yyyy")
    private Date initiaiDate;

    @DateTimeFormat(pattern = "MM-yyyy")
    private Date finalDate;

    @NotBlank
    private String activities;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "idiom_id")
    private Idiom idiom;

    public WorkExperience update(WorkExperience workExperience){
        if (workExperience.getTitle() != null)
            this.title = workExperience.getTitle();
        if (workExperience.getSite() != null)
            this.site = workExperience.getSite();
        if (workExperience.getInitiaiDate() != null)
            this.initiaiDate = workExperience.getInitiaiDate();
        if (workExperience.getFinalDate() != null)
            this.finalDate = workExperience.getFinalDate();

        return this;
    }
}
