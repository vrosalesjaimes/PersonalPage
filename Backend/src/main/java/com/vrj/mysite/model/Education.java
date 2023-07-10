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
@Table(name = "education")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String institution;

    @NotBlank
    private String average;

    private Date initialDate;

    private Date finalDate;

    @OneToOne(cascade = {CascadeType.ALL, CascadeType.REMOVE})
    @JoinColumn(name = "idiom_id")
    private Idiom idiom;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "image_id")
    private Image image;

    public Education update(Education education){

        if(education.getTitle() != null)
            this.title = education.getTitle();
        if(education.getAverage() != null)
            this.average = education.getAverage();
        if(education.getInitialDate() != null)
            this.initialDate = education.getInitialDate();
        if(education.getFinalDate() != null)
            this.finalDate = education.getFinalDate();

        return this;
    }

}
