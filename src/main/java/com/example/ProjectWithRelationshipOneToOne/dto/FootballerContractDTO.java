package com.example.ProjectWithRelationshipOneToOne.dto;

import lombok.Data;

@Data
public class FootballerContractDTO {

    private Integer duration;

    private int annualSalary;

    private String currencyCode;

    private Boolean automaticExtension;

    private Long footballerId;
}
