package com.vrj.mysite.dto;


import com.vrj.mysite.model.Tag;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Data
public class PostDTO {
    
    @NotBlank
    private String title;

    @NotBlank
    private String description;
    
    private String initImage;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;
    
    private Set<Tag> tags;
}
