package com.example.ProjectWithRelationshipOneToOne.mapper;

import com.example.ProjectWithRelationshipOneToOne.dto.FootballerDTO;
import com.example.ProjectWithRelationshipOneToOne.entity.Footballer;

public interface FootballerMapper {
    Footballer footballerDTOToFootballer(FootballerDTO footballerDTO);

    FootballerDTO footballerToFootballerDTO(Footballer footballer);
}
