package com.example.ProjectWithRelationshipOneToOne.dto;

import lombok.Data;

@Data
public class FootballerContractDTO {

    private Integer duration;

    private Integer annualSalary;

    private Boolean automaticExtension;

    private Long footballerId;
}
