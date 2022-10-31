package com.example.ProjectWithRelationshipOneToOne.mapper.footballerContract;

import com.example.ProjectWithRelationshipOneToOne.dto.FootballerContractDTO;
import com.example.ProjectWithRelationshipOneToOne.entity.FootballerContract;
import org.springframework.stereotype.Component;

import java.util.Currency;

@Component
public class FootballerContractMapperImpl implements FootballerContractMapper {
    @Override
    public FootballerContractDTO footballerContractToFootballerContractDTO(FootballerContract footballerContract) {
        FootballerContractDTO footballerContractDTO = new FootballerContractDTO();
        footballerContractDTO.setDuration(footballerContract.getDuration());
        footballerContractDTO.setAutomaticExtension(footballerContract.getAutomaticExtension());
        footballerContractDTO.setAnnualSalary(footballerContract.getAnnualSalary());
        footballerContractDTO.setFootballerId(footballerContract.getFootballer().getId());
        footballerContractDTO.setCurrencyCode(footballerContract.getCurrency().getCurrencyCode());

        return footballerContractDTO;
    }

    @Override
    public FootballerContract footballerContractDTOToFootballerContract(FootballerContractDTO footballerContractDTO) {
        FootballerContract footballerContract = new FootballerContract();
        footballerContract.setAnnualSalary(footballerContractDTO.getAnnualSalary());
        footballerContract.setDuration(footballerContractDTO.getDuration());
        footballerContract.setAutomaticExtension(footballerContractDTO.getAutomaticExtension());
        footballerContract.setCurrency(Currency.getInstance(footballerContractDTO.getCurrencyCode()));

        return footballerContract;
    }
}
