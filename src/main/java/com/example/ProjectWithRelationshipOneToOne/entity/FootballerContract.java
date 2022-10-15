package com.example.ProjectWithRelationshipOneToOne.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Entity
@Table(name = "footballerContract")
public class FootballerContract {
    @Id
    @Column(name = "footballer_id")
    private Long id;

    @Min(1)
    @Max(5)
    private Integer duration;

    @Min(0)
    private Integer annualSalary;

    private Boolean automaticExtension;

    @OneToOne
    @MapsId
    @JoinColumn(name = "footballer_id")
    private Footballer footballer;

}
