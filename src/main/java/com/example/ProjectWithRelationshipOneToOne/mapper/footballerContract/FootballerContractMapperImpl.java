package com.example.ProjectWithRelationshipOneToOne.mapper.footballerContract;

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
        footballerContractDTO.setFootballerId(footballerContract.getFootballer().getId());

        return footballerContractDTO;
    }

    @Override
    public FootballerContract footballerContractDTOToFootballerContract(FootballerContractDTO footballerContractDTO) {
        FootballerContract footballerContract = new FootballerContract();
        footballerContract.setAnnualSalary(footballerContractDTO.getAnnualSalary());
        footballerContract.setDuration(footballerContractDTO.getDuration());
        footballerContract.setAutomaticExtension(footballerContractDTO.getAutomaticExtension());

        return footballerContract;
    }
}
