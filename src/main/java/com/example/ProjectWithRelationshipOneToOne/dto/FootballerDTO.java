package com.example.ProjectWithRelationshipOneToOne.dto;

import com.example.ProjectWithRelationshipOneToOne.entity.FootballerContract;
import lombok.Data;

@Data
public class FootballerDTO {
    private String surname;

    private Integer age;

    private Integer rating;

    private FootballerContract footballerContract;
}
