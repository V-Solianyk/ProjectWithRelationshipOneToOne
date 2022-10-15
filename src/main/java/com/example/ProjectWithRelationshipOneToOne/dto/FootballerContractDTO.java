package com.example.ProjectWithRelationshipOneToOne.dto;

import lombok.Data;

@Data
public class FootballerContractDTO {

    private Integer duration;

    private Boolean automaticExtension;

    private Long footballerId;
}
