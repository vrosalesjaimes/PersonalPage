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
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToOne
    @JoinColumn(name = "idiom_id")
    private Idiom idiom;

}
