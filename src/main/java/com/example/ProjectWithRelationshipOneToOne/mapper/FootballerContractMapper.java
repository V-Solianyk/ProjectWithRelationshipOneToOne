package com.example.ProjectWithRelationshipOneToOne.mapper;

import com.example.ProjectWithRelationshipOneToOne.dto.FootballerContractDTO;
import com.example.ProjectWithRelationshipOneToOne.entity.FootballerContract;

public interface FootballerContractMapper {
    FootballerContractDTO footballerContractToFootballerContractDTO(FootballerContract footballerContract);
    FootballerContract footballerContractDTOToFootballerContract(FootballerContractDTO footballerContractDTO);
}
