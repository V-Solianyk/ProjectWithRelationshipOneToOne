package com.example.ProjectWithRelationshipOneToOne.mapper;

import com.example.ProjectWithRelationshipOneToOne.dto.FootballerContractDTO;
import com.example.ProjectWithRelationshipOneToOne.entity.FootballerContract;
import org.springframework.stereotype.Component;

@Component
public class FootballerContractMapperImpl implements FootballerContractMapper {
    @Override
    public FootballerContractDTO footballerContractToFootballerContractDTO(FootballerContract footballerContract) {
        FootballerContractDTO footballerContractDTO = new FootballerContractDTO();
        footballerContractDTO.setDuration(footballerContract.getDuration());
        footballerContractDTO.setAutomaticExtension(footballerContract.getAutomaticExtension());
        footballerContractDTO.setFootballer(footballerContract.getFootballer());
        return footballerContractDTO;
    }

    @Override
    public FootballerContract footballerContractDTOToFootballerContract(FootballerContractDTO footballerContractDTO) {
        FootballerContract footballerContract = new FootballerContract();
        footballerContract.setDuration(footballerContractDTO.getDuration());
        footballerContract.setAutomaticExtension(footballerContractDTO.getAutomaticExtension());
        footballerContract.setFootballer(footballerContractDTO.getFootballer());
        return footballerContract;
    }
}
