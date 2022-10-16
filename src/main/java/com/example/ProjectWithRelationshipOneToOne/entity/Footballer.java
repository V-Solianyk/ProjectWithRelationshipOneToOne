package com.example.ProjectWithRelationshipOneToOne.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "footballers")
public class Footballer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Footballer surname can not be null or blank.")
    private String surname;

    @Min(15)
    @Max(38)
    private Integer age;

    @Min(50)
    @Max(99)
    private Integer rating;

    @NotNull
    private String personalData;

    @OneToOne(mappedBy = "footballer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private FootballerContract footballerContract;

    //todo toString
}
