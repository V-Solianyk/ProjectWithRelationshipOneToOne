package com.example.ProjectWithRelationshipOneToOne.service.footballerContract;

import com.example.ProjectWithRelationshipOneToOne.dto.FootballerContractDTO;
import com.example.ProjectWithRelationshipOneToOne.entity.Footballer;
import com.example.ProjectWithRelationshipOneToOne.entity.FootballerContract;
import com.example.ProjectWithRelationshipOneToOne.mapper.footballerContract.FootballerContractMapper;
import com.example.ProjectWithRelationshipOneToOne.repository.FootballerContractRepository;
import com.example.ProjectWithRelationshipOneToOne.repository.FootballerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class FootballerContractImplTest {
    FootballerContractRepository footballerContractRepository = Mockito.mock(FootballerContractRepository.class);
    FootballerContractMapper footballerContractMapper = Mockito.mock(FootballerContractMapper.class);
    FootballerRepository footballerRepository = Mockito.mock(FootballerRepository.class);
    FootballerContractServiceImpl footballerContractService = new FootballerContractServiceImpl
            (footballerContractRepository, footballerContractMapper, footballerRepository);

    private Footballer footballer;
    private FootballerContract footballerContract;
    private FootballerContractDTO footballerContractDTO;
    private Pageable pageable;
    private final int annualSalary = 1000000;
    private final int duration = 5;
    private final boolean automaticExtension = true;

    @BeforeEach
    void beforeEach() {
        footballer = new Footballer();
        footballer.setId(10L);

        footballerContract = new FootballerContract();
        footballerContract.setId(footballer.getId());
        footballerContract.setAnnualSalary(annualSalary);
        footballerContract.setDuration(duration);
        footballerContract.setAutomaticExtension(automaticExtension);

        footballerContractDTO = new FootballerContractDTO();
        footballerContractDTO.setFootballerId(footballer.getId());
        footballerContractDTO.setAnnualSalary(annualSalary);
        footballerContractDTO.setDuration(duration);
        footballerContractDTO.setAutomaticExtension(automaticExtension);

        pageable = PageRequest.of(0, 500);
    }

    @Test
    void getAll() {
        Mockito.when(footballerContractRepository.findAll())
                .thenReturn(List.of(footballerContract, footballerContract));

        Mockito.when(footballerContractMapper
                        .footballerContractToFootballerContractDTO(Mockito.any(FootballerContract.class)))
                .thenReturn(footballerContractDTO);

        List<FootballerContractDTO> responseAllContracts = footballerContractService.getAll();

        Assertions.assertEquals(2, responseAllContracts.size());
        Assertions.assertNotNull(responseAllContracts.get(0));
        Assertions.assertNotNull(responseAllContracts.get(1));
    }

    @Test
    void getAllByDuration() {
        Mockito.when(footballerContractRepository.findAllByDuration(duration, pageable))
                .thenReturn(List.of(footballerContract, footballerContract));

        Mockito.when(footballerContractMapper
                        .footballerContractToFootballerContractDTO(Mockito.any(FootballerContract.class)))
                .thenReturn(footballerContractDTO);

        List<FootballerContractDTO> allByDuration = footballerContractService.getAllByDuration(duration, pageable);

        Assertions.assertEquals(2, allByDuration.size());
        Assertions.assertEquals(duration, allByDuration.get(0).getDuration());
        Assertions.assertEquals(duration, allByDuration.get(1).getDuration());
    }

    @Test
    void getAllByAutomaticExtension() {
        Mockito.when(footballerContractRepository.findAllByAutomaticExtension(automaticExtension, pageable))
                .thenReturn(List.of(footballerContract, footballerContract));

        Mockito.when(footballerContractMapper
                        .footballerContractToFootballerContractDTO(Mockito.any(FootballerContract.class)))
                .thenReturn(footballerContractDTO);

        List<FootballerContractDTO> listContractDTO = footballerContractService
                .getAllByAutomaticExtension(automaticExtension, pageable);

        Assertions.assertEquals(2, listContractDTO.size());
        Assertions.assertEquals(automaticExtension, listContractDTO.get(0).getAutomaticExtension());
        Assertions.assertEquals(automaticExtension, listContractDTO.get(1).getAutomaticExtension());
    }

    @Test
    void getAllByAnnualSalary(){}
}
