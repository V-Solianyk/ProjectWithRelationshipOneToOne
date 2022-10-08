package com.example.ProjectWithRelationshipOneToOne.dto;

import com.example.ProjectWithRelationshipOneToOne.entity.Footballer;
import lombok.Data;

@Data
public class FootballerContractDTO {

    private Integer duration;

    private Boolean automaticExtension;

    private Footballer footballer;
}
