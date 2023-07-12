package com.vrj.mysite.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tag", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "idiom_id")
    private Idiom idiom;

    public Tag update(Tag tag){

        if(tag.getName() != null)
            this.name = tag.getName();
        if(tag.getDescription() != null)
            this.description = tag.getDescription();

        return this;
    }
}
