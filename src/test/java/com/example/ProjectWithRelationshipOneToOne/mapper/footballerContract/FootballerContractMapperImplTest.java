package com.example.ProjectWithRelationshipOneToOne.mapper.footballerContract;

import com.example.ProjectWithRelationshipOneToOne.dto.FootballerContractDTO;
import com.example.ProjectWithRelationshipOneToOne.entity.Footballer;
import com.example.ProjectWithRelationshipOneToOne.entity.FootballerContract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FootballerContractMapperImplTest {
    FootballerContractMapperImpl footballerContractMapperImpl = new FootballerContractMapperImpl();

    @Test
    void footballerContractToFootballerContractDTO() {
        Footballer footballer = new Footballer();
        footballer.setId(10L);

        FootballerContract footballerContract = new FootballerContract();
        footballerContract.setFootballer(footballer);
        footballerContract.setAutomaticExtension(true);
        footballerContract.setDuration(4);
        footballerContract.setAnnualSalary(111111);

        FootballerContractDTO footballerContractDTO = footballerContractMapperImpl
                .footballerContractToFootballerContractDTO(footballerContract);

        Assertions.assertEquals(footballerContract.getFootballer().getId(), footballerContractDTO.getFootballerId());
        Assertions.assertEquals(footballerContract.getAutomaticExtension(), footballerContractDTO.getAutomaticExtension());
        Assertions.assertEquals(footballerContract.getDuration(), footballerContractDTO.getDuration());
        Assertions.assertEquals(footballerContract.getAnnualSalary(), footballerContractDTO.getAnnualSalary());
    }

    @Test
    void footballerContractDTOToFootballerContract() {
        FootballerContractDTO footballerContractDTO = new FootballerContractDTO();
        footballerContractDTO.setAnnualSalary(222222);
        footballerContractDTO.setDuration(5);
        footballerContractDTO.setAutomaticExtension(true);

        FootballerContract footballerContract = footballerContractMapperImpl
                .footballerContractDTOToFootballerContract(footballerContractDTO);

        Assertions.assertEquals(footballerContractDTO.getAnnualSalary(), footballerContract.getAnnualSalary());
        Assertions.assertEquals(footballerContractDTO.getDuration(), footballerContract.getDuration());
        Assertions.assertEquals(footballerContractDTO.getAutomaticExtension(), footballerContract.getAutomaticExtension());
    }
}
